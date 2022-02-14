package fr.jorisrouziere.animaux.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import fr.jorisrouziere.animaux.R;

public class AfficheAnimal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_animal);
    }
}