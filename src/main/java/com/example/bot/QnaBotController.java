package com.example.bot;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.ButtonsTemplate;

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
import org.w3c.dom.ls.LSInput;

import com.example.entity.BotInformation;
import com.example.entity.Sentence;
import com.example.entity.SentenceEntityRelation;
import com.example.entity.SentenceEntityRelationPK;
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

	@Autowired
	SentenceRepository sentenceRepository;

	@Autowired
	EntityRepository entityRepository;
	@Autowired
	SentenceEntityRelationRepository sentenceRelationRepository;
	@Autowired
	BotInformationRepository botInformationRepository;

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
					TextMessage textMessage = new TextMessage(sentence.getSentence());
					PushMessage pushMessage = new PushMessage(userId, textMessage);
					LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage).execute();
				} else {
					if (botInformation.getLanguageBot().equals("japanese")) {
						ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null,
								"該当する名言が見つかりませんでした。この言葉はどうですか？ -> " + sentence.getSentence(),
								Arrays.asList(new MessageAction("素晴らしい", "素晴らしい")));
						TemplateMessage templateMessage = new TemplateMessage(
								"該当する名言が見つかりませんでした。この言葉はどうですか？ -> " + sentence.getSentence(), buttonsTemplate);

						PushMessage pushMessage = new PushMessage(userId, templateMessage);
						LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage)
								.execute();
					}
					if (botInformation.getLanguageBot().equals("english")) {
						ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null,
								"I couldn't find the sentence. What about this? -> " + sentence.getSentence(),
								Arrays.asList(new MessageAction("Nice!", "Nice!")));
						TemplateMessage templateMessage = new TemplateMessage(
								"I couldn't find the sentence. What about this? -> " + sentence.getSentence(),
								buttonsTemplate);
						PushMessage pushMessage = new PushMessage(userId, templateMessage);
						LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage)
								.execute();
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
				TextMessage textMessage1 = new TextMessage(
						"ありがとうございます！このリンクをシェアしてください！ https://line.me/R/ti/p/%40tms8877v");
				PushMessage pushMessage1 = new PushMessage(userId, textMessage1);
				LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage1).execute();
			} else if (botInformation.getLanguageBot().equals("english")) {
				TextMessage textMessage1 = new TextMessage(
						"Thank you! here is your link. https://line.me/R/ti/p/%40tms8877v");
				PushMessage pushMessage1 = new PushMessage(userId, textMessage1);
				LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage1).execute();
			}

			break;
		case "search random sentence":
			try {
				int idSentence;
				if (botInformation.getLanguageBot().equals("japanese")) {
					idSentence = anyID("japanese");
					Sentence sentence = new Sentence();
					sentence = sentenceRepository.findOne(idSentence);
					botInformation.setSentenceToSearch(sentence.getSentence());
					botInformationRepository.saveAndFlush(botInformation);
					if (sentence != null && !sentence.equals("")) {
						ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null,
								"該当する名言が見つかりませんでした。この言葉はどうですか？ -> " + sentence.getSentence(),
								Arrays.asList(new MessageAction("素晴らしい", "素晴らしい")));
						TemplateMessage templateMessage = new TemplateMessage(
								"該当する名言が見つかりませんでした。この言葉はどうですか？ -> " + sentence.getSentence(), buttonsTemplate);

						PushMessage pushMessage = new PushMessage(userId, templateMessage);
						LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage)
								.execute();

					} else {
						logger.info("*************NO RANDOM SENTENCE JAPANESE************************** ");
					}

				}

				else if (botInformation.getLanguageBot().equals("english")) {
					idSentence = anyID("english");
					Sentence sentence = new Sentence();
					sentence = sentenceRepository.findOne(idSentence);
					botInformation.setSentenceToSearch(sentence.getSentence());
					botInformationRepository.saveAndFlush(botInformation);
					if (sentence != null && !sentence.equals("")) {
						ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, null,
								"I couldn't find the sentence. What about this? -> " + sentence.getSentence(),
								Arrays.asList(new MessageAction("Nice!", "Nice!")));
						TemplateMessage templateMessage = new TemplateMessage(
								"I couldn't find the sentence. What about this? -> " + sentence.getSentence(),
								buttonsTemplate);

						PushMessage pushMessage = new PushMessage(userId, templateMessage);
						LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage)
								.execute();

					} else {
						logger.info("*************NO RANDOM SENTENCE ENGLISH************************** ");
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "nice":
			if (botInformation.getLanguageBot().equals("japanese")) {
				TextMessage textMessage1 = new TextMessage(
						"ありがとうございます！この本からトラッカーさんの名言をもっと確認できます。https://amzn.to/2Gq1FMr");
				PushMessage pushMessage1 = new PushMessage(userId, textMessage1);
				LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage1).execute();
			} else if (botInformation.getLanguageBot().equals("english")) {
				TextMessage textMessage1 = new TextMessage("Thank you! You can check more from this book.");
				PushMessage pushMessage1 = new PushMessage(userId, textMessage1);
				LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage1).execute();
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
			logger.info("------------language--------------------", customerMessage);
			if (customerMessage.equals("english")) {
				botInformation.setLanguageBot("English");
			} else {
				botInformation.setLanguageBot("Japanese");
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
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(sentenceIDs.size());
		int item = sentenceIDs.get(index);
		logger.info("****************item Japanese******************** '{}'", item);
		return item;
	}

}
