package com.platz.market.domain.service;


import com.platz.market.domain.Product;
import com.platz.market.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service  //esta anotacion se puede reemplazar por @Component
public class ProductService {

    //Aqui se trabaja con terminos de dominio donde ocurre la conversion es en ProductRepository
    @Autowired  //ya que ProductRepository no tiene anotaciones
    private ProductRepository productRepository; //inyectar la interfaz del ProductRepository

    //Metodos
    //Metodo Obtiene toda la lista de productos
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    //Encontrar un producto
    public Optional<Product> getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    //buscar productos por categoria
    public Optional<List<Product>> getByCategory(int categoryId) {
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public boolean delete(int productId) {
        return getProduct(productId).map(product -> { //con getProduct verificar si existe el producto a eliminar
            productRepository.delete(productId); //elimino al producto
            return true;  //si elimino el producto retorno true
        }).orElse(false);//si no se elimino el producto retorna false (es porque no existia el producto)
    }

    //Otra forma de eliminar un producto
    public boolean delete2(int productId) {
        if (getProduct(productId).isPresent()) { //si el producto esta presente, este metodo retorna un true si existe, sino un false
            productRepository.delete(productId); //si existe el producto lo elimina
            return true; //luego retorna un true
        } else {
            return false;
        }
    }

}
