package com.etgenai.Hackathon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrchestratorService {
    @Autowired
    private DataService dataService;
    @Autowired
    private AnalysisService analysisService;
    @Autowired
    private DecisionService decisionService;
    @Autowired
    private AIService aiService;

    public String handleUserQuery(String symbol){
        Map<String,Object> data = dataService.getStockData(symbol);

        double price = 100;
        double prevClose = 98;
        double volume = 1200000;

        String signal = analysisService.analyze(price,prevClose,volume);

        String decision = decisionService.makeDecision(signal);

        String explanation = aiService.generateExplanation(signal);

        return "Stock : " + symbol +
                "\n Signal : " + signal +
                "\n Decision : " + decision +
                "\n Explanation : " + explanation;
    }
}
