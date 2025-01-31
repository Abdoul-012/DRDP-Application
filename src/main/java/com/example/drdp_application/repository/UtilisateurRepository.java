package com.example.drdp_application.repository;

import com.example.drdp_application.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    public Utilisateur findByLogin(String login);
}
