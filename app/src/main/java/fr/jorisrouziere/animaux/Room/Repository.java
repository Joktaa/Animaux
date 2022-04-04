package fr.jorisrouziere.animaux.Room;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.jorisrouziere.animaux.Room.DAO.AnimalDAO;
import fr.jorisrouziere.animaux.Room.models.Animal;

public class Repository {

    private final AnimalDAO mAnimalDAO;

    public Repository(Context context) {
        AnimalDatabase db = AnimalDatabase.getDatabase(context);
        mAnimalDAO = db.animalDAO();
    }

    public void insertAllAnimals(List<Animal> animalList) {
        AnimalDatabase.DATABASE_WRITE_EXECUTOR.execute(() -> mAnimalDAO.insertAll(animalList));
    }

    public void insertOneAnimal(Animal animal) {
        AnimalDatabase.DATABASE_WRITE_EXECUTOR.execute(() -> mAnimalDAO.insertOne(animal));
    }

    public void updateOneAnimal(Animal animal) {
        AnimalDatabase.DATABASE_WRITE_EXECUTOR.execute(() -> mAnimalDAO.update(animal));
    }

    public LiveData<List<Animal>> getAnimals() {
        return mAnimalDAO.getAnimals();
    }

    public LiveData<Animal> getAnimalById(Long id) {
        return mAnimalDAO.getAnimalById(id);
    }

    public void deleteById(Long id) {
        mAnimalDAO.deleteById(id);
    }

    public void reset() {
        mAnimalDAO.deleteAll();
    }
}
