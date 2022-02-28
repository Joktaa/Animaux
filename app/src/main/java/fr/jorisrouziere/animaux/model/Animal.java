package fr.jorisrouziere.animaux.model;


import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // A supprimer
public class Animal {
    private Long a_id;
    private String nom_commun;
    private String genre;
    private String espece;
    private String embranchement;
    private String sous_embranchement;
    private String ordre;
    private String uicn;
    private String image;
    private List<Physique> physiques;
    private List<Sexe> sexes;
    private List<Vie> vies;
    private List<Reproduction> reproductions;
    private List<Geographie> geographies;
}