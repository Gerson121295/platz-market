package com.platz.market.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Integer idCompra;

    @Column(name = "id_cliente")
    private String idCliente;

    private LocalDateTime fecha;

    @Column(name="medio_pago")
    private String medioPago;

    private String comentario;
    private String estado;

    //Relacion a la tabla cliente
    //Recuperar la informacion del cliente que esta realizando la compra
    @ManyToOne //muchas compras para un cliente
    @JoinColumn(name = "id_cliente", insertable = false, updatable = false ) // JoinColumn va el atributo que une a la tabla cliente, y no se permite insert o actualizar los clientes desde esta relacion. Si se desea crear, actualizar un cliente se hará desde entity cliente
    private Cliente cliente; //atributo privado Cliente que se llame cliente(cliente ira mapeado en la clase cliente, al hacer la relacion-  @OneToMany(mappedBy = "cliente")


    //Relacion a compras_productos
    @OneToMany(mappedBy = "compra") //producto
    private List<ComprasProducto> productos;  //lista COmprasProducto que se llama productos


    //Generate getters and setters

    public List<ComprasProducto> getProductos() {
        return productos;
    }
    public void setProductos(List<ComprasProducto> productos) {
        this.productos = productos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
