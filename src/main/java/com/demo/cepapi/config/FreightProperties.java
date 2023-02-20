package com.demo.cepapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Setter
@Configuration
@ConfigurationProperties(prefix = "freight")
public class FreightProperties {

    private Map<String, String> freightValues;

    private String sudeste;
    private String centroOeste;
    private String nordeste;
    private String sul;
    private String norte;

    @PostConstruct
    private void init() {
        this.freightValues = new HashMap<>();
        this.freightValues.put("Sudeste", sudeste);
        this.freightValues.put("Centro-Oeste", centroOeste);
        this.freightValues.put("Nordeste", nordeste);
        this.freightValues.put("Sul", sul);
        this.freightValues.put("Norte", norte);
    }

    public Map<String, String> getFreightValues() {
        return freightValues;
    }
}
