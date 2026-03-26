package com.etgenai.Hackathon.service;

import org.springframework.stereotype.Service;

@Service
public class AIService {
    public String generateExplanation(String signal) {
        if (signal.contains("Breakout"))
        {
            return "Stock is showing strong upward movement with high demand";
        }
        if(signal.contains("Downtrend"))
        {
            return "Stock is declining and may continue downward";

        }
        return "Stock is stable with no strong signal";
    }
}
