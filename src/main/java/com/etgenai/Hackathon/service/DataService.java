package com.etgenai.Hackathon.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
@Service
public class DataService    {
    public Map<String, Object> getStockData(String symbol) {

        String url = "https://query1.finance.yahoo.com/v7/finance/quote?symbols=" + symbol;
        RestTemplate restTemplate = new RestTemplate();
        try{
            return restTemplate.getForObject(url, Map.class);

        }
        catch (Exception e){
            return null;

        }
    }
}
