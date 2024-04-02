package com.sairam.cabBookingApp.configs.security;

import com.sairam.cabBookingApp.services.LogOutService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    @Autowired
    private JWTAuthenticationFilter jwtAuthFilter;

    @Autowired
    private UserDetailsService userDetailsService;
    private final LogOutService logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).
        authorizeHttpRequests(authorize-> authorize.requestMatchers("/check").hasAuthority("customer"));
        http.authorizeHttpRequests(authorize-> authorize.requestMatchers("/auth/**").permitAll());
        http.authorizeHttpRequests(authorize-> authorize.requestMatchers("/auth/driver/register").permitAll().
                requestMatchers("/driver/**").hasAuthority("driver").
                requestMatchers("/auth/refresh-token").permitAll().
                requestMatchers("/trip/**").authenticated()
                .requestMatchers("/drivers/**").authenticated());

        http.authorizeHttpRequests(authorize-> authorize.requestMatchers("/auth/simple").permitAll());
        http.authenticationProvider(authenticationProvider(userDetailsService));
        http.httpBasic(Customizer.withDefaults());
         http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.logout(logout ->
                logout.logoutUrl("/api/v1/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()));
        return http.build();
    }

    @Bean
   public  AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService){
        DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(getpasswordEncoder());
        return daoAuthenticationProvider;
    }
    @Bean
    public PasswordEncoder getpasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}



