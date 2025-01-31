package com.example.drdp_application.model;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@NamedQuery(name="findProgramme",query="SELECT p FROM Programme p where p.activite.id= :ida and p.utilisateur.id= :idu")
@NamedQuery(name="findProg",query="SELECT p FROM Programme p where p.utilisateur.id= :idu")
@Table(name = "programme")
public class Programme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_programme", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_activite", referencedColumnName = "id_activite", nullable = false)
    private Activite activite;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", referencedColumnName = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @Column(name = "objectif", nullable = false)
    private String objectif;

    @Column(name = "duree", nullable = false)
    private Integer duree;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "categorie", nullable = false)
    private String categorie;

    @Column(name = "score", nullable = false)
    private Integer score;

    // Getters et setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
