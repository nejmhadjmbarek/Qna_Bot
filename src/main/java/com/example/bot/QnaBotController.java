package com.example.bot;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.example.com.EntityRepository;
import com.example.com.SentenceRepository;
import com.example.entity.Sentence;
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
				List<Entity> entities = analyzeEntitiesText(customerMessage);

			} catch (Exception e) {
				// TODO Auto-generated catch block
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

//	public Sentence similarSentance(List<Entity> userEntities) {
//
//		List<Sentence> sentences = new ArrayList<>();
//		sentences = sentenceRepository.findAll();
//		List<com.example.entity.Entity> entities = new ArrayList<>();
//		for (Sentence s : sentences) {
//			entities = entityRepository.getEntitiesOfSentence(s.getIdSentence());
//
//		}
//	}

}
