package com.example.drdp_application.controller;

import com.example.drdp_application.model.Activite;
import com.example.drdp_application.model.Utilisateur;
import com.example.drdp_application.services.ServiceProgramme;
import com.example.drdp_application.services.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/profil")
public class ProfilController {
    HttpServletRequest request;
    private  final  HttpSession session;
    private  final SessionService sessionService;
    private  final ServiceProgramme serviceProgramme;

    public ProfilController(HttpServletRequest request, HttpSession session, SessionService sessionService,ServiceProgramme serviceProgramme) {
        this.request = request;
        this.session = session;
        this.sessionService = sessionService;
        this.serviceProgramme = serviceProgramme;
    }

    @GetMapping("/monProfil")
    public String profil(org.springframework.ui.Model model) {
        if(this.sessionService.isAuthenticated()){

            Utilisateur utilisateur = (Utilisateur) this.session.getAttribute("utilisateur");

            List<Activite> programmes = this.serviceProgramme.ativiteDuProgramme();

            if(programmes!=null){
                model.addAttribute("programmes", programmes);
                model.addAttribute("utilisateur", utilisateur);
                return "profil";
            }
            System.out.println("profil vide");
            model.addAttribute("utilisateur", utilisateur);
            model.addAttribute("message", "Vous n'avez aucune activé dans votre programme");

            return "profil";
        }
        return "login";
    }

}
