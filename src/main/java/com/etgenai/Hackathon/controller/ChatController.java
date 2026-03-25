package com.etgenai.Hackathon.controller;


import com.etgenai.Hackathon.service.OrchestratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {
    @Autowired
    private OrchestratorService orchestratorService;

    @GetMapping("/analyze")
    public String analyzeStock(@RequestParam String symbol){
        return orchestratorService.handleUserQuery(symbol);
    }
}
