package com.example.drdp_application.controller;

import com.example.drdp_application.model.Utilisateur;
import com.example.drdp_application.services.SessionService;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/recommandation")
public class RecommandationController {

    private  final SessionService sessionService;
    private  final HttpSession session;

    public RecommandationController(HttpSession session, SessionService sessionService) {
        this.session = session;
        this.sessionService = sessionService;
    }

    @GetMapping("mesRecommandations")
    public String recommandation(Model model) {
        if(this.sessionService.isAuthenticated()){
            Utilisateur utilisateur = (Utilisateur) this.session.getAttribute("utilisateur");
            model.addAttribute("utilisateur", utilisateur);
            return "recommandation";
        }
        return "login";

    }
}
