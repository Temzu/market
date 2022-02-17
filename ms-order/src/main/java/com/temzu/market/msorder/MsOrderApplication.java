package com.temzu.market.msorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.temzu.market")
@SpringBootApplication(scanBasePackages = "com.temzu.market")
public class MsOrderApplication {

  public static void main(String[] args) {
    SpringApplication.run(MsOrderApplication.class, args);
  }

}
