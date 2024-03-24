package com.example.spring_project.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder){
        return new InMemoryUserDetailsManager(
                User.withUsername("omar").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("otari").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()
        );
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .formLogin(ar->ar.loginPage("/login").permitAll())
                .authorizeHttpRequests(ar->ar.requestMatchers("/h2-console/**").permitAll())
                .authorizeHttpRequests(ar->ar.requestMatchers("/patients"). hasRole("USER"))
                .authorizeHttpRequests(ar->ar.requestMatchers("/delete").hasRole("ADMIN"))
                .authorizeHttpRequests(ar->ar.requestMatchers("/formPatient").hasRole("ADMIN"))
                .authorizeHttpRequests(ar->ar.requestMatchers("/editPatient").hasRole("ADMIN"))
                .authorizeHttpRequests(ar->ar.requestMatchers("/savePatient").hasRole("ADMIN"))
                .exceptionHandling(ar->ar.accessDeniedPage("/notAuthorized"))
                .authorizeHttpRequests(ar->ar.anyRequest().authenticated())
                .build();
    }

}