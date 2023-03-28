package com.platz.market.persistence;

import com.platz.market.domain.Product;
import com.platz.market.domain.repository.ProductRepository;
import com.platz.market.persistence.crud.ProductoCrudRepository;
import com.platz.market.persistence.entity.Producto;
import com.platz.market.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//ProductoRepository tiene todo para interacturar con la tabla producto de la BD con esto acoplamos nuestra app a la capa de la persistencia
//y nuestro proyecto esta construido bajo un efoque de dominio,

//Como esta es una clase que esta interactuando directamente con la BD es buena practica decorarla con la anotacion @Repository
@Repository //indicarle a Spring que esta clase se encarga de interacturar con la BD(Aquí se hacen las operaciones a aplicar a las tablas) o tambien se puede usar @Component(generalizacion de este tipo de anotaciones, componente de Spring)

//public class ProductoRepository {
public class ProductoRepository implements ProductRepository { //se agrego implemets para orientar nuestro ProductoRespository al dominio debemos implementar la Interfaz de ProductRepsoitory(repository) esto habla en terminos lo que queremos retornar finalmente

    private ProductoCrudRepository productoCrudRepository;

    //Para hacer la comversion de Producto a Product debemos llamar el atributo de ProductMapper(mapper)
    private ProductMapper mapper; //ProductMapper lo llamo mapper


    //ProductoRepository este enfocado a una tabla (Producto de entity)

    /*
    //Metodos para Producto(entity)

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
        */

    //Ahora es Los metodos van Orientado al dominio por lo que se usa Product de domain ya no Producto de Entity
    //ProductoRepository este enfocado al dominio(Product de domain) en ves de enfocado a una tabla (Producto de entity)
    //Con esto evitamos que el proyecto se acople a una BD puntual, mañana usamos mongo(ayi hay colecciones por lo que haria que nuestra api cambie si queremos usarla directamente
    //Con mapedStruct evitamos esto simplemente creamos un nuevo mapped que convierta, Product en lo que la coleccion tenga como nombrey el codigo seguira funcionando correctamente
    //Lista de product
    @Override
    public List<Product> getAll(){
        List<Producto> productos=(List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId){
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId); //retorna unalista normal por qlo que en la siguiente linea se agrega optional.of
        return Optional.of(mapper.toProducts(productos)); //como espera una lista de optional por eso se agrego optional.of
    }

    @Override

    public Optional<List<Product>>getScarseProducts(int quantity) { //se le envia la cantidad que se considera escaso(si enviamos 20, traiga los producots menos de 20)
    Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity);
    return productos.map(prods -> mapper.toProducts(prods));    //no tengo un mapeador que convierta una lista de opcionales, por lo que se hace un mapeador de los productos, map retorna lo que se esta haciendo en la expresion
        //la arrow function recibe los productos que tiene adentro y los convierte a prods y los retorna
    }


    @Override
    public Optional<Product> getProduct(int productId){
    return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));    //findById ya retorna un Optional
    }


    @Override
    public Product save(Product product){
        Producto producto = mapper.toProducto(product);//conversion inversa de product a producto
        return mapper.toProduct(productoCrudRepository.save(producto)); //el save espera un producto no un product por lo que anterior se tuvo que hacer la conversion
    }

    @Override
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }

}

