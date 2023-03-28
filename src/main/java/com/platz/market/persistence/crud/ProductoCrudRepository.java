package com.platz.market.persistence.crud;

import com.platz.market.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

/*
Repositorios de Spring Data
CrudRepository: Permite hacer las operaciones de CRUD.
PagingAndSortingRepository: Nos permite hacer todo lo que hace el CrudRepository pero adicionalmente nos permite hacer tareas de ordenación y paginamiento de nuestro repositorio.
JPARepository: Nos permite hacer lo mismo que los dos anteriores, pero además nos permite hacer tareas de JPA específicas como Flush que combina o guarda todo en memoria sin que otras
 entidades o entornos vean esos cambios en la base de datos.
 */


public interface ProductoCrudRepository extends CrudRepository <Producto, Integer>{ //recibe 2 parametros: Producto (la tabla), Integer(el tipo de dato de clave primaria)


}
