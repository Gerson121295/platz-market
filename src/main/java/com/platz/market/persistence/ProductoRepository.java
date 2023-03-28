package com.platz.market.persistence;

import com.platz.market.persistence.crud.ProductoCrudRepository;
import com.platz.market.persistence.entity.Producto;

import java.util.List;

public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

   //Metodo que recupere todos los productos que tenemos en la base de datos-
   //Estos productos van a ser recuperados gracias a los repositorios de Spring Data
    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll();
    }
}
