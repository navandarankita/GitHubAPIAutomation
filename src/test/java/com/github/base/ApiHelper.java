package com.github.base;

import com.github.utils.EnvironmentDetails;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiHelper {

	RequestSpecification reqSpec;

	public ApiHelper() {
		RestAssured.baseURI = EnvironmentDetails.getProperty("baseURL");
		reqSpec = RestAssured.given();
	}

	private RequestSpecification getRequestSpec() {
		return RestAssured.given().header("Authorization", "Bearer " + EnvironmentDetails.getProperty("authToken"))
				.contentType("application/json");
	}

	public Response sendGetRequest(String endpoint) {
		return getRequestSpec().get(endpoint).then().extract().response();
	}

	public Response sendPostRequest(String endpoint, Object body) {
		return getRequestSpec().body(body).post(endpoint).then().extract().response();
	}

	public Response sendPatchRequest(String endpoint, Object body) {
		return getRequestSpec().body(body).patch(endpoint).then().extract().response();
	}

	public Response sendDeleteRequest(String endpoint) {
		return getRequestSpec().delete(endpoint).then().extract().response();
	}
}
