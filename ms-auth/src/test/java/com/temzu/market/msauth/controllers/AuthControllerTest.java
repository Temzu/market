package com.temzu.market.msauth.controllers;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

import com.temzu.market.routinglib.dtos.AuthRequestDto;
import com.temzu.market.routinglib.dtos.SignUpRequestDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
class AuthControllerTest {

  @Autowired
  private ServletWebServerApplicationContext webServerAppCtxt;

  @BeforeEach
  public void setUp() {
    RestAssured.port = webServerAppCtxt.getWebServer().getPort();
  }

  @Order(1)
  @CsvSource(value = {"ivan1", "petr2", "andrey3"})
  @ParameterizedTest
  void signUp(String login) {
    given()
        .contentType(ContentType.JSON)
        .body(new SignUpRequestDto(login, "123"))
        .when()
        .post("/api/v1/auth/signup")
        .then()
        .body(containsString("Bearer "));
  }

  @Order(2)
  @CsvSource(value = {"ivan", "petr", "andrey"})
  @ParameterizedTest
  void login(String login) {
    Response response = given()
        .contentType(ContentType.JSON)
        .body(new AuthRequestDto(login, "123"))
        .when()
        .post("/api/v1/auth/login")
        .then()
        .body(containsString("Bearer "))
        .extract().response();

    assertFalse(response.jsonPath().getString("token").isBlank());
  }
}