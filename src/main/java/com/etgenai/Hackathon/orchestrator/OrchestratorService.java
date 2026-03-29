package com.etgenai.Hackathon.orchestrator;

import com.etgenai.Hackathon.service.AIService;
import com.etgenai.Hackathon.service.AnalysisService;
import com.etgenai.Hackathon.service.DataService;
import com.etgenai.Hackathon.service.DecisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    public Map<String,String> handleUserQuery(String symbol){
        Map<String,Object> data = dataService.getStockData(symbol);
        Map<String, Object> globalQuote = (Map<String, Object>) data.get("Global Quote");

        Map<String, String> response = new HashMap<>();

        if(globalQuote == null || globalQuote.isEmpty()) {
            response.put("Error: ","Data cant be loaded");
            return response;
        }

        double price = Double.parseDouble((String) globalQuote.get("05. price"));
        double prevClose = Double.parseDouble((String) globalQuote.get("08. previous close"));
        double volume = Double.parseDouble((String) globalQuote.get("06. volume"));

        String signal = analysisService.analyze(price,prevClose,volume);

        String decision = decisionService.makeDecision(signal);

        String explanation = aiService.generateExplanation(symbol, price, prevClose, signal);


        response.put("stock", symbol);
        response.put("signal", signal);
        response.put("decision", decision);
        response.put("explanation", explanation);

        return response;
    }
}
