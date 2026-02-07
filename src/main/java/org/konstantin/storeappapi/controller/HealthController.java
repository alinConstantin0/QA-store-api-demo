package org.konstantin.storeappapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController            // metodele din clasele RestController raspund la requesturi web si intorc direct text/JSON
public class HealthController {

    @GetMapping("/health")                                // mapping pentru check
    public String healthCheck() {
        return "ok";
    }
}
