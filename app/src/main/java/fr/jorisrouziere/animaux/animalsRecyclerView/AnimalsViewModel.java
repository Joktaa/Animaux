package fr.jorisrouziere.animaux.animalsRecyclerView;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.jorisrouziere.animaux.Room.Repository;
import fr.jorisrouziere.animaux.Room.models.Animal;

public class AnimalsViewModel extends AndroidViewModel {
    private final Repository repository;

    public AnimalsViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<List<Animal>> getAnimals() {
        return repository.getAnimals();
    }
    public LiveData<Animal> getAnimalById(Long id) {
        return repository.getAnimalById(id);
    }
    public LiveData<List<Animal>> getAnimalsByArbre(String arbre) {
        return repository.getAnimalsByArbre(arbre);
    }
}
