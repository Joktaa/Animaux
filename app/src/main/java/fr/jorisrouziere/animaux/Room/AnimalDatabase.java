package fr.jorisrouziere.animaux.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fr.jorisrouziere.animaux.Room.DAO.AnimalDAO;
import fr.jorisrouziere.animaux.Room.converters.Converters;
import fr.jorisrouziere.animaux.Room.models.Animal;

@Database(
        entities = {
                Animal.class
        },
        version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AnimalDatabase extends RoomDatabase {

    private static volatile AnimalDatabase INSTANCE;

    public abstract AnimalDAO animalDAO();

    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService DATABASE_WRITE_EXECUTOR = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AnimalDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AnimalDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(), AnimalDatabase.class, "animal-db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
