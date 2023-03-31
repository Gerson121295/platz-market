package com.platz.market.domain.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

//relacion con package security de archivo SecurityConfig

@Service //para posterior poderla inyectar
public class PlatzUserDetailsService implements UserDetailsService { //implementa la interfaz UserDetailService(por defecto de Spring)

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("alejandro", "{noop}platzi", new ArrayList<>()); //user, password(se agrega {noop} por que no ha pasado en codificador, list(roles de user)
    }

}















