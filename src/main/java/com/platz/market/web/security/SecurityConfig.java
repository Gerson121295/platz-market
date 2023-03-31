package com.platz.market.web.security;

import com.platz.market.domain.service.PlatzUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    @Autowired
    private PlatzUserDetailsService platzUserDetailsService;

  //  @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(platzUserDetailsService);
    }


    //Evita que se bloque al hacer consultas en la documentacion con Swagger
  //  @Override
//    protected void configure(HttpSecurity http)throws Exception {
//        http.authorizeRequests()
//                .dispatcherTypeMatchers(HttpMethod.valueOf("/swagger*")).permitAll();
//    }
}


/*
// Otra forma de hacer esta clase Y no extender de WebSecurityConfigurerAdapter, quedando asi:

public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http,                             BCryptPasswordEncoder bCryptPasswordEncoder)throws Exception {
	return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }
}

//Otra forma
@Bean
    public AuthenticationManager authManager(HttpSecurity http)throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsServicePedia)
                .and()
                .build();
    }

 */