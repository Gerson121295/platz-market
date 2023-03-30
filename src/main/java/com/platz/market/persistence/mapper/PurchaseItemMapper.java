package com.platz.market.persistence.mapper;

import com.platz.market.domain.PurchaseItem;
import com.platz.market.persistence.entity.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring") //,uses = {ProductMapper.class}  //como se usa idProducto se debe indicar en el mapper con uses para indicar que se va a usar //Se agrega (componentModel = "spring") con el fin de poderlo inyectar posteriormente desde otro lugares
public interface PurchaseItemMapper {

    //source ComprasProducto(entity) a PurchaseItem(domain)
    @Mappings({
            @Mapping(source = "id.idProducto", target = "productId"), //id.idProducto debido por que esa clase tiene clave compuesta(id definidos en otra clase(ComprasProductoPK))
            @Mapping(source = "cantidad", target = "quantity"),
         // @Mapping(source = "total", target = "total"), //como la fuente y el target se llaman igual no lo necesitamos  lo borramos(no es necesario meter ese mapeo en el mapper)
            @Mapping(source = "estado", target = "active"),

//cuando el atributo fuente y destino es el mismo se hace el mismo no se agrega
    })
    PurchaseItem toPurchaseItem(ComprasProducto producto); //Deseo obtener un PurchaseItem y lo que recibe el metodo toPurchaseItem es ComprasProducto se llama producto


    //Conversion contraria: PurchaseItem a ComprasProducto
    @InheritInverseConfiguration() //para que use de manera inversa el @Mappings definido arriba
    //Se deben agregar todos los atributos de la fuente al mapper y los que no se usan se deben ignorar
    @Mappings({
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "id.idCompra", ignore = true),
    })

    ComprasProducto toComprasProducto(PurchaseItem item);

}

