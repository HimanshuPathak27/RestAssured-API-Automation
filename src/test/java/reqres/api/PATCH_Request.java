package reqres.api;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PATCH_Request {

	@Test
	void createUser() {
		
		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "Himanshu Pathak");
		jsonData.put("job", "QA Automation");
		
		RestAssured.baseURI = "https://reqres.in/api/users/";
		RestAssured.given().queryParam("page", "800").header("Content-Type","application/json").contentType(ContentType.JSON).body(jsonData.toJSONString())
		.when().patch()
		.then().statusCode(200).log().all();
		
	}
	
}
