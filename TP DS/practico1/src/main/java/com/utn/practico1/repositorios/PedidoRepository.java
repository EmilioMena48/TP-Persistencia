package com.utn.practico1.repositorios;

import com.utn.practico1.entidades.Cliente;
import com.utn.practico1.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
