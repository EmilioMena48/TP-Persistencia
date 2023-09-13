package com.utn.practico1.repositorios;

import com.utn.practico1.entidades.Cliente;
import com.utn.practico1.entidades.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
