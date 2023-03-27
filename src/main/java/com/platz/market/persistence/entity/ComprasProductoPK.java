package com.platz.market.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;

import java.io.Serializable;

// no se  agrega la anotacion @Entity por que esta tabla no mapeara a otra en la BD
@Embeddable  //porque esta clase la vamos en Embeder en la clase ComprasProducto
public class ComprasProductoPK implements Serializable { //se implement al interfaz de JAVA Serializable

    @Column(name = "id_compra")
    private Integer idCompra;

    @Column(name = "id_producto")
    private Integer idProducto;


    //Generate Getters and Setters de los 2 atributos

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }


}
