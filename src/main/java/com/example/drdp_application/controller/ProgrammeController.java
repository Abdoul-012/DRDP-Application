package com.example.drdp_application.controller;

import com.example.drdp_application.model.Activite;
import com.example.drdp_application.model.Programme;
import com.example.drdp_application.model.Utilisateur;
import com.example.drdp_application.services.ServiceProgramme;
import com.example.drdp_application.services.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/programme")
public class ProgrammeController {
    private  final ServiceProgramme serviceProgramme;
    private  final  SessionService sessionService;

    public ProgrammeController(ServiceProgramme serviceProgramme,SessionService sessionService) {
        this.serviceProgramme = serviceProgramme;
        this.sessionService = sessionService;

    }

    @GetMapping("/monprogramme")
    public String monprogramme(Model model) {
        if(this.sessionService.isAuthenticated()){
            List<Activite> activiteList= this.serviceProgramme.ativiteDuProgramme();
            Utilisateur utilisateur= this.sessionService.getUtilisateur();
            List<Programme> programmeList =this.serviceProgramme.programmeUtilisateur();
            return getString(model, programmeList, activiteList, utilisateur);
        }
        return "login";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id,Model model) {
        if(this.sessionService.isAuthenticated()){
            this.serviceProgramme.deleteProgramme(Integer.parseInt(id));
            List<Programme> programmeList =this.serviceProgramme.programmeUtilisateur();
            List<Activite> activiteList= this.serviceProgramme.ativiteDuProgramme();
            System.out.println("id a suprimer "+ id);

            Utilisateur utilisateur= this.sessionService.getUtilisateur();
            return getString(model, programmeList, activiteList, utilisateur);
        }
        return "login";
    }

    private String getString(Model model, List<Programme> programmeList, List<Activite> activiteList, Utilisateur utilisateur) {
        if(programmeList==null || programmeList.isEmpty()){
            model.addAttribute("error","Vous n'avez aucune activité dans votre programme");
            model.addAttribute("utilisateur", utilisateur);
            return "programme";
        }
        model.addAttribute("activiteList",activiteList);
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("programmeList",programmeList);
        return "programme";
    }
}
