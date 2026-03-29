package com.etgenai.Hackathon.controller;


import com.etgenai.Hackathon.service.DataService;
import com.etgenai.Hackathon.orchestrator.OrchestratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class ChatController {
    @Autowired
    private OrchestratorService orchestratorService;

    @Autowired
    private DataService dataService;

    @GetMapping("/analyze")
    public Map<String, String> analyzeStock(@RequestParam String symbol){
        return orchestratorService.handleUserQuery(symbol);
    }

    @GetMapping("/chart")
    public List<Double> getChart(@RequestParam String symbol) {

        try {
            Map<String, Object> data = dataService.getStockHistory(symbol);

            if (data.containsKey("status") && data.get("status").equals("error")) {
                System.out.println("API limit or error");
                return List.of();
            }

            if (data == null || !data.containsKey("values")) {
                return List.of();
            }

            List<Map<String, String>> values =
                    (List<Map<String, String>>) data.get("values");

            List<Double> prices = new ArrayList<>();

            for (Map<String, String> point : values) {
                if (point.get("close") != null) {
                    prices.add(Double.parseDouble(point.get("close")));
                }
            }

            return prices;

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
