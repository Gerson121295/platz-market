package com.platz.market.web.controller;


import com.platz.market.domain.Product;
import com.platz.market.domain.service.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Anotacion indica a Spring que esta clase será un controlador de una API Rest
@RequestMapping("/products")  //En que path va a aceptar las peticion que hagamos
public class ProductController {

    @Autowired  //podemos usar Autowired porque en ProductService tiene la anotacion @Service y es de Spring
    private ProductService productService; //Inyectar el servicio ProductService le llamamos productService

    //Metodos

    /*Aplicaremos:
    @ResponseEntity para controlar los llamadas y respuestas que reciben nuestros controladores.
    HttpStatus: Para definir desde alli, que codigo queremos retornar, según cada caso puntual
    */

    @GetMapping("/all")  //GetMapping porque estamos obteniendo informacion y como parametro debemos incluir el path por sobre el cual va a atender
    //@ApiOperation(value = "Get all supermarket products")    //Para la documentacion dar a conocer en Swagger  lo que hace la API
   // @ApiResponse(code = 200, message = "OK") //La respuesta al ejecutar el metodo en Swagger

    @ApiOperation(value = "Obtener todos los productos del supermercado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Se han obtenido todos los productos exitosamente"),
            @ApiResponse(code = 500, message = "Error interno del servidor")
    })

    public ResponseEntity <List<Product>>getAll(){ //Responde con una lista de Productos
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
//    @ApiOperation(value = "Search a product with an ID")
//    @ApiResponses(value = {     //respuestas en Swagger
//            @ApiResponse(code = 200, message = "OK. El recurso se obtiene exitosamente"), //cuando encurentra el producto
//            @ApiResponse(code = 404, message = "Sorry my friend, Product not found") //cuando no encuentra el producto
//    })
    @ApiOperation(value = "Buscar un producto por ID", notes = "Busca un producto específico en el supermercado por su ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene exitosamente"),
            @ApiResponse(code = 404, message = "No se encontró el producto solicitado"),
            @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    public ResponseEntity<Product> getProduct(@ApiParam(value = "The id of the product", required = true, example = "7")
                                                  @PathVariable("id") int productId){ //ApiParam es para Swagger mostrar respuestas, el example es opcional /
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId){
        return productService.getByCategory(categoryId)  //aprovechando el metodo que viene el servicio podemos usar el .map
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); //no existan productos de la categoria que estoy consultando muestre NOT FOUND
    }

    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product){ //El producto no va a viajar dentro el path si no va a ser parte del cuerpo de la peticion se añade la peticion @RequestBody(cuerpo de la peticion)
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int productId){
        if(productService.delete(productId)){
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
