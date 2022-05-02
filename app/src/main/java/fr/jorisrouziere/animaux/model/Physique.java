package fr.jorisrouziere.animaux.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Physique {
    private Long p_id;
    private String description;
    private Animal animal;

    // TODO : A SUPPRIMER QUAND LOMBOK MARCHERA

    public Long getP_id() {
        return p_id;
    }

    public void setP_id(Long p_id) {
        this.p_id = p_id;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
