package com.platz.market.persistence.crud;

import com.platz.market.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository extends CrudRepository<Compra, Integer> { //CrudRepostory recibe el entity (Compra) y su tipo de clave primaria)

    Optional<List<Compra>> findByIdCliente(String idCliente);
}
