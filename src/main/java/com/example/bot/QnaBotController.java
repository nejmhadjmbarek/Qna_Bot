package com.example.bot;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.linecorp.bot.model.message.template.CarouselColumn;
import com.linecorp.bot.model.message.template.CarouselTemplate;
import com.linecorp.bot.model.response.BotApiResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.entity.Book;
import com.example.entity.BotInformation;
import com.example.entity.Sentence;
import com.example.repository.BookRepository;
import com.example.repository.BotInformationRepository;
import com.example.repository.EntityRepository;
import com.example.repository.SentenceEntityRelationRepository;
import com.example.repository.SentenceRepository;
import com.google.cloud.language.v1.AnalyzeEntitiesRequest;
import com.google.cloud.language.v1.AnalyzeEntitiesResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.EncodingType;
import com.google.cloud.language.v1.Entity;
import com.google.cloud.language.v1.EntityMention;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Document.Type;

@RestController
public class QnaBotController {

	private String option = "確認する";

	@Autowired
	SentenceRepository sentenceRepository;

	@Autowired
	EntityRepository entityRepository;
	@Autowired
	SentenceEntityRelationRepository sentenceRelationRepository;
	@Autowired
	BotInformationRepository botInformationRepository;
	@Autowired
	BookRepository bookRepository;

	private static final Logger logger = LoggerFactory.getLogger(QnaBotController.class);
	final String CHANNEL_ACCESS_TOKEN = "w2zniY4znZ94KwmBJcdCh4F72E/RMbInAyVjoI0+NFSVWDkJJU1IaBTRUkCe+wloVEuqk6ezpeYrw+hNHuukTZtl1NXh5OKPVg4VVVAVcmRrA76uRgGli7FN+ZFY7aPavh1li6+cRYlKwsY0ZXvcgwdB04t89/1O/w1cDnyilFU=";
	// final String CHANNEL_ACCESS_TOKEN =
	// "Zo8OmM486rC/8UcDZL3FLs6YAO14ldUxJGx0XLmMBu9cCxITbh/6cPt9W+N4yfiqlZfly0ExnLxaTBuhBNnCYy4h1w2nH1eVliaXZ5CPifZKDFdwTTE8c16bT3WSeWC+KvJvd8i2QpDi2CfFKbrBJQdB04t89/1O/w1cDnyilFU=";
	final LineMessagingClient client = LineMessagingClient.builder(CHANNEL_ACCESS_TOKEN).build();

