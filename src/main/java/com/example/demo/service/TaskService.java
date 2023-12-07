package com.example.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TaskService {

    public ResponseEntity<String> count(String str) {
        if (str == null || str.isEmpty()) {
            String emptyStringMessage = "string is empty";
            return new ResponseEntity<>(emptyStringMessage, HttpStatus.BAD_REQUEST);
        }
        Map<String, Integer> map = new LinkedHashMap<>();
        for (var c : str.toCharArray()) {
            String s = "\"" + c + "\"";
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
        return ResponseEntity.ok(map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new)).toString());
    }
}
