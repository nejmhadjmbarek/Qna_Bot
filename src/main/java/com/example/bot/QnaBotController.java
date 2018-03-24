package com.example.bot;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;

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

		if (intentName.equals("Default Fallback Intent")) {
			logger.info("********************Default Fallback Intent******************");

			try {
				BotInformation botInformation = new BotInformation();
				botInformation.setSentenceToSearch(customerMessage);
				botInformationRepository.saveAndFlush(botInformation);
				List<Entity> entities = analyzeEntitiesText(customerMessage);
				List<String> entitiesNames = new ArrayList<>();
				for (Entity e : entities) {
					entitiesNames.add(e.getName());
				}

				Sentence sentence = new Sentence();
				sentence = similarSentance(entitiesNames);
				if (sentence != null && !sentence.equals("")) {
					TextMessage textMessage = new TextMessage(sentence.getSentence());
					PushMessage pushMessage = new PushMessage(userId, textMessage);
					LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage).execute();
				} else {
					TextMessage textMessage = new TextMessage("sorry, there is no similar sentence.");
					PushMessage pushMessage = new PushMessage(userId, textMessage);
					LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build().pushMessage(pushMessage).execute();
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

	public Sentence similarSentance(List<String> userEntities) {

		List<Sentence> sentences = new ArrayList<>();
		sentences = sentenceRepository.findAll();
		float maxSalience = 0;
		Sentence similarSentence = null;

		List<com.example.entity.Entity> entities = new ArrayList<>();
		float salience = 0;
		for (Sentence s : sentences) {
			entities = sentenceRelationRepository.getEntitiesOfSentence(s.getIdSentence());
			for (com.example.entity.Entity e : entities) {
				if (userEntities.contains(e.getNameEntity())) {
					salience = salience + e.getSalience();
				} else {
					salience = (float) (salience - 0.001);
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

}
