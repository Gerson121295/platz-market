package com.platz.market.persistence;

import com.platz.market.persistence.crud.ProductoCrudRepository;
import com.platz.market.persistence.entity.Producto;

import java.util.List;
import java.util.Optional;

public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

   //Metodo que recupere todos los productos que tenemos en la base de datos-
   //Estos productos van a ser recuperados gracias a los repositorios de Spring Data
    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll();
    }

    //Metodo que retorne una lista de producto que pertenece a una categoria ordenado por alfabeto(ascendente)
    public List<Producto> getByCategoria(int idCategoria){
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);  //usa el Query methods findByIdCategoriaOrderByNombreAsc de la interfaz ProductoCrudRepository

    }

    //Metodo que retorne un optional de una lista de producto de escasos

    public Optional<List<Producto>> getEscasos(int cantidad){ //el parametro , boolean estado no fue necesario porque se le envio true directamente en el cuerpo como argumento(sig, linea de codigo).
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }

}
