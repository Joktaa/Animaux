package fr.jorisrouziere.animaux.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;

import fr.jorisrouziere.animaux.R;
import fr.jorisrouziere.animaux.Room.Repository;
import fr.jorisrouziere.animaux.Room.models.Animal;
import fr.jorisrouziere.animaux.Utils.ActivityUtils;
import fr.jorisrouziere.animaux.api.API;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        Repository repository = new Repository(getApplicationContext());

        // Connexion
        new Thread(() -> {
            API api = API.getInstance();
            try {
                List<Animal> animaux =  api.getAnimaux();
                repository.insertAllAnimals(animaux);
                ActivityUtils.goToMainActivity(this);
            } catch (API.ApiErrorException | IOException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
