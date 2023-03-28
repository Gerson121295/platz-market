package com.platz.market.persistence;

import com.platz.market.persistence.crud.ProductoCrudRepository;
import com.platz.market.persistence.entity.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//ProductoRepository tiene todo para interacturar con la tabla producto de la BD con esto acoplamos nuestra app a la capa de la persistencia
//y nuestro proyecto esta construido bajo un efoque de dominio,


//Como esta es una clase que esta interactuando directamente con la BD es buena practica decorarla con la anotacion @Repository
@Repository //indicarle a Spring que esta clase se encarga de interacturar con la BD(Aquí se hacen las operaciones a aplicar a las tablas) o tambien se puede usar @Component(generalizacion de este tipo de anotaciones, componente de Spring)

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

        //Retornar un producto en particular (por id)
    public Optional<Producto> getProducto(int idProducto){
        return productoCrudRepository.findById(idProducto);  //findById : encuentra registro por la clave primaria del producto
    }

    //Metodo para Guardar un producto
    public Producto save(Producto producto) {
        return productoCrudRepository.save(producto);    //los repositoris de spring data ofrecen el metodo save que recibe una entidad a guardar, y es el producto que se recibe como parametro
    }

    //Metodo para eliminar un producto
    public void delete(int idProducto) {   //no se quiere que retorne nada se utiliza void, se puede eleiminar un producto mandando el objeto completo o enviando su clave primaria
        productoCrudRepository.deleteById(idProducto);
    }

    //Metodo para actualizar un producto

    public Producto update(Producto newProducto, int id){
        return productoCrudRepository.findById(id)
                .map(
                        producto -> {
                            producto.setCantidadStock(newProducto.getCantidadStock());
                            producto.setCategoria(newProducto.getCategoria());
                            producto.setEstado(newProducto.getEstado());
                            producto.setNombre(newProducto.getNombre());
                            producto.setCodigoBarras(newProducto.getCodigoBarras());
                            producto.setPrecioVenta(newProducto.getPrecioVenta());
                            return productoCrudRepository.save(producto);
                        }
                ).get();
    }


}

