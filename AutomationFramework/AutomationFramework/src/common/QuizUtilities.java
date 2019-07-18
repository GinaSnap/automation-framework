package common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class QuizUtilities {
	
	
	public String getQuizJson() {
		Response response = 
				RestAssured.given().header("Api-Token","999e8106-417f-4687-a140-4f61a83ec9e4"). //Staging
//				RestAssured.given().header("Api-Token","e2b8cf38-7b3a-4201-800b-975ff1b17b3c"). //Production
				when().
				        get("https://api-stage.snapkitchen.com/v1/quiz_questions").
//				        get("https://api.snapkitchen.com/v1/products").

				then().
				        statusCode(200).
				extract().
				        response(); 
		
		return response.asString();
		}

	public List<String> getQuizSections(){
		ObjectMapper objectMapper = new ObjectMapper();
		List<String> quizSections = new ArrayList<String>();
		
		String quizJson = getQuizJson();
		try {
			JsonNode jsonNode = objectMapper.readTree(quizJson).get("data");
			for (JsonNode quizSectionNode : jsonNode)
			{
				quizSections.add(quizSectionNode.get("id").asText());
			}
			
		}
		catch (IOException e) {
			//Just return the empty list
		}
		return quizSections;
		
	}
	
	public Map<String,String> getQuizQuestions(String quizSection){
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String,String> questionsMap = new LinkedHashMap<String,String>();
		
		String quizJson = getQuizJson();
		try {
			JsonNode jsonNode = objectMapper.readTree(quizJson).get("data");
			for (JsonNode quizSectionNode : jsonNode)
			{
				if (quizSectionNode.get("id").asText().equals(quizSection)){
					JsonNode questionsNode = quizSectionNode.get("screens");
					for (JsonNode questionNode : questionsNode) {
						questionsMap.put(questionNode.get("title").asText(), questionNode.get("type").asText());
					}
				}
			}
		}
		catch (IOException e) {
			//Just return the empty list
		}
		return questionsMap;
	}
	
	public List<String> getAnswersByQuestion(String question){
		ObjectMapper objectMapper = new ObjectMapper();
		List<String> answerList = new ArrayList<String>();
		
		String quizJson = getQuizJson();
		try {
			JsonNode jsonNode = objectMapper.readTree(quizJson).get("data");
			for (JsonNode quizSectionNode : jsonNode)
			{
				JsonNode questionsNode = quizSectionNode.get("screens");
				for (JsonNode questionNode : questionsNode) {
					if (questionNode.get("title").asText().equals(question)) {
						JsonNode answerListNode = questionNode.get("answers");
						for (JsonNode answerNode : answerListNode) {
							answerList.add(answerNode.get("title").asText());
						}
					}
				}
			}
		}
		catch (IOException e) {
			//Just return the empty list
		}
		return answerList;
	}
//		ObjectMapper objectMapper = new ObjectMapper();
//		JsonNode jsonNode = objectMapper.readTree(response.asString()).get("data");
//		for (JsonNode quizSectionNode : jsonNode)
//		{
//				System.out.println(quizSectionNode.get("id").asText());
//				System.out.println(quizSectionNode.get("title").asText());
//				JsonNode listQuestionsNode = quizSectionNode.get("screens");
//				for (JsonNode questionNode : listQuestionsNode) {
//					System.out.println(questionNode.get("title").asText());
//					System.out.println(questionNode.get("type").asText());
//					if (questionNode.get("type").asText().equals("single_select")) {
//						System.out.println("Has Answers");
//						JsonNode listAnswersNode = questionNode.get("answers");
//						for (JsonNode answerNode : listAnswersNode) {
//							System.out.println(answerNode.get("title").asText());
//						}
//					}
//					else {
//						System.out.println("Has no answers");
//					}
//				}
//		}
//	}
}
