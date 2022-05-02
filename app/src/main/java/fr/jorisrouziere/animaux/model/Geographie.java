package fr.jorisrouziere.animaux.model;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Geographie {
    private Long g_id;
    private String description;
    private Animal animal;

    // TODO : A SUPPRIMER QUAND LOMBOK MARCHERA


    public Long getG_id() {
        return g_id;
    }

    public String getDescription() {
        return description;
    }

    public void setG_id(Long g_id) {
        this.g_id = g_id;
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
