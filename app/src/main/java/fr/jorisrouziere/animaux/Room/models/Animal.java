package fr.jorisrouziere.animaux.Room.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

import fr.jorisrouziere.animaux.model.Geographie;
import fr.jorisrouziere.animaux.model.Physique;
import fr.jorisrouziere.animaux.model.Reproduction;
import fr.jorisrouziere.animaux.model.Sexe;
import fr.jorisrouziere.animaux.model.Vie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(tableName = "animal")
public class Animal implements Serializable {

    @PrimaryKey
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

    // SUPPRIMER QUAND LOMBOK FONCTIONNERA

    public Long getA_id() {
        return a_id;
    }

    public String getNom_commun() {
        return nom_commun;
    }

    public String getGenre() {
        return genre;
    }

    public String getEspece() {
        return espece;
    }

    public String getEmbranchement() {
        return embranchement;
    }

    public String getSous_embranchement() {
        return sous_embranchement;
    }

    public String getOrdre() {
        return ordre;
    }

    public String getUicn() {
        return uicn;
    }

    public String getImage() {
        return image;
    }

    public List<Physique> getPhysiques() {
        return physiques;
    }

    public List<Sexe> getSexes() {
        return sexes;
    }

    public List<Vie> getVies() {
        return vies;
    }

    public List<Reproduction> getReproductions() {
        return reproductions;
    }

    public List<Geographie> getGeographies() {
        return geographies;
    }



    public void setA_id(Long a_id) {
        this.a_id = a_id;
    }

    public void setNom_commun(String nom_commun) {
        this.nom_commun = nom_commun;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

    public void setEmbranchement(String embranchement) {
        this.embranchement = embranchement;
    }

    public void setSous_embranchement(String sous_embranchement) {
        this.sous_embranchement = sous_embranchement;
    }

    public void setOrdre(String ordre) {
        this.ordre = ordre;
    }

    public void setUicn(String uicn) {
        this.uicn = uicn;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPhysiques(List<Physique> physiques) {
        this.physiques = physiques;
    }

    public void setSexes(List<Sexe> sexes) {
        this.sexes = sexes;
    }

    public void setVies(List<Vie> vies) {
        this.vies = vies;
    }

    public void setReproductions(List<Reproduction> reproductions) {
        this.reproductions = reproductions;
    }

    public void setGeographies(List<Geographie> geographies) {
        this.geographies = geographies;
    }
}
