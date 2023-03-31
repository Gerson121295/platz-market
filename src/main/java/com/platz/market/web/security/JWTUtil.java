package com.platz.market.web.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

///Clase para que genere los Tokens
public class JWTUtil {

    private static final String KEY = "pl4tz";  //Recomendacion la llave deberia ser mas compleja

    public String generateToken(UserDetails userDetails){ //recibe UserDetails -clase de Spring
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date()) //builder permite en una secuencia de metodos construir nuestro JWT, adentro de setSubjet va el usuario,setIssuedAt(va la fecha creada)
                .setExpiration(new Date(System.currentTimeMillis() * 1000 * 60 * 60 * 10 )) //1000*60min*60seg*10horas
                .signWith(SignatureAlgorithm.HS256, KEY).compact(); //se elije el algoritmo y se usa una clave para firmar el metodo con el algoritomo Sha256 y compact para crear nuestro JWT
    }
}

//para crear JWT debemos crear un controlador para que reciba, como parametro el usuario y la contraseña
//y como respuesta envie el JSON WEB TOKEN
//Tambien se deben crear 2 clases en DTO(AuthenticationRequest, AuthenticationResponse) y  que permita controlar estos, de una mejor manera




