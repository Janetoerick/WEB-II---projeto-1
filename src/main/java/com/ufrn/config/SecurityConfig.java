package com.ufrn.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ufrn.secutiry.JwtAuthFilter;
import com.ufrn.secutiry.JwtService;
import com.ufrn.service.UsuarioService;

@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioService usuarioService;

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(jwtService, usuarioService);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .cors(Customizer.withDefaults())
//                .cors().and()
                .csrf().disable()
                .authorizeHttpRequests((authz) -> {
                    try {
                        authz
                                .antMatchers(HttpMethod.POST, "/equipamento/**")
                                .hasRole("ADMIN")
                                .antMatchers(HttpMethod.GET, "/equipamento/**")
                                .hasAnyRole("PROFESSOR", "ALUNO", "ADMIN")
                                .antMatchers(HttpMethod.DELETE, "/equipamento/**")
                                .hasRole("ADMIN")
                                .antMatchers(HttpMethod.PUT, "/equipamento/**")
                                .hasRole("ADMIN")

                                .antMatchers(HttpMethod.POST, "/sala/**")
                                .hasRole("ADMIN")
                                .antMatchers(HttpMethod.GET, "/sala/**")
                                .hasAnyRole("PROFESSOR", "ALUNO", "ADMIN")
                                .antMatchers(HttpMethod.DELETE, "/sala/**")
                                .hasRole("ADMIN")
                                .antMatchers(HttpMethod.PUT, "/sala/**")
                                .hasRole("ADMIN")

                                .antMatchers(HttpMethod.POST, "/turma/**")
                                .hasRole("ADMIN")
                                .antMatchers(HttpMethod.GET, "/turma/**")
                                .hasAnyRole("PROFESSOR", "ALUNO", "ADMIN")
                                .antMatchers(HttpMethod.DELETE, "/turma/**")
                                .hasRole("ADMIN")
                                .antMatchers(HttpMethod.PUT, "/turma/**")
                                .hasAnyRole("ADMIN", "PROFESSOR")

                                .antMatchers(HttpMethod.POST, "/reservaIndividual/**")
                                .hasRole("ALUNO")
                                .antMatchers(HttpMethod.GET, "/reservaIndividual/**")
                                .hasAnyRole("ALUNO", "ADMIN")
                                .antMatchers(HttpMethod.DELETE, "/reservaIndividual/**")
                                .hasAnyRole("ALUNO", "ADMIN")
                                .antMatchers(HttpMethod.PUT, "/reservaIndividual/**")
                                .hasRole("ALUNO")

                                .antMatchers(HttpMethod.POST, "/reservaGrupal/**")
                                .hasRole("PROFESSOR")
                                .antMatchers(HttpMethod.GET, "/reservaGrupal/professor/**")
                                .hasAnyRole("PROFESSOR", "USUARIO", "ADMIN")
                                .antMatchers(HttpMethod.DELETE, "/reservaGrupal/**")
                                .hasAnyRole("PROFESSOR", "ADMIN")
                                .antMatchers(HttpMethod.PUT, "/reservaGrupal/**")
                                .hasRole("PROFESSOR")

                                .antMatchers(HttpMethod.PUT, "/usuario/**")
                                .hasAnyRole("PROFESSOR", "USUARIO", "ADMIN")
                                .antMatchers(HttpMethod.GET, "/usuario/**")
                                .hasAnyRole("PROFESSOR", "USUARIO", "ADMIN")

                                .antMatchers(HttpMethod.POST, "/usuario/**")
                                .permitAll()
                                .anyRequest().authenticated()

                                .and()
                                .sessionManagement()
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                .and()
                                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                                .httpBasic();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                );

        return http.build();
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//
//        return source;
//    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowedOrigins(Arrays.asList("http://localhost:19000"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        
        return new CorsFilter(source);
    }

}
