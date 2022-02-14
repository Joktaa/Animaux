package fr.jorisrouziere.animaux.activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


import fr.jorisrouziere.animaux.R;
import fr.jorisrouziere.animaux.Room.Repository;
import fr.jorisrouziere.animaux.adapters.AnimalAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository repository = new Repository(getApplicationContext());

        repository.getAnimaux().observe(this, (animaux) -> {
            ListView animalListView = findViewById(R.id.animal_listView);
            AnimalAdapter animalAdapter = new AnimalAdapter(getApplicationContext(), getSupportFragmentManager(), animaux);
            animalListView.setAdapter(animalAdapter);
        });

    }
}