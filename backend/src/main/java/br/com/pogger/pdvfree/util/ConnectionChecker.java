package br.com.pogger.pdvfree.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConnectionChecker implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ConnectionChecker.class);

    // Injeta as propriedades de conexão usando @Value
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    // Note: É melhor evitar logar a senha em ambientes de produção.
    // Se precisar logar para debug, faça-o de forma controlada.
    @Value("${spring.datasource.password}")
    private String dbPassword; 

    @Override
    public void run(String... args) throws Exception {
        log.info("--- VALORES DE CONEXÃO DO BANCO DE DADOS ---");
        log.info("URL: {}", dbUrl);
        log.info("Username: {}", dbUsername);

        // Logar apenas o comprimento da senha para confirmar que não está vazia.
        log.info("Password Length: {}", dbPassword.length()); 

        // Se for estritamente necessário ver a senha, faça:
        // log.warn("PASSWORD (WARNING: DO NOT EXPOSE IN PRODUCTION): {}", dbPassword);

        log.info("---------------------------------------------");
    }
}