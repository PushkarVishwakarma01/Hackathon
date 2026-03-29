package com.etgenai.Hackathon.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


@Service
public class AIService {

    @Value("${gemini.api.key}")
    private String apiKey;

//    private final String URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=";
    private final String URL =
            "https://generativelanguage.googleapis.com/v1/models/gemini-2.5-flash-lite:generateContent?key=";
    public String generateExplanation(String symbol, double price, double prevClose, String signal) {

        RestTemplate restTemplate = new RestTemplate();

        String prompt = buildPrompt(symbol, price, prevClose, signal);

        Map<String, Object> request = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    URL + apiKey,
                    entity,
                    Map.class
            );

            List candidates = (List) response.getBody().get("candidates");
            Map first = (Map) candidates.get(0);
            Map content = (Map) first.get("content");
            List parts = (List) content.get("parts");
            Map textPart = (Map) parts.get(0);

            return (String) textPart.get("text");

        } catch (Exception e) {
            e.printStackTrace();
            return "AI explanation unavailable.";
        }
    }

    private String buildPrompt(String symbol, double price, double prevClose, String signal) {

        return "You are a professional financial AI assistant.\n" +
                "Analyze the stock and give output in EXACT format:\n\n" +

                "📈 Insight:\n" +
                "💡 Recommendation:\n" +
                "⚠️ Risk:\n" +
                "📊 Confidence:\n\n" +

                "Rules:\n" +
                "- Keep it under 60 words\n" +
                "- No extra text\n" +
                "- Be concise and clear\n\n" +

                "Stock: " + symbol + "\n" +
                "Price: " + price + "\n" +
                "Previous Close: " + prevClose + "\n" +
                "Signal: " + signal;
    }
}







//@Service
//public class AIService {
//
//    public String generateExplanation(String signal) {
//        if (signal.contains("Breakout"))
//        {
//            return "Stock is showing strong upward movement with high demand";
//        }
//        if(signal.contains("Downtrend"))
//        {
//            return "Stock is declining and may continue downward";
//
//        }
//        return "Stock is stable with no strong signal";
//    }
//}
