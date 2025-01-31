package com.example.drdp_application.controller;

import com.example.drdp_application.model.Utilisateur;
import com.example.drdp_application.services.ServiceUtilisateur;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/log")
public class LoginController {
    private  final  ServiceUtilisateur serviceUtilisateur;
    private final HttpServletRequest request;
    HttpSession session;

    public LoginController(ServiceUtilisateur serviceUtilisateur,
                           HttpServletRequest request, HttpSession session) {
        this.serviceUtilisateur = serviceUtilisateur;
        this.request = request;
        this.session = session;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestParam String username,
                               @RequestParam String password,org.springframework.ui.Model model) {
        Optional<Utilisateur>u = this.serviceUtilisateur
                .getUtilisateur(username,password);
        if(u.isPresent()) {
            session = this.request.getSession();
            session.setAttribute("utilisateur", u.get());
//            return loginSuccessRedirect();
            model.addAttribute("utilisateur", u.get());
            return "accueil";
        }
        model.addAttribute("error", "Utlisateur introuvable");
//        return loginSuccessRedirect();
        return "login";
    }


    @GetMapping("logout")
    public String logout() {
        session=this.request.getSession();
        session.invalidate();

        return "accueil";
    }

//    public String loginSuccessRedirect() {
//        String referer = request.getHeader("Referer");
//        if (referer != null && !referer.contains("login")) {
//            return referer;
//        }
//        return "accueil"; // Page par défaut si aucune page précédente

}
