package fr.jorisrouziere.animaux;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.jorisrouziere.animaux.adapters.AnimalAdapter;
import fr.jorisrouziere.animaux.api.API;
import fr.jorisrouziere.animaux.model.Animal;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connexion
        new Thread(() -> {
            API api = API.getInstance();
            try {
                List<Animal> animaux =  api.getAnimal();
                System.out.println(animaux);
            } catch (API.ApiErrorException | IOException e) {
                e.printStackTrace();
            }
        }).start();


        List<Animal> animaux = new ArrayList<>();
        animaux.add(new Animal());
        animaux.add(new Animal());
        animaux.add(new Animal());

        ListView animalListView = findViewById(R.id.animal_listView);
        AnimalAdapter animalAdapter = new AnimalAdapter(getApplicationContext(), getSupportFragmentManager(), animaux);
        animalListView.setAdapter(animalAdapter);


    }
}