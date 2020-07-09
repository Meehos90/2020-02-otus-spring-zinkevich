package ru.otus.spring.actuators;

import lombok.Data;

import java.util.Map;

@Data
public class CustomHealth {
    private Map<String, Object> healthDetails;
}
