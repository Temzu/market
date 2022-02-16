package com.temzu.market.msproduct.controllers;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class ProductControllerTest {

  @Autowired
  private ServletWebServerApplicationContext webServerAppCtxt;

  @BeforeEach
  public void setUp() {
    RestAssured.port = webServerAppCtxt.getWebServer().getPort();
  }

  @Test
  void findPage_CORRECT() {
    get("/api/v1/products").then().body("numberOfElements", equalTo(10));
    get("/api/v1/products").then().body("totalElements", equalTo(17));
  }

  @Test
  void findPage_CORRECT_WRONG() {
//    get("/products").then().body("numberOfElements", equalTo(10));
//    get("/products").then().body("totalElements", equalTo(17));
  }
}