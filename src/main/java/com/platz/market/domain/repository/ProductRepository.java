package com.platz.market.domain.repository;


import com.platz.market.domain.Product;

import java.util.List;
import java.util.Optional;

//Se indica el nombre de los metodos que queremos que cualquier repositorio que valla a trabajar con producto tenga que implementar
//Es similar al de paquete entity  clase ProductoResposioty que ya esta construido(como clase en entity), este que ya estaba construido
// va a implemetar esta interfas ProducRepository para que hable en terminos del dominio(Product) y no de Producto que es la tabla a la que se hace referencia en la BD

public interface ProductRepository {

    //MEtodos en terminos de dominio

    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);

    Optional<List<Product>>getScarseProducts(int quantity); //se le envia la cantidad que se considera escaso(si enviamos 20, traiga los producots menos de 20)

    Optional<Product> getProduct(int productId);
    Product save(Product product);
    void delete(int productId);

}
//con esto se define las reglas del dominio para que cualquier repositorio que quiera acceder
// a producto en una BD, esto garantiza no acoplar nuestra solucion a una BD especifica
//Si no que siempre se este hablando en terminos del dominio en este caso Product.



