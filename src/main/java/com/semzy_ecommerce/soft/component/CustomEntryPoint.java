package com.semzy_ecommerce.soft.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
        Map<String, Object> jsonBuilder = new HashMap<>();
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(403);
        jsonBuilder.put("timestamp", System.currentTimeMillis());
        jsonBuilder.put("status", 403);
        jsonBuilder.put("message", "Please login to add to cart");

        // Convert the Map to a JSON string using Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(jsonBuilder);

        res.getWriter().write(jsonResponse);
    }
}
