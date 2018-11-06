package common;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SnapAPIUtilities {
	
	List<String> listExpectedImageTypes = new ArrayList<String>();
	StringBuilder missingImagesDetail = new StringBuilder();
	
	private final String PRODUCT_HTML_FILE = "/Users/GMitchell/git/automation-framework/OutputFiles/products.html";
	private final String MISSING_IMAGES_DETAIL_FILE = "/Users/GMitchell/git/automation-framework/OutputFiles/missing_images_detail.txt";
	
	public void getProductJson() throws IOException
	{
		int count=1;
		StringBuilder sb = new StringBuilder();
		sb.append("<html><body>");
		sb.append("<table>");
		
		addValidImageTypes();

		Response response = 
//				RestAssured.given().header("Api-Token","999e8106-417f-4687-a140-4f61a83ec9e4"). //Staging
				RestAssured.given().header("Api-Token","e2b8cf38-7b3a-4201-800b-975ff1b17b3c"). //Production
				when().
//				        get("https://api-stage.snapkitchen.com/v1/products").
				        get("https://api.snapkitchen.com/v1/products").

				then().
				        statusCode(200).
				extract().
				        response(); 
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(response.asString()).get("data");
		for (JsonNode productNode : jsonNode)
		{
//			if (count < 5)
//			{
				insertProductNameCell(sb, productNode);
				insertImageCountCell(sb, productNode.get("photos"));
				insertProductImageCells(sb, productNode.get("photos"));
				
				missingImagesDetail.append("Testing: " + productNode.get("name") + "\n");
				List<String> imageTypeList = getProductImageTypes(productNode.get("photos"));
				verifyImageTypes(imageTypeList);
//			}
//			count++;
		}
		
		
		sb.append("</table>");
		sb.append("</body></html>");
		writeFile(sb, PRODUCT_HTML_FILE);
		writeFile(missingImagesDetail, MISSING_IMAGES_DETAIL_FILE);
	}
	
	private void insertImageCountCell(StringBuilder sb, JsonNode jsonNode)
	{
		sb.append("<tr><td>");
		sb.append("Total Image Count: " + jsonNode.size());
		sb.append("</td></tr>");
	}

	private void insertProductNameCell(StringBuilder sb, JsonNode jsonNode) throws IOException {
		
		sb.append("<tr><td>");
		sb.append(jsonNode.get("name").asText());
		sb.append("</td></tr>");

	}
	
	private void insertProductImageCells(StringBuilder sb, JsonNode jsonNode) throws IOException {
		int count=0;
		for (JsonNode productImageNode : jsonNode)
		{	
			if (count%5==0)
			{
				sb.append("<tr>");
			}
			
			sb.append("<td>");
			
			sb.append("<table border=\"1\">");
			sb.append("<tr><td>");
			
			sb.append(productImageNode.get("type").asText());
			
			sb.append("</td></tr>");

			sb.append("<tr><td>");
			
			sb.append("<img src=\"https://");
			sb.append(productImageNode.get("base_url").asText() + productImageNode.get("extension").asText());
			sb.append("\" height=\"250\" width=\"250\">");
			
			sb.append("</td></tr></table>");
			
			sb.append("</td>");
			
			if ((count+1)%5==0)
			{
				sb.append("</tr>");
			}
			count++;

		}
		
	}
	
	private List<String> getProductImageTypes(JsonNode jsonNode) {
		
		List<String> imageList = new ArrayList<String>();
		for (JsonNode imageNode : jsonNode)
		{
			imageList.add(imageNode.get("type").asText());
		}
		return imageList;
	}
	
	private void verifyImageTypes(List<String> listImageTypes)
	{
		for (String imageType : listExpectedImageTypes)
		{
			if (!listImageTypes.contains(imageType))
			{
				missingImagesDetail.append("**MISSING** " + imageType + "\n");
			}
		}
	}
	
	private void writeFile(StringBuilder sb, String fileName) throws IOException
	{
		FileWriter outputFile = null;
		try
		{
			outputFile = new FileWriter(fileName);
			outputFile.write(sb.toString());
			outputFile.close();
		}
		finally
		{
			if (outputFile != null)
			{
				outputFile.close();
			}
		}
	}
	
	private void addValidImageTypes()
	{
		//product basket photos (ios only)
		listExpectedImageTypes.add("product_basket_photo_ios_x");
		listExpectedImageTypes.add("product_basket_photo_ios");
		listExpectedImageTypes.add("product_basket_photo_ios_plus");
		listExpectedImageTypes.add("product_basket_photo_ios_se");
		
		//product list photos (iOS uses these for both list and grid)
		listExpectedImageTypes.add("product_list_photo_ios_plus");
		listExpectedImageTypes.add("product_list_photo_ios");
		listExpectedImageTypes.add("product_list_photo_ios_se");
		listExpectedImageTypes.add("product_list_photo_ios_x");
		
		//product detail photos
		listExpectedImageTypes.add("product_photo_ios_plus");
		listExpectedImageTypes.add("product_photo_ios_se");
		listExpectedImageTypes.add("product_photo_web");
		listExpectedImageTypes.add("product_photo_ios_x");
		listExpectedImageTypes.add("product_photo_ios");
		
		//This is not currently used.
//		listExpectedImageTypes.add("product_share_photo_small");
//		listExpectedImageTypes.add("product_share_photo_medium");
//		listExpectedImageTypes.add("product_share_photo_large");
		
//		//product detail backgrounds
//		listExpectedImageTypes.add("product_background_photo_ios");
//		listExpectedImageTypes.add("product_background_photo_ios_se");
//		listExpectedImageTypes.add("product_background_photo_ios_x");
//		listExpectedImageTypes.add("product_background_photo_ios_plus");
//
//		//product list/grid backgrounds (ios only)
//		listExpectedImageTypes.add("product_list_background_photo_ios");
//		listExpectedImageTypes.add("product_list_background_photo_ios_plus");
//		listExpectedImageTypes.add("product_list_background_photo_ios_x");
//		listExpectedImageTypes.add("product_list_background_photo_ios_se");

	}

}
