package com.sistemasdistribuidos.chatcolegio.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MessageStatus {
    SENT("sent"),
    DELIVERED("delivered"),
    READ("read");

    private final String valorJson;

    
    MessageStatus(String valorJson) {
        this.valorJson = valorJson;
    }

    @JsonValue 
    public String getValorJson() {
        return valorJson;
    }
}