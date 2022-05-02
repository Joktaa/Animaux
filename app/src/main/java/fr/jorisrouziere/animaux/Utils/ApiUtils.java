package fr.jorisrouziere.animaux.Utils;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.List;

import fr.jorisrouziere.animaux.Room.Repository;
import fr.jorisrouziere.animaux.Room.models.Animal;
import fr.jorisrouziere.animaux.api.API;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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

    public static void deleteAnimal(Long id) {

        new Thread(() -> {
            API api = API.getInstance();
            try {
                api.deleteAnimal(id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void postAnimal(Context applicationContext, Animal animal) {
        Repository repository = new Repository(applicationContext);

        new Thread(() -> {
            API api = API.getInstance();
            api.postAnimal(animal, new Callback() {
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    repository.insertOneAnimal(animal);
                }

                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                }
            });
        }).start();
    }

    public static void putAnimal(Context applicationContext, Animal animal) {
        Repository repository = new Repository(applicationContext);

        new Thread(() -> {
            API api = API.getInstance();
            api.putAnimal(animal, new Callback() {
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    repository.updateOneAnimal(animal);
                }

                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                }
            });
        }).start();
    }
}
