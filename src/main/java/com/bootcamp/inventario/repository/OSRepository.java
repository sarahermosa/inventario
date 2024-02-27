package com.bootcamp.inventario.repository;

import com.bootcamp.inventario.model.OS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OSRepository extends JpaRepository<OS, Integer> {

}

