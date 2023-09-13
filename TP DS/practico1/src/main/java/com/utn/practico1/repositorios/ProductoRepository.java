package com.utn.practico1.repositorios;

import com.utn.practico1.entidades.Cliente;
import com.utn.practico1.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
