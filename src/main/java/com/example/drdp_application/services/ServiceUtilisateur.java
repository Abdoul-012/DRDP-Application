package com.example.drdp_application.services;

import com.example.drdp_application.model.Utilisateur;
import com.example.drdp_application.repository.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceUtilisateur {

    private  final PasswordEncoder passwordEncoder;
    private  final   UtilisateurRepository utilisateurRepository;
    SessionService sessionService;

    public ServiceUtilisateur(PasswordEncoder passwordEncoder, UtilisateurRepository utilisateurRepository,SessionService sessionService) {
        this.passwordEncoder = passwordEncoder;
        this.utilisateurRepository = utilisateurRepository;
        this.sessionService = sessionService;
    }

    public boolean newUtilisateur(Utilisateur utilisateur) {
        try {
            String mdp = utilisateur.getMdp();
            String hashedPassword = this.passwordEncoder.encode(mdp);
            utilisateur.setMdp(hashedPassword);
            this.utilisateurRepository.save(utilisateur);
            return true;
        } catch (Exception e) {// Log des erreurs
            return false;
        }
    }

    public Optional<Utilisateur> getUtilisateur(String login , String password) {
        Utilisateur utilisateur = this.utilisateurRepository.findByLogin(login);
        if(utilisateur != null && this.passwordEncoder.matches
                (password, utilisateur.getMdp())) {
            return Optional.of(utilisateur);
        }
        return Optional.empty();
    }


}


