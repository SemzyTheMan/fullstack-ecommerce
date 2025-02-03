package com.semzy_ecommerce.soft.dtos.response;

import java.time.LocalDateTime;

public class CustomResponse {
    private boolean success;
    private String message;
    private LocalDateTime timestamp;
    private Object data;

    public CustomResponse(boolean success, String message,  Object data) {
        this.success = success;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
