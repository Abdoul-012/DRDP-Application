package com.example.drdp_application.services;

import com.example.drdp_application.model.Utilisateur;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    HttpSession session;
    HttpServletRequest request;

    public SessionService(HttpServletRequest request, HttpSession session) {
        this.request = request;
        this.session = session;
    }

    public boolean isAuthenticated() {
        session = this.request.getSession();
        return session.getAttribute("utilisateur") != null;
    }

    public Utilisateur getUtilisateur() {
        session = this.request.getSession();
        return (Utilisateur) session.getAttribute("utilisateur");
    }
}
