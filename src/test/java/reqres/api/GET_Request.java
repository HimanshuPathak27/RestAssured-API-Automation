package reqres.api;

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

	@Test(priority=1)
	public void getSingleUser() {

		//Step-1 Specify path of URL
		String baseURI = "https://reqres.in";
		String basePath = "api/users/2";

		//Step-2 Get Request specification of the Request
		RequestSpecification requestSpec = RestAssured.given();
		requestSpec.baseUri(baseURI);
		requestSpec.basePath(basePath);

		//Step-3 Call GET method
		Response response = requestSpec.get();

		//Step-4 Retrieve and Validate Status code
		int statusCode = response.getStatusCode();
		System.out.println("Status code : " + statusCode);
		Assert.assertEquals(statusCode, 200, "Failed | Status Code Mismatched");
		System.out.println();

		//Step-5 Retrieve and Validate Status Line
		String statusLine = response.getStatusLine();
		System.out.println("Status Line : " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK", "Failed | Status Code Message Mismatched");
		System.out.println();

		//Step-6 Retrieve and Validate a single Header
		String contentTypeValue = response.getHeader("Content-Type");
		System.out.println("Value of Content-Type header is : " + contentTypeValue);
		Assert.assertEquals(contentTypeValue, "application/json; charset=utf-8", "Failed | Header: Content-type Mismatched");
		System.out.println();

		//Step-7 Retrieve list of Headers
		Headers headersList = response.getHeaders();
		for(Header header : headersList) {
			System.out.println(header.getName() + "  |  " + header.getValue());
		}
		System.out.println();

		//Step-8 Retrieve and Validate Response body
		ResponseBody<?> responseBody = response.getBody();
		String responseBodyString = responseBody.asPrettyString();
		System.out.println("Response Body: " + responseBodyString);
		System.out.println();

		JsonPath jsonPathView = responseBody.jsonPath();
		String firstName_user2 = jsonPathView.get("data.first_name");
		Assert.assertEquals(firstName_user2, "Janet", "Failed | First Name validation of id-2 Mismatched");
		
		String lastName_user2 = jsonPathView.get("data.last_name");
		Assert.assertEquals(lastName_user2, "Weaver", "Failed | Last Name validation of id-2 Mismatched");
		
		String email_user2 = jsonPathView.get("data.email");
		Assert.assertEquals(email_user2, "janet.weaver@reqres.in", "Failed | Email validation of id-2 Mismatched");
		
		System.out.println("**********************************");
	}
	

	@Test(priority=2)
	public void getListOfUser() {

		//Step-1 Specify path of URL
		String baseURI = "https://reqres.in";
		String basePath = "api/users";
		String queryParam = "page";

		//Step-2 Get Request specification of the Request
		RequestSpecification requestSpec = RestAssured.given();
		requestSpec.baseUri(baseURI);
		requestSpec.basePath(basePath);
		requestSpec.queryParam(queryParam, 2);

		//Step-3 Call GET method
		Response response = requestSpec.get();

		//Step-4 Retrieve and Validate Status code
		int statusCode = response.getStatusCode();
		System.out.println("Status code : " + statusCode);
		Assert.assertEquals(statusCode, 200, "Failed | Status Code Mismatched");
		System.out.println();

		//Step-5 Retrieve and Validate Status Line
		String statusLine = response.getStatusLine();
		System.out.println("Status Line : " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK", "Failed | Status Code Message Mismatched");
		System.out.println();

		//Step-6 Retrieve and Validate a single Header
		String contentTypeValue = response.getHeader("Content-Type");
		System.out.println("Value of Content-Type header is : " + contentTypeValue);
		Assert.assertEquals(contentTypeValue, "application/json; charset=utf-8", "Failed | Header: Content-type Mismatched");
		System.out.println();

		//Step-7 Retrieve list of Headers
		Headers headersList = response.getHeaders();
		for(Header header : headersList) {
			System.out.println(header.getName() + "  |  " + header.getValue());
		}
		System.out.println();
		
		//Step-8 Retrieve and Validate Response body
		ResponseBody<?> responseBody = response.getBody();
		String responseBodyString = responseBody.asPrettyString();
		System.out.println("Response Body: " + responseBodyString);
		System.out.println();

		JsonPath jsonPathView = responseBody.jsonPath();
		String firsName_user9 = jsonPathView.get("data[2].first_name");
		Assert.assertEquals(firsName_user9, "Tobias", "Failed | First Name validation of id-9 Mismatched");
		
		String firsName_user12 = jsonPathView.get("data[5].first_name");
		Assert.assertEquals(firsName_user12, "Rachel", "Failed | First Name validation of id-12 Mismatched");
		
		System.out.println("**********************************");
	}
	
	@Test(priority=3)
	public void basicAuthentication() {
		String baseURI = "https://postman-echo.com";
		String basePath = "basic-auth";
		
		RequestSpecification requestSpec = RestAssured.given();
		requestSpec.baseUri(baseURI);
		requestSpec.basePath(basePath);
		
		Response response = requestSpec.get();
		
		int statusCode_beforeAuth = response.getStatusCode();
		System.out.println("Status code Before Authentication: " + statusCode_beforeAuth);
		Assert.assertEquals(statusCode_beforeAuth, 401, "Failed | Status Code Mismatched");

		String statusLine_beforeAuth = response.getStatusLine();
		System.out.println("Status Line Before Authentication: " + statusLine_beforeAuth);
		Assert.assertEquals(statusLine_beforeAuth, "HTTP/1.1 401 Unauthorized", "Failed | Status Code Message Mismatched");
		
		String responseBody_beforeAuth = response.getBody().asPrettyString();
		System.out.println("Response Body Before Authentication : " + responseBody_beforeAuth);

		System.out.println();
		
		response = requestSpec.auth().preemptive().basic("postman", "password").get();
		int statusCode_afterAuth = response.getStatusCode();
		System.out.println("Status code After Authentication: " + statusCode_afterAuth);
		Assert.assertEquals(statusCode_afterAuth, 200, "Failed | Status Code Mismatched");

		String statusLine_afterAuth = response.getStatusLine();
		System.out.println("Status Line After Authentication: " + statusLine_afterAuth);
		Assert.assertEquals(statusLine_afterAuth, "HTTP/1.1 200 OK", "Failed | Status Code Message Mismatched");
		
		String responseBody_afterAuth = response.getBody().asPrettyString();
		System.out.println("Response Body After Authentication : " + responseBody_afterAuth);
		
		System.out.println("**********************************");
		
	}
	
	@Test(priority=4)
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
		
		System.out.println("**********************************");
		
	}
	
}