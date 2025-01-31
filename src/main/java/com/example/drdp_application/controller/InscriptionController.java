package com.example.drdp_application.controller;

import com.example.drdp_application.model.Utilisateur;
import com.example.drdp_application.services.ServiceUtilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inscription")

public class InscriptionController {

    private final ServiceUtilisateur serviceUtilisateur;

    public InscriptionController(ServiceUtilisateur serviceUtilisateur) {
        this.serviceUtilisateur = serviceUtilisateur;
    }

    @GetMapping("/inscriptionForm")
    public String inscriptionForm(org.springframework.ui.Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "inscriptionForm";
    }

    @PostMapping("/new")
    public String newInscription(Utilisateur utilisateur) {
        this.serviceUtilisateur.newUtilisateur(utilisateur);
        return "inscriptionForm";
    }
}
