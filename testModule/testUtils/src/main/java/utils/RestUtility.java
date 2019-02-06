package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestUtility {

    public static Response postResponse(String url, String json) {

        return RestAssured.given()
                .header("Content-Type", "text/html; charset=utf-8")
                .header("Date", "Tue, 22 Jan 2019 22:43:52 GMT")
                .body(json)
                .post(url);
    }

    public static Response postResponse(String url, String username, String password, String body) {
        return RestAssured.given()
                .auth().basic(username,password)
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(body)
                .post(url + "/create");
    }

    public static Response postResponse(String url, String username, String password, String token, String body) {
        return RestAssured.given()
                .auth().basic(username,password)
                .header("Authorization", token)
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(body)
                .post(url + "/create");
    }

    public static Response getResponse(String url) {
        return RestAssured.given().header("Content-Type", "application/json; charset=UTF-8")
                .get(url + "/employees");
    }

    public static Response getResponse(String url, int id) {
        return RestAssured.given().header("Content-Type", "application/json; charset=UTF-8")
                .get(url + "/employee/" + id);
    }
}
