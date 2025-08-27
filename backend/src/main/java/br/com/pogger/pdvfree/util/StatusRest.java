package br.com.pogger.pdvfree.util;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusRest {

    @GetMapping("/status")
    public String status() {
        return "OK";
    }
}