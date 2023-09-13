package com.utn.practico1.entidades;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder



public class Pedido extends BaseEntidad{

    public enum Estado {
        iniciado,
        preparacion,
        entregado
    }

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Temporal(TemporalType.DATE)
    private Date fecha = new Date();

    public enum TipoEnvio{
        delivery,
        retira
    }

    @Enumerated(EnumType.STRING)
    private TipoEnvio tipoEnvio;

    private double total;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "factura_id")
    private Factura factura;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    @Builder.Default
    private List<DetallePedido> DetPedidos = new ArrayList<>();


    public void agregarDetallesPedido(DetallePedido d) {
        DetPedidos.add(d);
    }

    public void mostrarDetalllesPedido(){
        System.out.println("Detalle del Pedido:" + getId() + ": ");
        for (DetallePedido detallePedido: DetPedidos){
            System.out.println("Detalle n° " + detallePedido.getId() + "Cantidad: " + detallePedido.getCantidad() + ", Subtotal: " + detallePedido.getSubtotal());
        }
    }
}
