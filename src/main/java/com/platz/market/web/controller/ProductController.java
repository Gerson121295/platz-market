package com.platz.market.web.controller;


import com.platz.market.domain.Product;
import com.platz.market.domain.repository.ProductRepository;
import com.platz.market.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //Anotacion indica a Spring que esta clase será un controlador de una API Rest
@RequestMapping("/products")  //En que path va a aceptar las peticion que hagamos
public class ProductController {

    @Autowired  //podemos usar Autowired porque en ProductService tiene la anotacion @Service y es de Spring
    private ProductService productService; //Inyectar el servicio ProductService le llamamos productService

    //Metodos
    @GetMapping("/all")  //GetMapping porque estamos obteniendo informacion y como parametro debemos incluir el path por sobre el cual va a atender
    public List<Product> getAll(){
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable("id") int productId){
        return productService.getProduct(productId);
    }

    @GetMapping("/category/{categoryId}")
    public Optional<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId){
        return productService.getByCategory(categoryId);
    }

    @PostMapping("/save")
    public Product save(@RequestBody Product product){ //El producto no va a viajar dentro el path si no va a ser parte del cuerpo de la peticion se añade la peticion @RequestBody(cuerpo de la peticion)
        return productService.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int productId){
        return productService.delete(productId);
    }

}
