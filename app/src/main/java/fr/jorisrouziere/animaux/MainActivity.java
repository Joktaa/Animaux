package fr.jorisrouziere.animaux;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import fr.jorisrouziere.animaux.adapters.AnimalAdapter;
import fr.jorisrouziere.animaux.model.Animal;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Animal> animaux = new ArrayList<>();

        animaux.add(new Animal());
        animaux.add(new Animal());
        animaux.add(new Animal());

        ListView animalListView = findViewById(R.id.animal_listView);
        AnimalAdapter animalAdapter = new AnimalAdapter(getApplicationContext(), getSupportFragmentManager(), animaux);
        animalListView.setAdapter(animalAdapter);
        animalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent fen = new Intent(MainActivity.this, AfficheAnimal.class);
                startActivity(fen);
            }
        });
    }
}