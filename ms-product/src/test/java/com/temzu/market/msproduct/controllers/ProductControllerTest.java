package com.temzu.market.msproduct.controllers;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ProductControllerTest {

  @BeforeEach
  public void setUp() {
    RestAssured.baseURI = "http://localhost:8189/api/v1";
    RestAssured.port = 8189;
  }

  @Test
  void findPage_CORRECT() {
    get("/products").then().body("numberOfElements", equalTo(10));
    get("/products").then().body("totalElements", equalTo(17));
  }

  @Test
  void findPage_CORRECT_WRONG() {
//    get("/products").then().body("numberOfElements", equalTo(10));
//    get("/products").then().body("totalElements", equalTo(17));
  }
}