package com.utn.practico1.entidades;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente extends BaseEntidad{
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @Builder.Default
    private List<Domicilio> domicilios = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();

    public void agregarDomicilio(Domicilio domi) {
        domicilios.add(domi);
    }

    public void mostrarDomicilios(){
        System.out.println("Domicilios de " + nombre + apellido + ": ");
        for (Domicilio domicilio: domicilios){
            System.out.println("Calle: " + domicilio.getCalle() + ", Número: " + domicilio.getNumero() + ", Localidad: " + domicilio.getLocalidad());
        }
    }

    public void agregarPedidos(Pedido p) {
        pedidos.add(p);
    }

    public void mostrarPedidos() {
        System.out.println("Pedidos de " + nombre + apellido + ": ");
        for (Pedido pedido : pedidos) {
            System.out.println("ID: " + pedido.getId() + "Estado: " + pedido.getEstado() + ", Fecha: " + pedido.getFecha() + ", Tipo de envío: " + pedido.getTipoEnvio() + ", Total: " + pedido.getTotal());
            int contador = 0;
            for (DetallePedido detalle : pedido.getDetPedidos()) {
                contador += 1;
                System.out.println("Producto " + contador + ": " + detalle.getProducto().getDenominacion() + "  cantidad: " + detalle.getCantidad() + "  subtotal: " + detalle.getSubtotal());
            }
        }
    }
}
