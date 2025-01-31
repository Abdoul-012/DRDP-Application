package com.example.drdp_application.services;

import com.example.drdp_application.model.Activite;
import com.example.drdp_application.repository.ActiviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceActivite {
    @Autowired
    private ActiviteRepository activiteRepository;

    public ServiceActivite(ActiviteRepository activiteRepository) {
        this.activiteRepository = activiteRepository;
    }

    public void save(Activite activite) {
        activiteRepository.save(activite);
    }
    public List<Activite> getAllActivites() {
        return this.activiteRepository.findAll();
    }

}
