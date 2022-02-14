package fr.jorisrouziere.animaux.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import fr.jorisrouziere.animaux.R;
import fr.jorisrouziere.animaux.Room.Repository;
import fr.jorisrouziere.animaux.Utils.ActivityUtils;
import fr.jorisrouziere.animaux.model.Geographie;
import fr.jorisrouziere.animaux.model.Physique;
import fr.jorisrouziere.animaux.model.Reproduction;
import fr.jorisrouziere.animaux.model.Sexe;
import fr.jorisrouziere.animaux.model.Vie;

public class FicheAnimal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_animal);

        Intent intent = getIntent();
        Long id = intent.getLongExtra(ActivityUtils.idTag, 0L);

        Repository repository = new Repository(this);
        repository.getAnimalById(id).observe(this, (animal -> {
            String texteAAfficher;

            TextView name = findViewById(R.id.textNomAnimal);
            name.setText(animal.getNom_commun());

            TextView genre = findViewById(R.id.descpritionGenre);
            genre.setText(animal.getGenre());

            TextView espece = findViewById(R.id.descpritionEspece);
            espece.setText(animal.getEspece());

            TextView embranchement = findViewById(R.id.descpritionEmbranchement);
            embranchement.setText(animal.getEmbranchement());

            TextView sousEmbranchement = findViewById(R.id.descpritionSousEmbranchement);
            sousEmbranchement.setText(animal.getSous_embranchement());

            TextView ordre = findViewById(R.id.descpritionOrdre);
            ordre.setText(animal.getOrdre());

            TextView uicn = findViewById(R.id.descpritionUicn);
            uicn.setText(animal.getUicn());

            texteAAfficher = "";
            TextView physique = findViewById(R.id.descpritionPhysiques);
            for (Physique p: animal.getPhysiques()) {
                texteAAfficher = "- " + p.getDescription() + "\n";
            }
            physique.setText(texteAAfficher);

            texteAAfficher = "";
            TextView sexe = findViewById(R.id.descpritionSexes);
            for (Sexe s: animal.getSexes()) {
                texteAAfficher = "- " + s.getDescription() + "\n";
            }
            sexe.setText(texteAAfficher);

            texteAAfficher = "";
            TextView vie = findViewById(R.id.descpritionVies);
            for (Vie v: animal.getVies()) {
                texteAAfficher = "- " + v.getDescription() + "\n";
            }
            vie.setText(texteAAfficher);

            texteAAfficher = "";
            TextView reproduction = findViewById(R.id.descpritionReproductions);
            for (Reproduction r: animal.getReproductions()) {
                texteAAfficher = "- " + r.getDescription() + "\n";
            }
            reproduction.setText(texteAAfficher);

            texteAAfficher = "";
            TextView geographie = findViewById(R.id.descpritionGeographie);
            for (Geographie g: animal.getGeographies()) {
                texteAAfficher = "- " + g.getDescription() + "\n";
            }
            geographie.setText(texteAAfficher);
        }));
    }
}