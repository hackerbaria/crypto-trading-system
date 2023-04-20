package com.example.cryptotradingsystem.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalPriceService {

    private final RestTemplate restTemplate;

    public ExternalPriceService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> ResponseEntity<T> getPriceData(String url, Class<T> responseType) {
        return restTemplate.exchange(url, HttpMethod.GET, null, responseType);
    }
}
