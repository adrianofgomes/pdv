package br.com.pogger.pdvfree.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    /***
     * Configuração inicial de segurança HTTP
     * @param http
     * @return
     * @throws Exception
     * 
     * Permitir acesso público ao endpoint /status
     * Permitir acesso ao Swagger UI apenas para usuários com papel ADMIN
     * Todas as outras requisições exigem autenticação
     * 
     */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/status").permitAll()
                .requestMatchers("/swagger-ui/**").hasRole(AppRoles.ADMIN)
				.anyRequest().authenticated()
			)
			.httpBasic(Customizer.withDefaults())
			.formLogin(Customizer.withDefaults());

		return http.build();
	}

    /***
     * Configuração de usuário em memória para testes
     * Usuário: user
     * Senha: password
     * Papel: USER
     * 
     * Usuário: admin
     * Senha: admin123
     * Papel: ADMIN
     * 
     * @return
     */
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails userDetails = User.withUsername("user")
			.password("{noop}user")
			.roles(AppRoles.USER)
			.build();
        UserDetails adminDetails = User.withUsername("admin")
            .password("{noop}admin")
            .roles(AppRoles.ADMIN, AppRoles.PRODUTOS_READ, AppRoles.PRODUTOS_WRITE)
            .build();
		return new InMemoryUserDetailsManager(userDetails, adminDetails);
	}

}