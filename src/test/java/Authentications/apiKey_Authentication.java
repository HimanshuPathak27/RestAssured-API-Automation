package Authentications;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class apiKey_Authentication {
	
	@Test
	public void apiKeyAuthentication() {
		String baseURI = "https://api.openweathermap.org";
		String basePath = "data/2.5/weather";

		RequestSpecification requestSpec = RestAssured.given();
		requestSpec.baseUri(baseURI);
		requestSpec.basePath(basePath);
		requestSpec.queryParam("lat", 20).queryParam("lon", 30).queryParam("appid", "A P I ----- K E Y");

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
