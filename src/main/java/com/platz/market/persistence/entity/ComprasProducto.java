package com.platz.market.persistence.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


/* En la tabla compras_producto de la BD hay columnas que tiene clave primarias compuesta
id_compra y id_producto por lo que no se puede añadir directamente a nuestro entity ComprasProducto
para eso debemos crear otra clase(ComprasProductoPK) para que las contenga, luego meter esa clase
dentro de ComprasProducto
 */
@Entity
@Table(name = "compras_productos")
public class ComprasProducto {
    //Ya no se agrega los campos id_compra y id_producto porque se agregaron en la clase ComprasProductoPK debido a que tiene clave primaria compuesta

    @EmbeddedId  //@EmbeddedId se usa cuando la clave primaria es compuesta, y esta dada por otra clase(ComprasProductoPK) //@id se usa cuando la clave primaria es sencilla
    private ComprasProductoPK id; //porque estas son el id de la clase

    private Integer cantidad;
    private Double total;  //es decimal por eso Double
    private Boolean estado;


    //Generate Getters and Setters de todos los atributos

    public ComprasProductoPK getId() {
        return id;
    }

    public void setId(ComprasProductoPK id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


}
