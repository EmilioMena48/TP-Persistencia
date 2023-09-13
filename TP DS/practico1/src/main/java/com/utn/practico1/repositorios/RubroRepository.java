package com.utn.practico1.repositorios;

import com.utn.practico1.entidades.Cliente;
import com.utn.practico1.entidades.Rubro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RubroRepository extends JpaRepository<Rubro, Long> {
}
