package com.platz.market.persistence.crud;

import com.platz.market.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/*
Repositorios de Spring Data
CrudRepository: Permite hacer las operaciones de CRUD.
PagingAndSortingRepository: Nos permite hacer todo lo que hace el CrudRepository pero adicionalmente nos permite hacer tareas de ordenación y paginamiento de nuestro repositorio.
JPARepository: Nos permite hacer lo mismo que los dos anteriores, pero además nos permite hacer tareas de JPA específicas como Flush que combina o guarda todo en memoria sin que otras
 entidades o entornos vean esos cambios en la base de datos.
 */


public interface ProductoCrudRepository extends CrudRepository <Producto, Integer>{ //recibe 2 parametros: Producto (la tabla), Integer(el tipo de dato de clave primaria)

    //Query Methods
    //Recuperar toda la lista de productos que pertenezca con una categoria
     List<Producto>findByIdCategoriaOrderByNombreAsc(int idCategoria);   //buscar los productos por el atributo: idCategoria, hay que respetata el camelCase: se escribe "IdCategoria", y como parametro recibe el idCategoria(como se llama dentro de la clase)

    //Query de manera nativa
   // @Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)       //Otra forma es hacerlo por un Query Nativo
    //Si tenemos Asignado nuestro anotacion Query no es Necesario llamar asi(como el method de arriba) nuestro metodo asi lo podemos llamar de cualquier manera
    //List<Producto> getByCategoria(int idCategoria);   //buscar los productos por el atributo: idCategoria, hay que respetata el camelCase: se escribe "IdCategoria", y como parametro recibe el idCategoria(como se llama dentro de la clase)

    //Query Methods: Recupera productos por la cantidad de Stock, productos agotados
    Optional<List<Producto>>findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);  //cantidadStock respentando camel case es CantidadStock, LessThan(menos que), And("y" para agregar mas consulta) Estado(estado en la clase)


}
