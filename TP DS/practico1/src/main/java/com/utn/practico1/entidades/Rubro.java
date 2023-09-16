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
public class Rubro extends BaseEntidad{
    private String denominacion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "rubro_id")
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();

    public void agregarProductos(Producto p) {
        productos.add(p);
    }

    public void mostrarProductos(){
        System.out.println("Productos del rubro: " + denominacion + ": ");
        for (Producto producto: productos){
            System.out.println("Nombre: " + producto.getDenominacion());
        }
    }
}
