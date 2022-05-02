package fr.jorisrouziere.animaux.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Vie {
    private Long v_id;
    private String description;
    private Animal animal;

    // TODO : A SUPPRIMER QUAND LOMBOK MARCHERA


    public Long getV_id() {
        return v_id;
    }

    public void setV_id(Long v_id) {
        this.v_id = v_id;
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
