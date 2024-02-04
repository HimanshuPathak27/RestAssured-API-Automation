package Authentications;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class digest_Authentication {

	@Test
	public void digestAuthentication() {
		String baseURI = "https://httpbin.org";
		String basePath = "digest-auth/undefined/test_username/test_password";

		RequestSpecification requestSpec = RestAssured.given();
		requestSpec.baseUri(baseURI);
		requestSpec.basePath(basePath);

		Response response = requestSpec.get();

		int statusCode_beforeAuth = response.getStatusCode();
		System.out.println("Status code Before Authentication: " + statusCode_beforeAuth);
		Assert.assertEquals(statusCode_beforeAuth, 401, "Failed | Status Code Mismatched");

		String statusLine_beforeAuth = response.getStatusLine();
		System.out.println("Status Line Before Authentication: " + statusLine_beforeAuth);
		Assert.assertEquals(statusLine_beforeAuth, "HTTP/1.1 401 UNAUTHORIZED", "Failed | Status Code Message Mismatched");

		String responseBody_beforeAuth = response.getBody().asPrettyString();
		System.out.println("Response Body Before Authentication : " + responseBody_beforeAuth);

		System.out.println();

		response = requestSpec.auth().digest("test_username", "test_password").get();
		int statusCode_afterAuth = response.getStatusCode();
		System.out.println("Status code After Authentication: " + statusCode_afterAuth);
		Assert.assertEquals(statusCode_afterAuth, 200, "Failed | Status Code Mismatched");

		String statusLine_afterAuth = response.getStatusLine();
		System.out.println("Status Line After Authentication: " + statusLine_afterAuth);
		Assert.assertEquals(statusLine_afterAuth, "HTTP/1.1 200 OK", "Failed | Status Code Message Mismatched");

		String responseBody_afterAuth = response.getBody().asPrettyString();
		System.out.println("Response Body After Authentication : " + responseBody_afterAuth);

	}
}
