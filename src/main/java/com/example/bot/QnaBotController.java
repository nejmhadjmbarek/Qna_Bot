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

import javax.imageio.ImageIO;

import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.linecorp.bot.model.message.template.CarouselColumn;
import com.linecorp.bot.model.message.template.CarouselTemplate;

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

	@RequestMapping(value = "/webhookQnaBot", method = RequestMethod.POST)
	private @ResponseBody Map<String, Object> webhook(@RequestBody Map<String, Object> obj)
			throws JSONException, IOException {

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		String CHANNEL_ACCESS_TOKEN = "w2zniY4znZ94KwmBJcdCh4F72E/RMbInAyVjoI0+NFSVWDkJJU1IaBTRUkCe+wloVEuqk6ezpeYrw+hNHuukTZtl1NXh5OKPVg4VVVAVcmRrA76uRgGli7FN+ZFY7aPavh1li6+cRYlKwsY0ZXvcgwdB04t89/1O/w1cDnyilFU=";

		JSONObject jsonResult = new JSONObject(obj);

		logger.info("******************JSON RESULT!!!!!!! :   '{}' ", jsonResult);

		JSONObject rsl = jsonResult.getJSONObject("originalRequest");
		JSONObject data = rsl.getJSONObject("data");
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
					if (botInformation.getLanguageBot().equals("japanese")) {

						if (sentence.getSentence().length() > 151) {
							ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null,
									sentence.getSentence().substring(0, 151),
									Arrays.asList(new MessageAction("素晴らしいです！", "素晴らしいです！")));
							TemplateMessage templateMessage = new TemplateMessage(
									sentence.getSentence().substring(0, 151), buttonsTemplate);

							PushMessage pushMessage = new PushMessage(userId, templateMessage);
							LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage)
									.execute();

						} else {
							ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null, sentence.getSentence(),
									Arrays.asList(new MessageAction("素晴らしいです！", "素晴らしいです！")));
							TemplateMessage templateMessage = new TemplateMessage(sentence.getSentence() + " 素晴らしいです!",
									buttonsTemplate);

							PushMessage pushMessage = new PushMessage(userId, templateMessage);
							LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage)
									.execute();
						}

					}
					if (botInformation.getLanguageBot().equals("english")) {
						if (sentence.getSentence().length() > 138) {
							ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null,
									sentence.getSentence().substring(0, 138),
									Arrays.asList(new MessageAction("Nice！", "Nice!")));
							TemplateMessage templateMessage = new TemplateMessage(
									sentence.getSentence().substring(0, 138), buttonsTemplate);

							PushMessage pushMessage = new PushMessage(userId, templateMessage);
							LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage)
									.execute();
						} else {
							ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null, sentence.getSentence(),
									Arrays.asList(new MessageAction("Nice！", "Nice!")));
							TemplateMessage templateMessage = new TemplateMessage(sentence.getSentence(),
									buttonsTemplate);

							PushMessage pushMessage = new PushMessage(userId, templateMessage);
							LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage)
									.execute();
						}
					}
				} else {

					try {

						int idSentence;
						if (botInformation.getLanguageBot().equals("japanese")) {

							logger.info("------------------ENTITIES SIZE -------------------- '{}'", entities.size());
							if (entities.isEmpty()) {
								TextMessage textMessage1 = new TextMessage("具体的にどういうことかな？");
								PushMessage pushMessage1 = new PushMessage(userId, textMessage1);
								LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build()
										.pushMessage(pushMessage1).execute();
							} else {
								idSentence = anyID("jp");
								logger.info("*************idSentence****************'{}'", idSentence);
								Sentence sentence1 = new Sentence();
								sentence1 = sentenceRepository.findOne(idSentence);
								botInformation.setSentenceToSearch(sentence1.getSentence());
								botInformationRepository.saveAndFlush(botInformation);
								TextMessage textMessage1 = new TextMessage("それについては考えたことがないな。。。つまりこういうことかな？");

								PushMessage pushMessage1 = new PushMessage(userId, textMessage1);
								LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build()
										.pushMessage(pushMessage1).execute();

								if (sentence1 != null && !sentence1.equals("")) {

									if (sentence1.getSentence().length() > 149) {

										ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null,
												sentence1.getSentence().substring(0, 149),
												Arrays.asList(new MessageAction("素晴らしいです！", "素晴らしいです！")));
										TemplateMessage templateMessage = new TemplateMessage(
												sentence1.getSentence().substring(0, 149), buttonsTemplate);

										PushMessage pushMessage = new PushMessage(userId, templateMessage);
										LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build()
												.pushMessage(pushMessage).execute();

									} else {
										ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null,
												sentence1.getSentence(),
												Arrays.asList(new MessageAction("素晴らしいです！", "素晴らしいです！")));
										TemplateMessage templateMessage = new TemplateMessage(sentence1.getSentence(),
												buttonsTemplate);

										PushMessage pushMessage = new PushMessage(userId, templateMessage);
										LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build()
												.pushMessage(pushMessage).execute();
									}

								} else {
									logger.info("*************NO RANDOM SENTENCE JAPANESE************************** ");
								}
							}
						}

						else if (botInformation.getLanguageBot().equals("english")) {
							if (entities.isEmpty()) {
								TextMessage textMessage1 = new TextMessage("ask me in detail");
								PushMessage pushMessage1 = new PushMessage(userId, textMessage1);
								LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build()
										.pushMessage(pushMessage1).execute();
							} else {
								idSentence = anyID("en");
								logger.info("*************idSentence****************'{}'", idSentence);
								Sentence sentence1 = new Sentence();
								sentence1 = sentenceRepository.findOne(idSentence);
								botInformation.setSentenceToSearch(sentence1.getSentence());
								botInformationRepository.saveAndFlush(botInformation);

								TextMessage textMessage1 = new TextMessage(
										"I couldn't find the sentence. What about this?");
								PushMessage pushMessage1 = new PushMessage(userId, textMessage1);
								LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build()
										.pushMessage(pushMessage1).execute();

								if (sentence1 != null && !sentence1.equals("")) {

									if (sentence1.getSentence().length() > 136) {

										ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null,
												sentence1.getSentence().substring(0, 136),
												Arrays.asList(new MessageAction("Nice!", "Nice!")));
										TemplateMessage templateMessage = new TemplateMessage(
												sentence1.getSentence().substring(0, 136), buttonsTemplate);

										PushMessage pushMessage = new PushMessage(userId, templateMessage);
										LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build()
												.pushMessage(pushMessage).execute();
									} else {
										ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null,
												sentence1.getSentence(),
												Arrays.asList(new MessageAction("Nice!", "Nice!")));
										TemplateMessage templateMessage = new TemplateMessage(sentence1.getSentence(),
												buttonsTemplate);

										PushMessage pushMessage = new PushMessage(userId, templateMessage);
										LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build()
												.pushMessage(pushMessage).execute();
									}

								} else {
									logger.info("*************NO RANDOM SENTENCE ENGLISH************************** ");
								}
							}
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				//
				// for (Entity e : entities) {
				// Sentence sentenceUser = new Sentence();
				// sentenceUser.setSentence(customerMessage);
				// sentenceRepository.saveAndFlush(sentenceUser);
				// com.example.entity.Entity entityUser = new com.example.entity.Entity();
				// entityUser.setNameEntity(e.getName());
				// entityUser.setSalience(e.getSalience());
				// entityRepository.saveAndFlush(entityUser);
				// SentenceEntityRelationPK sentenceEntityRelationPK = new
				// SentenceEntityRelationPK();
				// sentenceEntityRelationPK.setIdEntity(entityUser.getIdEntity());
				// sentenceEntityRelationPK.setIdSentence(sentenceUser.getIdSentence());
				//
				// SentenceEntityRelation sentenceEntityRelation = new SentenceEntityRelation();
				// sentenceEntityRelation.setSentenceRelationPK(sentenceEntityRelationPK);
				// sentenceEntityRelation.setEntity(entityUser);
				// sentenceEntityRelation.setSentence(sentenceUser);
				//
				// sentenceRelationRepository.saveAndFlush(sentenceEntityRelation);
				// }

			} catch (Exception e) {
				e.printStackTrace();
			}

			break;

		/************ user wants to invite his friends *************/
		case "invite friend":
			if (botInformation.getLanguageBot().equals("japanese")) {
				logger.info("-----------JAPANESE LANGUAGE INVITE FRIEND----------------");
				TextMessage textMessage1 = new TextMessage("どうも。このリンクをシェアしてくれ。\n https://line.me/R/ti/p/%40tms8877v");
				PushMessage pushMessage1 = new PushMessage(userId, textMessage1);
				LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage1).execute();
			} else if (botInformation.getLanguageBot().equals("english")) {
				logger.info("-----------ENGLISH LANGUAGE INVITE FRIEND----------------");
				TextMessage textMessage1 = new TextMessage(
						"Thank you! here is your link.\n https://line.me/R/ti/p/%40tms8877v");
				PushMessage pushMessage1 = new PushMessage(userId, textMessage1);
				LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage1).execute();
			}

			break;
		case "search random sentence":
			try {
				int idSentence;
				if (botInformation.getLanguageBot().equals("japanese")) {
					idSentence = anyID("jp");
					logger.info("*************idSentence****************", idSentence);
					Sentence sentence1 = new Sentence();
					sentence1 = sentenceRepository.findOne(idSentence);
					botInformation.setSentenceToSearch(sentence1.getSentence());
					botInformationRepository.saveAndFlush(botInformation);
					if (sentence1 != null && !sentence1.equals("")) {

						if (sentence1.getSentence().length() > 151) {

							ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null,
									sentence1.getSentence().substring(0, 151),
									Arrays.asList(new MessageAction("素晴らしいです！", "素晴らしいです！")));
							TemplateMessage templateMessage = new TemplateMessage(
									sentence1.getSentence().substring(0, 151), buttonsTemplate);

							PushMessage pushMessage = new PushMessage(userId, templateMessage);
							LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage)
									.execute();

						} else {
							ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null, sentence1.getSentence(),
									Arrays.asList(new MessageAction("素晴らしいです！", "素晴らしいです！")));
							TemplateMessage templateMessage = new TemplateMessage(sentence1.getSentence(),
									buttonsTemplate);

							PushMessage pushMessage = new PushMessage(userId, templateMessage);
							LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage)
									.execute();
						}
					} else {
						logger.info("*************NO RANDOM SENTENCE JAPANESE************************** ");
					}

				}

				else if (botInformation.getLanguageBot().equals("english")) {
					idSentence = anyID("en");
					logger.info("*************idSentence****************", idSentence);
					Sentence sentence1 = new Sentence();
					sentence1 = sentenceRepository.findOne(idSentence);
					botInformation.setSentenceToSearch(sentence1.getSentence());
					botInformationRepository.saveAndFlush(botInformation);
					if (sentence1 != null && !sentence1.equals("")) {

						if (sentence1.getSentence().length() > 138) {

							ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null,
									sentence1.getSentence().substring(0, 138),
									Arrays.asList(new MessageAction("Nice!", "Nice!")));
							TemplateMessage templateMessage = new TemplateMessage(
									sentence1.getSentence().substring(0, 138), buttonsTemplate);

							PushMessage pushMessage = new PushMessage(userId, templateMessage);
							LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage)
									.execute();
						} else {
							ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null, sentence1.getSentence(),
									Arrays.asList(new MessageAction("Nice!", "Nice!")));
							TemplateMessage templateMessage = new TemplateMessage(sentence1.getSentence(),
									buttonsTemplate);

							PushMessage pushMessage = new PushMessage(userId, templateMessage);
							LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage)
									.execute();
						}

					}

					else {
						logger.info("*************NO RANDOM SENTENCE ENGLISH************************** ");
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "nice":

			List<Integer> bookIDs = new ArrayList<>();
			bookIDs = bookRepository.getAllBooksIDs();
			List<Integer> randomBookIDs = new ArrayList<>();
			randomBookIDs = pickNRandom(bookIDs, 5);
			if (botInformation.getLanguageBot().equals("japanese")) {
				TextMessage textMessage1 = new TextMessage("どうも。この本を読んでみないか？君に役立つであろう。");
				PushMessage pushMessage1 = new PushMessage(userId, textMessage1);
				LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage1).execute();

				try {
					sendCarouselBooks(botInformation, userId, CHANNEL_ACCESS_TOKEN, timestamp, randomBookIDs);
				} catch (IOException | JSONException e) {
					logger.error("exception", e);
				}

			} else if (botInformation.getLanguageBot().equals("english")) {
				TextMessage textMessage1 = new TextMessage("Thank you! You can check more from this book.");
				PushMessage pushMessage1 = new PushMessage(userId, textMessage1);
				LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage1).execute();
				try {
					sendCarouselBooks(botInformation, userId, CHANNEL_ACCESS_TOKEN, timestamp, randomBookIDs);
				} catch (IOException | JSONException e) {
					logger.error("exception", e);
				}
			}
			break;

		case "change language":
			if (botInformation.getLanguageBot().equals("japanese")) {
				ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null, "言語を変えたいです。",
						Arrays.asList(new MessageAction("English", "English"), new MessageAction("日本語", "日本語")));
				TemplateMessage templateMessage = new TemplateMessage("言語を変えたいです。", buttonsTemplate);
				PushMessage pushMessage = new PushMessage(userId, templateMessage);
				LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage).execute();
			}
			if (botInformation.getLanguageBot().equals("english")) {
				ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null, "Change language",
						Arrays.asList(new MessageAction("English", "English"), new MessageAction("日本語", "日本語")));
				TemplateMessage templateMessage = new TemplateMessage("Change language", buttonsTemplate);
				PushMessage pushMessage = new PushMessage(userId, templateMessage);
				LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage).execute();
			}
			break;

		case "language":
			logger.info("------------language--------------------'{}'", customerMessage);
			if (customerMessage.equals("English")) {
				botInformation.setLanguageBot("english");
			} else {
				botInformation.setLanguageBot("japanese");
			}
			botInformationRepository.saveAndFlush(botInformation);
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

	public void sendCarouselBooks(BotInformation botInformation, String userId, String CHANNEL_ACCESS_TOKEN,
			String timestamp, List<Integer> randomBookdsID) throws IOException, JSONException {

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
		templateText = "Which book?";

		TemplateMessage templateMessage = new TemplateMessage(templateText, carouselTemplate);
		PushMessage pushMessage = new PushMessage(userId, templateMessage);

		try {
			LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage).execute();
		} catch (IOException e) {
			logger.error("Exception is raised ", e);
		}
	}

}
