package com.etgenai.Hackathon.service;

import com.etgenai.Hackathon.model.Alert;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlertService {
    private List<Alert> alerts = new ArrayList<>();

    public void addAlert(Alert alert) {
        alerts.add(alert);
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

}
