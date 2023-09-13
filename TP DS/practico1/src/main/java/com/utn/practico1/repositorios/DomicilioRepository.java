package com.utn.practico1.repositorios;

import com.utn.practico1.entidades.Cliente;
import com.utn.practico1.entidades.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {
}
