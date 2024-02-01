package reqres.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GET_Request {


	@Test
	void getSingleUser() {

		//Step-1 Specify Base URL
		RestAssured.baseURI = "https://reqres.in/api/users/2";

		//Step-2 Get Request specification of the Request
		RequestSpecification requestSpec = RestAssured.given();

		//Step-3 Call GET method
		Response response = requestSpec.get();

		//Step-4 Retrieve and Validate Status code
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200, "Status Code Mismatched");

		//Step-4 Retrieve and Validate Status code
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK", "Status Code Message Mismatched");

	}

	@Test
	void getSingleUser_BDDStyle() {

		RestAssured
		.given()

		.when()
		.get("https://reqres.in/api/users/2")

		.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK");

	}

}