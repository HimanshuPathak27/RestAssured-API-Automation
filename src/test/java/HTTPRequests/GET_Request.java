package HTTPRequests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class GET_Request {

	@Test
	public void get_a_single_user() {
		
		String baseURI = "https://reqres.in";
		String basePath = "api/users/2";
		
		RequestSpecification requestSpec = RestAssured.given();
		requestSpec.baseUri(baseURI);
		requestSpec.basePath(basePath);
		
		Response response = requestSpec.get();
		
		//Validate Status Code
		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);
		Assert.assertEquals(statusCode, 200, "Failed | Satus Code is not correct");
		System.out.println();
		
		//Validate Status Line
		String statusLine = response.getStatusLine();
		System.out.println("Status Line: " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK", "Failed | Satus Line is not correct");
		System.out.println();
		
		//Get all headers and validate headers
		Headers headers = response.getHeaders();
		for(Header header : headers) {
			System.out.println(header.getName() + " => " + header.getValue());
		}
		System.out.println();
		
		String value_contentTypeHeader = response.getHeader("Content-Type");
		System.out.println("Value of Content Type header is: " + value_contentTypeHeader);
		Assert.assertEquals(value_contentTypeHeader, "application/json; charset=utf-8", "Failed | Value of Content Type header is not correct");
		
		String value_serverHeader = response.getHeader("Server");
		System.out.println("Value of Server header is: " + value_serverHeader);
		Assert.assertEquals(value_serverHeader, "cloudflare", "Failed | Value of Server header is not correct");
		System.out.println();
		
		//Validate Response Body
		ResponseBody<?> responseBody = response.getBody();
		String responseBodyString = responseBody.asPrettyString();
		System.out.println("ResponseBody: ");
		System.out.println(responseBodyString);
		System.out.println();
		
		JsonPath jsonPathView = responseBody.jsonPath();
		
		String firstName_user2 = jsonPathView.get("data.first_name");
		System.out.println("FirstName: " + firstName_user2);
		Assert.assertEquals(firstName_user2, "Janet", "Failed | First Name validation of id-2 Mismatched");
		Assert.assertEquals(firstName_user2.getClass(), String.class, "Failed | Data Type of First Name of id-2 Mismatched");
		
		String lastName_user2 = jsonPathView.get("data.last_name");
		Assert.assertEquals(lastName_user2, "Weaver", "Failed | Last Name validation of id-2 Mismatched");
		System.out.println("LastName: " + lastName_user2);
		Assert.assertEquals(lastName_user2.getClass(), String.class, "Failed | Data Type of Last Name of id-2 Mismatched");
		
		String email_user2 = jsonPathView.get("data.email");
		Assert.assertEquals(email_user2, "janet.weaver@reqres.in", "Failed | Email validation of id-2 Mismatched");
		System.out.println("Email: " + email_user2);
		Assert.assertEquals(email_user2.getClass(), String.class, "Failed | Data Type of Email of id-2 Mismatched");
		
	}
}
