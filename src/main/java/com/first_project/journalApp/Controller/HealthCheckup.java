package com.first_project.journalApp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckup {
    @GetMapping("/health-checkup")
    public String healthCheckup() {
        return "healthy";
    }
}
