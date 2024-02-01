package reqres.api;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class DELETE_Request {

	@Test
	void createUser() {
		
		RestAssured.baseURI = "https://reqres.in/api/users/";
		RestAssured.given().queryParam("page", "800")
		.when().delete()
		.then().statusCode(204).log().all();
		
	}
	
}
