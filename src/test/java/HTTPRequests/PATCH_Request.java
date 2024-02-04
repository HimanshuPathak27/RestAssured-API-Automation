package HTTPRequests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class PATCH_Request {

	@Test
	public void update_a_user() {

		String baseURI = "https://reqres.in";
		String basePath = "api/users/2";

		RequestSpecification requestSpec = RestAssured.given();
		requestSpec.baseUri(baseURI);
		requestSpec.basePath(basePath);

		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "Himanshu Pathak");
		jsonData.put("job", "QA Engineer");

		requestSpec.contentType(ContentType.JSON).body(jsonData.toJSONString());
		Response response = requestSpec.patch();

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

		String name = jsonPathView.get("name");
		System.out.println("Name: " + name);
		Assert.assertEquals(name, "Himanshu Pathak", "Failed | Name Mismatched");
		Assert.assertEquals(name.getClass(), String.class, "Failed | Data Type of Name Mismatched");

		String job = jsonPathView.get("job");
		Assert.assertEquals(job, "QA Engineer", "Failed | Job Mismatched");
		System.out.println("Job: " + job);
		Assert.assertEquals(job.getClass(), String.class, "Failed | Data Type of Job Mismatched");

	}
}