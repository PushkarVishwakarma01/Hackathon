package com.etgenai.Hackathon.service;

import com.etgenai.Hackathon.model.Alert;
import com.etgenai.Hackathon.orchestrator.OrchestratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlertScheduler {
    @Autowired
    private OrchestratorService orchestratorService;

    @Autowired
    private AlertService alertService;

    private List<String> watchlist = List.of("TCS", "RELIANCE", "INFY");

    @Scheduled(fixedRate = 60000)
    public void monitorStocks() {

        Map<String, Long> lastAlertTime = new HashMap<>();

        for (String symbol : watchlist) {

            long now = System.currentTimeMillis();
            long last = lastAlertTime.getOrDefault(symbol, 0L);

            if (now - last > 300000) {
                lastAlertTime.put(symbol, now);
            }

            Map<String, String> result = orchestratorService.handleUserQuery(symbol);

            if (result == null) continue;

            String signal = result.get("signal");
            String decision = result.get("decision");
            String explanation = result.get("explanation");

            if (signal == null) continue;

            if (signal.contains("Breakout") || signal.contains("Downtrend")) {

                Alert alert = new Alert();
                alert.setSymbol(symbol);
                alert.setSignal(signal);
                alert.setDecision(decision);
                alert.setMessage(explanation);
                alert.setTimestamp(System.currentTimeMillis());

                alertService.addAlert(alert);

                System.out.println("ALERT GENERATED: " + symbol);
            }
        }
    }
}
