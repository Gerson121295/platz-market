package com.platz.market.persistence.mapper;


import com.platz.market.domain.Product;
import com.platz.market.persistence.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


//Como estamos usando Category y este ya tiene su mapper propio, dentro de la anotacion @Mapper agregamos uses={CategoryMapper.class}
@Mapper(componentModel = "spring", uses = {CategoryMapper.class}) //uses {{CategoryMapper.class} para que el ya sabe cuando tenga que convertir categoria a category tiene que usar {CategoryMapper.class}
public interface ProductMapper {

    //Creacion de los mapeadores: Producto(entity) a Product(domain)

    @Mappings({
            @Mapping(source="idProducto", target = "productId"),
            @Mapping(source="nombre", target = "name"), //nombre se traduce a name
            @Mapping(source="idCategoria", target = "categoryId"),
            @Mapping(source="precioVenta", target = "price"),
            @Mapping(source="cantidadStock", target = "stock"),
            @Mapping(source="estado", target = "active"),
            @Mapping(source="categoria", target = "category"), //categoria completa
    })
    Product toProduct(Producto producto);

    //Otro mapeador
    List<Product> toProducts(List<Producto> productos); //Recibe 1 lista de productos, y los comvierte en una lista de Product //No debemos definir el @Mapping porque ya internamente mappedStruct se va a encargar de que esto se va a comportar de la misma manera porque es el mismo tipo de conversion

    //Conversion Contraria

    @InheritInverseConfiguration // @InheritInverseConfiguration para que tome los mismos mapeos de @Mappings({ }) pero de forma inversa los valores source sera target y target sera source.
    @Mapping(target = "codigoBarras", ignore = true) //Pero como debemos ignorar aquello que no tenemos en el dominio como el codigo de barras
    Producto toProducto(Product product);
}
