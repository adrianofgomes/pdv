package br.com.pogger.pdvfree.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pogger.pdvfree.security.AppSecurityUtil;

@RestController
public class StatusRest {

    @Autowired
    private AppInfoUtil appInfoUtil;
    
    @Autowired
    private AppSecurityUtil appSecurityUtil;

    @GetMapping("/status")
    public String status() {
        return appInfoUtil.getNomeAplicacaoVersao() + " - Usuário logado: " + appSecurityUtil.getUsuarioLogado();
    }
}