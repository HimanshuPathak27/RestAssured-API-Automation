package HTTPRequests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DELETE_Request {

	@Test
	public void delete_a_single_user() {
		
		String baseURI = "https://reqres.in";
		String basePath = "api/users/2";
		
		RequestSpecification requestSpec = RestAssured.given();
		requestSpec.baseUri(baseURI);
		requestSpec.basePath(basePath);
		
		Response response = requestSpec.delete();
		
		//Validate Status Code
		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);
		Assert.assertEquals(statusCode, 204, "Failed | Satus Code is not correct");
		System.out.println();
		
		//Validate Status Line
		String statusLine = response.getStatusLine();
		System.out.println("Status Line: " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 204 No Content", "Failed | Satus Line is not correct");
		System.out.println();
		
		//Get all headers and validate headers
		Headers headers = response.getHeaders();
		for(Header header : headers) {
			System.out.println(header.getName() + " => " + header.getValue());
		}
		System.out.println();

		
		String value_serverHeader = response.getHeader("Server");
		System.out.println("Value of Server header is: " + value_serverHeader);
		Assert.assertEquals(value_serverHeader, "cloudflare", "Failed | Value of Server header is not correct");
		System.out.println();
	
	}
}
