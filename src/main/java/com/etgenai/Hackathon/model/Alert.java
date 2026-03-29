package com.etgenai.Hackathon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alert {
    private String symbol;
    private String signal;
    private String decision;
    private String message;
    private long timestamp;
}
