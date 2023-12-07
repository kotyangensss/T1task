package com.example.demo.controller;

import com.example.demo.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/count")
    @Operation(summary = "Count number of entries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Number of symbols"),
            @ApiResponse(responseCode = "400", description = "Bad string")
    })
    public ResponseEntity<String> count(@RequestParam String string) {
        return taskService.count(string);
    }
}
