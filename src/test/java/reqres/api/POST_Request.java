package reqres.api;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class POST_Request {

	@Test
	void createUser() {
		
		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "Himanshu");
		jsonData.put("job", "QA");
		
		RestAssured.baseURI = "https://reqres.in/api/users/800";
		RestAssured.given().header("Content-Type","application/json").contentType(ContentType.JSON).body(jsonData.toJSONString())
		.when().post()
		.then().statusCode(201).log().all();
		
	}
}
