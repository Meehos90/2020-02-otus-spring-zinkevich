package ru.otus.spring.actuators;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class CustomHealth {
    private Map<String, Object> healthDetails;

}
