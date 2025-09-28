package br.com.pogger.pdvfree.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

//@Component
// 1. Mude a interface para ApplicationEnvironmentPreparedEvent
public class PreConnectionChecker implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private static final Logger log = LoggerFactory.getLogger(PreConnectionChecker.class);

    public void onApplicationEvent(@NonNull ApplicationEnvironmentPreparedEvent event) {
        // 2. Acesse as propriedades através do objeto Environment
        Environment env = event.getEnvironment();

        String dbUrl = env.getProperty("spring.datasource.url");
        String dbUsername = env.getProperty("spring.datasource.username");
        String dbPassword = env.getProperty("spring.datasource.password");

        // 3. Exiba as informações de debug
        log.error("===========================================");
        log.error("VALORES DE CONEXÃO CARREGADOS (PRE-CONEXÃO):");
        log.error("URL: {}", dbUrl);
        log.error("Username: {}", dbUsername);
        // Exiba a senha apenas se for necessário para debug
        if (dbPassword != null) {
            log.error("Password length: {}", dbPassword.length());
        } else {
            log.error("Password is null");
        }
        log.error("===========================================");
    }
}