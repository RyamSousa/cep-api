package com.demo.cepapi;

import com.demo.cepapi.config.FreightProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties(FreightProperties.class)
public class CepApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CepApiApplication.class, args);
    }

}
