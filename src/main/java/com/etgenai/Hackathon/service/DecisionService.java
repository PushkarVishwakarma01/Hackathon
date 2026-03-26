package com.etgenai.Hackathon.service;

import org.springframework.stereotype.Service;

@Service
public class DecisionService {
    public String makeDecision(String signal) {
        if(signal.contains("Breakout")){
            return "Consider Buying ";

        } else if (signal.contains("Downtrend")) {
            return "Avoid / Sell";

        }
        return "Hold";
    }
}
