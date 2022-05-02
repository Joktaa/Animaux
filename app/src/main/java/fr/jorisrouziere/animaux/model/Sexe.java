package fr.jorisrouziere.animaux.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Sexe {
    private Long s_id;
    private String description;
    private Animal animal;

    // TODO : A SUPPRIMER QUAND LOMBOK MARCHERA


    public Long getS_id() {
        return s_id;
    }

    public void setS_id(Long s_id) {
        this.s_id = s_id;
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
