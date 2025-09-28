package br.com.pogger.pdvfree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.pogger.pdvfree.util.PreConnectionChecker;

@SpringBootApplication
public class PdvfreeApplication {

	public static void main(String[] args) {
		//SpringApplication.run(PdvfreeApplication.class, args);
         // Crie uma instância de SpringApplication
        SpringApplication app = new SpringApplication(PdvfreeApplication.class);

        // REGISTRO ESTÁTICO: Adicione o listener antes de rodar o aplicativo
        app.addListeners(new PreConnectionChecker());

        // Inicie o aplicativo
        app.run(args);
	}

}
