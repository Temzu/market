package com.temzu.market.msauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.temzu.market")
public class MsAuthApplication {

  public static void main(String[] args) {
    SpringApplication.run(MsAuthApplication.class, args);
  }

}
