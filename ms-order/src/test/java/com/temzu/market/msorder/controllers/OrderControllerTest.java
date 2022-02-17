package com.temzu.market.msorder.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

import com.temzu.market.corelib.services.TokenService;
import com.temzu.market.routinglib.dtos.OrderDto;
import com.temzu.market.routinglib.dtos.SignUpRequestDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class OrderControllerTest {

  @Autowired
  private ServletWebServerApplicationContext webServerAppCtxt;

  @MockBean
  private TokenService tokenService;

  @BeforeEach
  public void setUp() {
    RestAssured.port = webServerAppCtxt.getWebServer().getPort();
  }

  @CsvSource({
      "Token_1,  1",
      "Token_2,  2",
      "Token_3', 3",
      "Token_4,  4"
  })
  @ParameterizedTest
  void findPageByCurrentUser(String token, Long userId) {
    Mockito.when(tokenService.getUserId(token)).thenReturn(userId);

    Response pageResponse = given()
        .header("AUTHORIZATION", token)
        .when()
        .get("/api/v1/orders/current")
        .then().extract().response();

    List<OrderDto> orderDtos = pageResponse.jsonPath().getList("content", OrderDto.class);

    assertNotNull(orderDtos);
    assertFalse(orderDtos.isEmpty());
  }
}