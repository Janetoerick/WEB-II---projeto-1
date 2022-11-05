package com.ufrn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import net.bytebuddy.build.Plugin.Engine.Source.InMemory;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() //seguranca entre aplicavao web e api,aqui nossa api é rest
            .authorizeHttpRequests((authz) -> {
                try {
                    authz
                        .antMatchers("/api/clientes/**")
                            //.hasRole("USER") 
                            .hasAnyRole("USER","ADMIN")
                        .antMatchers("/api/produtos/**")
                            .hasRole("ADMIN")
                        .antMatchers("/api/pedidos/**")
                            //.hasRole("USER")    
                            .hasAnyRole("USER","ADMIN") 
                        .antMatchers(HttpMethod.POST, "/api/usuarios/**")
                            .permitAll()
                        .anyRequest().authenticated()   ;
//                    .and() 
//                        .sessionManagement()
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //sessões sem usuários - TODA REQUISICAO PRECISA DO TOKEN
//                    .and() //volta a raiz
//                        .addFilterBefore( jwtFilter(), UsernamePasswordAuthenticationFilter.class);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
                
                 
            );
        return http.build();
    }

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		return http
			.csrf(csrt -> csrt.disable())
			.authorizeRequests(auth -> {
				auth.antMatchers("/").permitAll();
				auth.antMatchers("/user").hasRole("USER");
				auth.antMatchers("/admin").hasRole("ADMIN");
			})
			.httpBasic(Customizer.withDefaults())
			.build();
	}

}
