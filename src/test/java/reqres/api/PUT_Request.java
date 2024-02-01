package reqres.api;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PUT_Request {

	@Test
	void createUser() {
		
		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "Himanshu");
		jsonData.put("job", "QA Automation");
		
		RestAssured.baseURI = "https://reqres.in/api/users/";
		RestAssured.given().queryParam("page", "800").header("Content-Type","application/json").contentType(ContentType.JSON).body(jsonData.toJSONString())
		.when().put()
		.then().statusCode(200).log().all();
		
	}
	
}
