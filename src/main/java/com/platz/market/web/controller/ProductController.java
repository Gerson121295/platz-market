package com.platz.market.web.controller;


import com.platz.market.domain.Product;
import com.platz.market.domain.repository.ProductRepository;
import com.platz.market.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController //Anotacion indica a Spring que esta clase será un controlador de una API Rest
@RequestMapping("/products")  //En que path va a aceptar las peticion que hagamos
public class ProductController {

    @Autowired  //podemos usar Autowired porque en ProductService tiene la anotacion @Service y es de Spring
    private ProductService productService; //Inyectar el servicio ProductService le llamamos productService

    //Metodos

     public List<Product> getAll(){
        return productService.getAll();
    }


    public Optional<Product> getProduct(int productId){
        return productService.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId){
        return productService.getByCategory(categoryId);
    }

    public Product save(Product product){
        return productService.save(product);
    }

    public boolean delete(int productId){
        return productService.delete(productId);
    }

}
