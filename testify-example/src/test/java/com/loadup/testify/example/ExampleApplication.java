package com.loadup.testify.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.loadup.testify.example",
        "com.loadup.testify.data",
        "com.loadup.testify.mock",
        "com.loadup.testify.assertions"
})
public class ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }
}