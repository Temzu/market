package com.temzu.market.msorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.temzu.market")
public class MsOrderApplication {

  public static void main(String[] args) {
    SpringApplication.run(MsOrderApplication.class, args);
  }

}
