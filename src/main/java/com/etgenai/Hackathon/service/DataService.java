package com.etgenai.Hackathon.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
@Service
public class DataService    {

    @Value("${alphavantage.api.key}")
    private String apiKey;

    @Value("${twelvedata.api.key}")
    private String apiKey2;

    @Value("${alphavantage.base.url}")
    private String baseUrl;

    public Map<String, Object> getStockData(String symbol) {

        String url = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol="
                + symbol + "&apikey=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        try{
            return restTemplate.getForObject(url, Map.class);

        }
        catch (Exception e){
            return null;

        }
    }

    public Map<String, Object> getStockHistory(String symbol) {

        String url = "https://api.twelvedata.com/time_series"
                + "?symbol=" + symbol
                + "&interval=5min"
                + "&outputsize=10"
                + "&apikey=" + apiKey2;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Map.class);
    }
}
