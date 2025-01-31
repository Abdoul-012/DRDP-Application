package com.example.drdp_application.repository;

import com.example.drdp_application.model.Activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiviteRepository extends JpaRepository<Activite, Integer> {
}
