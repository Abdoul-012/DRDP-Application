package com.example.drdp_application.controller;

import com.example.drdp_application.model.Activite;
import com.example.drdp_application.model.Utilisateur;
import com.example.drdp_application.services.ServiceActivite;
import com.example.drdp_application.services.ServiceProgramme;
import com.example.drdp_application.services.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/activité")
public class ActiviteController {
    private  final ServiceActivite serviceActivite;
    private  final  ServiceProgramme serviceProgramme;
    private  final SessionService sessionService;
    private final HttpServletRequest request;
    HttpSession session;

    public ActiviteController(ServiceActivite serviceActivite,
                              HttpServletRequest request, HttpSession session, SessionService sessionService,
                              ServiceProgramme serviceProgramme) {
        this.serviceActivite = serviceActivite;
        this.request = request;
        this.session = session;
        this.sessionService = sessionService;
        this.serviceProgramme = serviceProgramme;
    }


    @GetMapping("/mes-activités")
    public String activiteHomme(org.springframework.ui.Model model) {
        List<Activite> activiteList=this.serviceActivite.
                getAllActivites();
        if(activiteList==null) {
            Utilisateur utilisateur = this.sessionService.getUtilisateur();
            model.addAttribute("utilisateur", utilisateur);
            model.addAttribute("message","Aucune Activite dans la base de données");
            return "activite";
        }
        Utilisateur utilisateur = this.sessionService.getUtilisateur();
        model.addAttribute("activites", activiteList);
        model.addAttribute("utilisateur", utilisateur);
        return "activite";
    }


    @PostMapping("/ajout")
    public String ajoutActivite(@RequestParam String id, @RequestParam String duree, Model model) {
        if(!this.sessionService.isAuthenticated()){
            return "login";
        }
        int id_Activite = Integer.parseInt(id);
        int duree_Activite = Integer.parseInt(duree);
        session=this.request.getSession();
        Utilisateur utilisateur=(Utilisateur) session.getAttribute("utilisateur");
        String confirmation=this.serviceProgramme.saveActivite(utilisateur,id_Activite,duree_Activite,model);

        if(confirmation.equals("ok")){
            List<Activite> activiteList=this.serviceActivite.
                    getAllActivites();
            model.addAttribute("activites", activiteList);
            model.addAttribute("utilisateur", utilisateur);
            model.addAttribute("succes","cette Activité  a été ajouter à votre programme");
            return "activite";

        }
        model.addAttribute("error","cette Activité  existe deja dans votre programme");
        List<Activite> activiteList=this.serviceActivite.
                getAllActivites();
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("activites", activiteList);
        return "activite";
    }


}

