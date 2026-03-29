package com.etgenai.Hackathon.service;

import org.springframework.stereotype.Service;

@Service
public class AnalysisService {
    public String analyze(double price, double prevClose, double volume) {

        if(price == 0 || prevClose == 0) {
            return "Data not reliable currently. Try again later.";
        }

        if(price > prevClose * 1.02) {
            return "Breakout Detected";
        }
        else if(price < prevClose * 0.98) {
            return "Downtrend Risk";
        }

        return "Stable";
    }
}
