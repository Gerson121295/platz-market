package com.platz.market.persistence.mapper;

import com.platz.market.domain.Purchase;
import com.platz.market.persistence.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})  //se agrega componentModel = "spring" para poderlo inyectar, asi tambiens e le dice que use el mapper PurchaseItemMapper porque internamente vamos a mapear dentro de la compra todos sus productos

public interface PurchaseMapper {

    //Necesito un Purchase a partir de una compra
    @Mappings({
            @Mapping(source = "idCompra", target = "purchaseId"),
            @Mapping(source = "idCliente", target = "clientId"),
            @Mapping(source = "fecha", target = "date"),
            @Mapping(source = "medioPago", target = "paymentMethod"),
            @Mapping(source = "comentario", target = "comment"),
            @Mapping(source = "estado", target = "state"),
            @Mapping(source = "productos", target = "items"), //este mapers es el que usa PurchaseItemMapper para luego convertir los productos uno a uno
    })
    Purchase toPurchase(Compra compra); // mapers de la forma Compra a Purchase
    //Siempre la clase destino debe tener todos los mapeos, si no los tiene, debemos ignoralos,  //solo si en fuente como destino son iguales no se agrega.

    List<Purchase> toPurchases(List<Compra> compras); //Como ya se definio un mapers de la forma Compra a Purchase no necesitamos hacerlo en esta, el automaticamente va a adquirir la configuracion de @Mappings de arriba

    //Inversion contraria

    @InheritInverseConfiguration //para habilitar la conversion inversa del mapeo de arriba
    @Mapping(target = "cliente", ignore = true)         //ignorar todos aquellos atributos que no se utilizan
    Compra toCompra(Purchase purchase);  //Necesito una compra y recibo un Purchase

}





/*

una forma
    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active")
    })
    Category toCategory(Categoria categoria);

   Otra forma esto:

@Mapping(source = "idCategoria", target = "categoryId")
@Mapping(source = "descripcion", target = "category")
@Mapping(source = "estado", target = "active")
Category toCategory(Categoria categoria);

 */