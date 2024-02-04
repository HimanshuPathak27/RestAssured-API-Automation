package Authentications;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class bearerToken_Authentication {

	@Test
	public void bearerTokenAuthentication() {
		String baseURI = "https://gorest.co.in";
		String basePath = "public/v2/users";
		String authToken = "Bearer 258500282ef1a0651eae3def4e9876292141b1e71a9d26f334f5b092e5022ce4";

		RequestSpecification requestSpec = RestAssured.given();
		requestSpec.baseUri(baseURI);
		requestSpec.basePath(basePath);
		requestSpec.header("Authorization", authToken);

		Response response = requestSpec.get();

		int statusCode = response.getStatusCode();
		System.out.println("Status code : " + statusCode);
		Assert.assertEquals(statusCode, 200, "Failed | Status Code Mismatched");

		String statusLine = response.getStatusLine();
		System.out.println("Status Line Before Authentication: " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK", "Failed | Status Code Message Mismatched");

		String responseBody = response.getBody().asPrettyString();
		System.out.println("Response Body : " + responseBody);

	}
}
