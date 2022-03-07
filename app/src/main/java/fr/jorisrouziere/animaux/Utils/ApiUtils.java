package fr.jorisrouziere.animaux.Utils;

import android.content.Context;

import java.io.IOException;
import java.util.List;

import fr.jorisrouziere.animaux.Room.Repository;
import fr.jorisrouziere.animaux.Room.models.Animal;
import fr.jorisrouziere.animaux.api.API;

public class ApiUtils {

    public static void getAnimals(Context applicationContext, Context activityContext) {
        Repository repository = new Repository(applicationContext);

        new Thread(() -> {
            API api = API.getInstance();
            try {
                List<Animal> animaux =  api.getAnimaux();
                repository.insertAllAnimals(animaux);
                ActivityUtils.goToMainActivity(activityContext);
            } catch (API.ApiErrorException | IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void deleteAnimal(Context applicationContext, Long id) {
        Repository repository = new Repository(applicationContext);

        new Thread(() -> {
            API api = API.getInstance();
            try {
                if (api.deleteAnimal(id)) {
                    repository.deleteById(id);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
