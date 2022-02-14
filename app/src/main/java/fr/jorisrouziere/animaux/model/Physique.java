package fr.jorisrouziere.animaux.model;

import lombok.Getter;

@Getter
public class Physique {
    private Long p_id;
    private String description;
    private Animal animal;

    // TODO : A SUPPRIMER QUAND LOMBOK MARCHERA

    public String getDescription() {
        return description;
    }
}
