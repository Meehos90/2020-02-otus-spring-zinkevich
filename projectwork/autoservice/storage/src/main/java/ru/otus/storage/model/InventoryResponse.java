package ru.otus.storage.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class InventoryResponse {
    List<String> successArticles;
    Map<String, String> errorArticles;
}
