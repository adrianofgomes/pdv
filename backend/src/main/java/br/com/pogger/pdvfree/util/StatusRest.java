package br.com.pogger.pdvfree.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusRest {

    @Autowired
    private AppInfoUtil appInfoUtil;

    @GetMapping("/status")
    public String status() {
        return appInfoUtil.getNomeAplicacaoVersao();
    }
}