	@RequestMapping(value = "/webhookQnaBot", method = RequestMethod.POST)
	private @ResponseBody Map<String, Object> webhook(@RequestBody Map<String, Object> obj)
			throws JSONException, IOException {

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

		JSONObject jsonResult = new JSONObject(obj);

		logger.info("******************JSON RESULT!!!!!!! :   '{}' ", jsonResult);

		JSONObject rsl = jsonResult.getJSONObject("originalRequest");
		JSONObject data = rsl.getJSONObject("data");
		String replyToken = data.getString("replyToken");

		JSONObject source = data.getJSONObject("source");
		JSONObject message = data.getJSONObject("message");
		String userId = source.getString("userId");
		String customerMessage = message.getString("text");
		String timestamp = jsonResult.getString("timestamp");
		String language = jsonResult.getString("lang");
		String sessionId = jsonResult.getString("sessionId");
		JSONObject result = jsonResult.getJSONObject("result");
		JSONObject metadata = result.getJSONObject("metadata");
		String intentName = metadata.getString("intentName");
		JSONObject parameters = result.getJSONObject("parameters");
		JSONObject fulfillment = result.getJSONObject("fulfillment");
		String speech = fulfillment.getString("speech");

		logger.info("********************LANGUAGE : '{}'", language);
		logger.info("*******************INTENT NAME : '{}'", intentName);
		logger.info("********************CUSTOMER MESSAGE : '{}'", customerMessage);
		logger.info("********************replyToken : '{}'", replyToken);

		BotInformation botInformation = new BotInformation();

		switch (intentName) {
		case "Default Fallback Intent":
			logger.info("********************Default Fallback Intent******************");

			try {

				botInformation.setSentenceToSearch(customerMessage);
				botInformationRepository.saveAndFlush(botInformation);
				List<Entity> entities = analyzeEntitiesText(customerMessage);

				Sentence sentence = new Sentence();
				sentence = similarSentance(entities);
				if (sentence != null && !sentence.equals("")) {

					if (sentence.getSentence().length() > 151) {
						ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null,
								sentence.getSentence().substring(0, 151),
								Arrays.asList(new MessageAction("素晴らしいです！", "素晴らしいです！")));
						TemplateMessage templateMessage = new TemplateMessage(sentence.getSentence().substring(0, 151),
								buttonsTemplate);

						ReplyMessage replyMessage = new ReplyMessage(replyToken, templateMessage);
						BotApiResponse botApiResponse;
						try {
							botApiResponse = client.replyMessage(replyMessage).get();
						} catch (InterruptedException | ExecutionException e) {
							e.printStackTrace();
						}

					} else {
						ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null, sentence.getSentence(),
								Arrays.asList(new MessageAction("素晴らしいです！", "素晴らしいです！")));
						TemplateMessage templateMessage = new TemplateMessage(sentence.getSentence() + " 素晴らしいです!",
								buttonsTemplate);

						ReplyMessage replyMessage = new ReplyMessage(replyToken, templateMessage);
						BotApiResponse botApiResponse;
						try {
							botApiResponse = client.replyMessage(replyMessage).get();
						} catch (InterruptedException | ExecutionException e) {
							e.printStackTrace();
						}

					}

				} else {

					try {

						logger.info("------------------ENTITIES SIZE -------------------- '{}'", entities.size());
						if (entities.isEmpty()) {
							TextMessage textMessage1 = new TextMessage("なに？それは具体的にどういうことかな？");

							ReplyMessage replyMessage = new ReplyMessage(replyToken, textMessage1);
							BotApiResponse botApiResponse;
							try {
								botApiResponse = client.replyMessage(replyMessage).get();
							} catch (InterruptedException | ExecutionException e) {
								e.printStackTrace();
							}

						} else {

							ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null,
									"ふむ。。。それについては考えがまとまってないな。",
									Arrays.asList(new MessageAction("でも、教えてほしいです！", "でも、教えてほしいです！")));
							TemplateMessage templateMessage = new TemplateMessage("ふむ。。。それについては考えがまとまってないな。",
									buttonsTemplate);

							ReplyMessage replyMessage = new ReplyMessage(replyToken, templateMessage);
							BotApiResponse botApiResponse;
							try {
								botApiResponse = client.replyMessage(replyMessage).get();
							} catch (InterruptedException | ExecutionException e) {
								e.printStackTrace();
							}
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			break;

		case "noEntityInDB":
			int idSentence;
			idSentence = anyID("jp");
			logger.info("*************idSentence****************'{}'", idSentence);
			Sentence sentence1 = new Sentence();
			sentence1 = sentenceRepository.findOne(idSentence);
			botInformation.setSentenceToSearch(sentence1.getSentence());
			botInformationRepository.saveAndFlush(botInformation);

			if (sentence1 != null && !sentence1.equals("")) {

				if (sentence1.getSentence().length() > 149) {

					ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null,
							sentence1.getSentence().substring(0, 149),
							Arrays.asList(new MessageAction("素晴らしいです！", "素晴らしいです！")));
					TemplateMessage templateMessage = new TemplateMessage(sentence1.getSentence().substring(0, 149),
							buttonsTemplate);

					ReplyMessage replyMessage1 = new ReplyMessage(replyToken, templateMessage);
					BotApiResponse botApiResponse1;
					try {
						botApiResponse1 = client.replyMessage(replyMessage1).get();
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
					}
				} else {
					ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null, sentence1.getSentence(),
							Arrays.asList(new MessageAction("素晴らしいです！", "素晴らしいです！")));
					TemplateMessage templateMessage = new TemplateMessage(sentence1.getSentence(), buttonsTemplate);

					ReplyMessage replyMessage1 = new ReplyMessage(replyToken, templateMessage);
					BotApiResponse botApiResponse1;
					try {
						botApiResponse1 = client.replyMessage(replyMessage1).get();
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
					}

				}

			} else {
				logger.info("*************NO RANDOM SENTENCE JAPANESE************************** ");
			}
			break;

		/************ user wants to invite his friends *************/
		case "invite friend":
			logger.info("-----------JAPANESE LANGUAGE INVITE FRIEND----------------");
			TextMessage textMessage1 = new TextMessage("そうか。ではこのリンクをシェアするが良い。\n https://line.me/R/ti/p/%40tsv3082a");
			ReplyMessage replyMessage = new ReplyMessage(replyToken, textMessage1);
			BotApiResponse botApiResponse;
			try {
				botApiResponse = client.replyMessage(replyMessage).get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}

			break;
		case "search random sentence":
			try {
				int idSentence1;
				idSentence1 = anyID("jp");
				logger.info("*************idSentence****************", idSentence1);
				Sentence sentence11 = new Sentence();
				sentence11 = sentenceRepository.findOne(idSentence1);
				botInformation.setSentenceToSearch(sentence11.getSentence());
				botInformationRepository.saveAndFlush(botInformation);
				if (sentence11 != null && !sentence11.equals("")) {

					if (sentence11.getSentence().length() > 151) {

						ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null,
								sentence11.getSentence().substring(0, 151),
								Arrays.asList(new MessageAction("素晴らしいです！", "素晴らしいです！")));
						TemplateMessage templateMessage = new TemplateMessage(
								sentence11.getSentence().substring(0, 151), buttonsTemplate);

						ReplyMessage replyMessage1 = new ReplyMessage(replyToken, templateMessage);
						BotApiResponse botApiResponse1;
						try {
							botApiResponse1 = client.replyMessage(replyMessage1).get();
						} catch (InterruptedException | ExecutionException e) {
							e.printStackTrace();
						}

					} else {
						ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null, sentence11.getSentence(),
								Arrays.asList(new MessageAction("素晴らしいです！", "素晴らしいです！")));
						TemplateMessage templateMessage = new TemplateMessage(sentence11.getSentence(),
								buttonsTemplate);

						ReplyMessage replyMessage1 = new ReplyMessage(replyToken, templateMessage);
						BotApiResponse botApiResponse1;
						try {
							botApiResponse1 = client.replyMessage(replyMessage1).get();
						} catch (InterruptedException | ExecutionException e) {
							e.printStackTrace();
						}

					}
				} else {
					logger.info("*************NO RANDOM SENTENCE JAPANESE************************** ");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "nice":
			ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null, "そうか。おぬしに役立ちそうな本をおすすめしてもいいか？",
					Arrays.asList(new MessageAction("教えてください！", "教えてください！")));
			TemplateMessage templateMessage = new TemplateMessage("そうか。おぬしに役立ちそうな本をおすすめしてもいいか？", buttonsTemplate);
			ReplyMessage replyMessage1 = new ReplyMessage(replyToken, templateMessage);
			BotApiResponse botApiResponse1;
			try {
				botApiResponse1 = client.replyMessage(replyMessage1).get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}

