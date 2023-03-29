package com.platz.market.persistence.entity;

import jakarta.persistence.*;


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


    //Establecer la relacion con la tabla compra

    @ManyToOne //Muchas ComprasProducto en 1 Compra
    @MapsId("idCompra") //añadimos el nombre de la clave primaria que queremos que se enlace //Con esto cuando comprasProducto se guarde en cascada va a saber a que clave primaria pertence cada 1 de los productos que esta en una compra
    @JoinColumn(name = "id_compra", insertable = false, updatable = false)  //el id_compra une la clase ComprasProducto con Compras //todos las clases que lleven JoinColumn deben llevar insertable y updatable=false
    private Compra compra; //Atributo privado Compra se llama compra, (compra ira en la relacion de Compra a ComprasProducto)

    //Establecer la relacion con la tabla Producto

    @ManyToOne  //Muchas ComprasProducto en 1 producto
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)// id_producto es el campo que une la clase ComprasProducto con Producto
    private Producto producto; //Atributo privado Producto llamado producto, producto sera mapeado en la clase producto al hacer la relacion.

    //Generate Getters and Setters de todos los atributos


    public Compra getCompra() {
        return compra;
    }
    public void setCompra(Compra compra) {
        this.compra = compra;
    }
    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }


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
