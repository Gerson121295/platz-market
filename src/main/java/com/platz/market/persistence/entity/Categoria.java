package com.platz.market.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    private String descripcion;
    private Boolean estado;

    //vamos a acceder a los atributos que pertenecen a la clase categoria


    @OneToMany(mappedBy = "categoria")  ////Relacion de una categoria tiene muchos productos, se agrega categoria porque en la clase Producto se escribio categoria al atributo Categoria en la relacion
    //mappedBy sig. por quien esta mapeado o que relacion la respalda
    private List<Producto> productos;   //Lista de producto se va a llamar productos


    //Generate Getters and Setters

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }


    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
