package com.etgenai.Hackathon.service;

import org.springframework.stereotype.Service;

@Service
public class AnalysisService {
    public String analyze(double price, double prevClose, double volume) {
        if(price > prevClose * 1.02 && volume > 1000000)
        {
            return "Breakout Detected";

        } else if (price < prevClose * 0.98) {
            return "Downtrend Risk";

        }
        return "Stable";
    }
}
