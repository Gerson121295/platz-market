package com.platz.market.persistence.mapper;


import com.platz.market.domain.Category;
import com.platz.market.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring") //para indicar al project que es un mapeador, adentor del parentesis decimos que es un compnente tipo spring
public interface CategoryMapper {
    //Mapeers o conversores de Categoria(package entity) en Category(package domain)

    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),  //traducir de la fuente(entity-Categoria) y target adonde quiero llevarla(domain-Category)
            @Mapping(source = "descripcion", target = "category"), //descripcion se traduce a category
            @Mapping(source = "estado", target = "active"),
    })
    Category toCategory(Categoria categoria); //Category es lo que vamos a obtener del mapped y el metodo se llama toCategory, comvertimos una Categoria en Category

    //Caso donde la conversion es externa
    // @InheritInverseConfiguration para que tome los mismos mapeos de @Mappings({ }) pero de forma inversa los valores source sera target y target sera source.
    @InheritInverseConfiguration //indica a mapedStruc que la conversion es a la inversa a la que se realizo anterior, entoces no tendremos que definir de nuevo @Mappings
                                // simplemente usamos @InheritInverseConfiguration mapedstruct sabe que tiene que hacer el mateo inverso

    @Mapping(target = "productos", ignore = true) //para excluir un atributo de Categia(entity), productos no va,
    Categoria toCategoria(Category category);

}
