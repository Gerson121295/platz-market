package com.platz.market.persistence.entity;

import jakarta.persistence.*;

@Entity //Define la arquitectura del resto del modelo, dará a entender a JAva que esta clase se comporta como una clase que mapea una tabla en la BD
@Table(name = "productos") //en name va el nombre de la tabla en la BD productos
public class Producto {

    @Id  //se le agrega a la columna con la Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //llave primaria se genera automaticamente al crear un nuevo producto, se le agrega una estrategia que le da la identidad a los registros de la tabla productos. //JAVA genera el valor automaticamente
    //Siempre que una columna se llame diferente en la clase a la columna de la BD de la tabla se debe de agregar @Column
    @Column(name="id_producto")   //Como se cambio el nombre de la columna se agrega para refenciar: en la BD es id_producto, en la clase:idProducto
    private Integer idProducto; //no usar int sino usar el valor enpaquetado integer

    private String nombre; //no se agrego @column porque asi esta en la columna de la tabla producots de la BD

    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "codigo_barras")
    private String codigoBarras;

    @Column(name = "precio_venta")
    private Double precioVenta;

    @Column(name = "cantidad_stock")
    private Integer cantidadStock;

    private Boolean estado;

    //Generate de Getters and Setters seleccionar todos los atributos de la clase

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(Integer cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }




}
