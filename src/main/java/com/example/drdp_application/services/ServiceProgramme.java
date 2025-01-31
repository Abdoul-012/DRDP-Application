package com.example.drdp_application.services;

import com.example.drdp_application.model.Activite;
import com.example.drdp_application.model.Programme;
import com.example.drdp_application.model.Utilisateur;
import com.example.drdp_application.repository.ActiviteRepository;
import com.example.drdp_application.repository.ProgrammeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceProgramme {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    ProgrammeRepository programmeRepository;
    @Autowired
    ActiviteRepository activiteRepository;
    HttpSession session;
    HttpServletRequest request;

    public ServiceProgramme(ProgrammeRepository programmeRepository,
                            ActiviteRepository activiteRepository, HttpSession session, HttpServletRequest request,EntityManager em) {
        this.programmeRepository = programmeRepository;
        this.activiteRepository = activiteRepository;
        this.session = session;
        this.request = request;
        this.em = em;
    }
    public Programme getProgrammeDouble(int idUtilisateur, int idActivite) {
        try {
            return this.em.createNamedQuery("findProgramme",Programme.class)
                    .setParameter("ida",idActivite).setParameter("idu",idUtilisateur).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    public String saveActivite(Utilisateur utilisateur, int idActivite, int duree, Model model) {
        Optional<Activite> activite = activiteRepository.findById(idActivite);
        if(activite.isPresent()){
           Programme programmeDouble = getProgrammeDouble(utilisateur.getId(), idActivite);
            if(programmeDouble==null){
                Programme programme = createProgramme();
                String objectif=activite.get().getDescription();
                programme.setObjectif(objectif);
                programme.setDuree(duree);
                programme.setUtilisateur(utilisateur);  // objet utilisateur
                programme.setActivite(activite.get());
                programme.setCategorie(activite.get().getCategorie());
                programme.setScore(0);// objet activite

                LocalDate date=getDate();
                programme.setDate(date);

                this.programmeRepository.save(programme);
               return "ok";
            }
        }
        return "error";

    }

    public LocalDate getDate(){
        return LocalDate.now();
    }
    public Programme createProgramme() {
        Programme programme = new Programme();
        return programme;
    }

    public List<Activite> ativiteDuProgramme() {
       List<Programme> programmeList= programmeUtilisateur();
       if(programmeList==null){
           return null;
       }
        List<Activite> activiteList =new ArrayList<>();
        for(Programme programme:programmeList){
            Activite activite = activiteRepository.findById(programme.getActivite().getId()).get();
            activiteList.add(activite);
        }
         return activiteList;
    }

    public List<Programme> programmeUtilisateur() {
        Utilisateur utilisateur = (Utilisateur) this.session.getAttribute("utilisateur");
        int id_utilisateur=utilisateur.getId();
        List<Programme> programmeList= this.em.createNamedQuery("findProg",Programme.class)
                .setParameter("idu",id_utilisateur).getResultList();
        if(programmeList.isEmpty()){
            return null;
        }
        return programmeList;
    }
    public void deleteProgramme(int idProgramme) {
        if (programmeRepository.existsById(idProgramme)) {
            programmeRepository.deleteById(idProgramme);
            System.out.println("Programme supprimé avec succès : " + idProgramme);
        } else {
            System.out.println("Programme introuvable avec ID : " + idProgramme);
        }
    }
}
