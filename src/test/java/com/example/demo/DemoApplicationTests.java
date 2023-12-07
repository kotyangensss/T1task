package com.example.demo;

import com.example.demo.controller.TaskController;
import com.example.demo.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private TaskController taskController;

    @Autowired
    private TaskService taskService;

    @Test
    void contextLoads() {
        assertThat(taskController).isNotNull();
        assertThat(taskService).isNotNull();
    }

    @Test
    public void emptyString() {
        ResponseEntity<String> response = taskService.count("");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isEqualTo("string is empty");
    }

    @Test
    public void multipleEntryString() {
        ResponseEntity<String> response = taskService.count("aaa");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("{\"a\"=3}");
    }

    @Test
    public void specialSymbols() {
        ResponseEntity<String> response = taskService.count("\\\\,.+= !@#$%^&*()_-");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("{\"\\\"=2,");
        assertThat(response.getBody()).contains("\",\"=1");
        assertThat(response.getBody()).contains("\".\"=1");
        assertThat(response.getBody()).contains("\"+\"=1");
        assertThat(response.getBody()).contains("\"=\"=1");
        assertThat(response.getBody()).contains("\"!\"=1");
        assertThat(response.getBody()).contains("\"@\"=1");
        assertThat(response.getBody()).contains("\"#\"=1");
        assertThat(response.getBody()).contains("\"$\"=1");
        assertThat(response.getBody()).contains("\"%\"=1");
        assertThat(response.getBody()).contains("\"^\"=1");
        assertThat(response.getBody()).contains("\"&\"=1");
        assertThat(response.getBody()).contains("\"*\"=1");
        assertThat(response.getBody()).contains("\"(\"=1");
        assertThat(response.getBody()).contains("\")\"=1");
        assertThat(response.getBody()).contains("\"_\"=1");
        assertThat(response.getBody()).contains("\"-\"=1");
    }

    @Test
    public void descendingOrder() {
        ResponseEntity<String> response = taskService.count("abcbccCCCC");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("{\"C\"=4, \"c\"=3, \"b\"=2, \"a\"=1}");
    }
}