			break;

		case "pleaseTellMe":
			List<Integer> bookIDs = new ArrayList<>();
			bookIDs = bookRepository.getAllBooksIDs();
			List<Integer> randomBookIDs = new ArrayList<>();
			randomBookIDs = pickNRandom(bookIDs, 5);
			logger.info("-----------PLEASE TELL ME INTENT------------------");
			try {
				sendCarouselBooks(botInformation, CHANNEL_ACCESS_TOKEN, replyToken, timestamp, randomBookIDs);
			} catch (IOException | JSONException e) {
				logger.error("exception", e);
			}
			break;
		}
		return obj;

	}

	/**
	 * Identifies entities in the string {@code text}.
	 * 
	 * @return
	 */
	public List<Entity> analyzeEntitiesText(String text) throws Exception {
		// [START analyze_entities_text]
		// Instantiate the Language client
		// com.google.cloud.language.v1.LanguageServiceClient
		try (LanguageServiceClient language = LanguageServiceClient.create()) {
			Document doc = Document.newBuilder().setContent(text).setType(Type.PLAIN_TEXT).build();
			AnalyzeEntitiesRequest request = AnalyzeEntitiesRequest.newBuilder().setDocument(doc)
					.setEncodingType(EncodingType.UTF16).build();

			AnalyzeEntitiesResponse response = language.analyzeEntities(request);

			// Print the response
			for (Entity entity : response.getEntitiesList()) {
				logger.info("********************Entity : '{}'", entity.getName());
				logger.info("********************Salience : '{}'", entity.getSalience());

				// for (Map.Entry<String, String> entry : entity.getMetadataMap().entrySet()) {
				// System.out.printf("%s : %s", entry.getKey(), entry.getValue());
				// }

				for (EntityMention mention : entity.getMentionsList()) {
					System.out.printf("Begin offset: %d\n", mention.getText().getBeginOffset());
					System.out.printf("Content: %s\n", mention.getText().getContent());
					System.out.printf("Type: %s\n\n", mention.getType());
				}
			}
			return response.getEntitiesList();

		}

		// [END analyze_entities_text]
	}

	public Sentence similarSentance(List<Entity> userEntities) {

		List<Sentence> sentences = new ArrayList<>();
		sentences = sentenceRepository.findAll();
		float maxSalience = 0;
		Sentence similarSentence = null;

		List<String> entitiesNames = new ArrayList<>();
		for (Entity e : userEntities) {
			entitiesNames.add(e.getName());
		}
		List<com.example.entity.Entity> entities = new ArrayList<>();
		float salience = 0;
		for (Sentence s : sentences) {
			entities = sentenceRelationRepository.getEntitiesOfSentence(s.getIdSentence());
			for (com.example.entity.Entity e : entities) {
				if (entitiesNames.contains(e.getNameEntity())) {
					for (Entity en : userEntities) {
						salience = salience + (en.getSalience() * e.getSalience());
					}
				}
			}

			if (salience > maxSalience) {
				maxSalience = salience;
				similarSentence = s;
			}
			salience = 0;
		}
		return similarSentence;
	}

	public int anyID(String language) {
		List<Integer> sentenceIDs = new ArrayList<>();
		sentenceIDs = sentenceRepository.getAllSentenceIDs(language);
		logger.info("****************sentenceIDs SIZE******************** '{}'", sentenceIDs.size());
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(sentenceIDs.size());
		int item = sentenceIDs.get(index);
		logger.info("****************item Japanese******************** '{}'", item);
		return item;
	}

	public static List<Integer> pickNRandom(List<Integer> lst, int n) {
		List<Integer> copy = new LinkedList<Integer>(lst);
		Collections.shuffle(copy);
		return copy.subList(0, n);
	}

	public void sendCarouselBooks(BotInformation botInformation, String CHANNEL_ACCESS_TOKEN, String replyToken,
			String timestamp, List<Integer> randomBookdsID) throws IOException, JSONException {

		logger.info("--------------------------------REPLY TOKEN ---------------------- '{}'", replyToken);
		java.util.List<CarouselColumn> columns = new ArrayList<>();
		String img = null;
		String title = "";
		String text = "";
		Image image = null;

		logger.info("------------Book IDS Size --------------- '{}'", randomBookdsID.size());
		for (int id : randomBookdsID) {
			Book b = new Book();
			b = bookRepository.findOne(id);
			img = b.getImageURL();
			try {
				URL url = new URL(img);
				image = ImageIO.read(url);
			} catch (IOException e) {
				logger.error("*************EXCEPTION URL IMAGE***************", e);
			}

			if (b.getNameBook() != null && !b.getNameBook().equals("")) {

				text = b.getNameBook();
				byte[] textByte = text.getBytes(StandardCharsets.UTF_8); // Explicit,
				text = new String(textByte, StandardCharsets.UTF_8);
			} else {
				text = "no text found ";
			}
			String link = b.getLink();
			URIAction uriAction = new URIAction(option, link);
			CarouselColumn column = new CarouselColumn(img, null, text, Arrays.asList(uriAction));
			columns.add(column);
		}

		CarouselTemplate carouselTemplate = new CarouselTemplate(columns);
		String templateText = "";
		templateText = "この本を読んでみるが良い。";

		TemplateMessage templateMessage = new TemplateMessage(templateText, carouselTemplate);

		ReplyMessage replyMessage1 = new ReplyMessage(replyToken, templateMessage);
		BotApiResponse botApiResponse1;
		try {
			botApiResponse1 = client.replyMessage(replyMessage1).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}

}
