package fr.jorisrouziere.animaux.Room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import fr.jorisrouziere.animaux.Room.models.Animal;

@Dao
public interface AnimalDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Animal> animalList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(Animal animal);

    @Query("SELECT * FROM animal")
    LiveData<List<Animal>> getAnimals();

    @Query("SELECT * FROM animal " +
            "WHERE a_id = :id")
    LiveData<Animal> getAnimalById(Long id);

    @Query("DELETE FROM animal " +
            "WHERE a_id = :id")
    void deleteById(Long id);

    @Query("DELETE FROM animal")
    void deleteAll();
}
