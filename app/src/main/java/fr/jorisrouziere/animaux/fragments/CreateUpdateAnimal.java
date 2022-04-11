package fr.jorisrouziere.animaux.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.jorisrouziere.animaux.R;
import fr.jorisrouziere.animaux.Room.Repository;
import fr.jorisrouziere.animaux.Room.models.Animal;
import fr.jorisrouziere.animaux.Utils.ApiUtils;
import fr.jorisrouziere.animaux.model.Geographie;
import fr.jorisrouziere.animaux.model.Physique;
import fr.jorisrouziere.animaux.model.Reproduction;
import fr.jorisrouziere.animaux.model.Sexe;
import fr.jorisrouziere.animaux.model.Vie;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateUpdateAnimal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateUpdateAnimal extends Fragment implements View.OnClickListener {

    private Long id;
    View view;
    Button saveAnimal;

    public CreateUpdateAnimal(Long _id) {
        id = _id;
    }
    public CreateUpdateAnimal() { }


    public static CreateUpdateAnimal newInstance(Long _id) {
        CreateUpdateAnimal fragment = new CreateUpdateAnimal(_id);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create_update_animal, container, false);
        id = getArguments().getLong("ID");
        if (id != 0){
            Init(view);
        }
        else{
            TextView titre = view.findViewById(R.id.titre);
            titre.setText("Création d'un animal");
        }

        saveAnimal = view.findViewById(R.id.buttonSaveAnimal);
        saveAnimal.setOnClickListener(this); //on button appay click listner

        return view;

    }

    public Animal InitAnimal(View view){
        Animal animal = new Animal();
        if (this.id != 0){
            animal.setA_id(this.id);
        }

        EditText nomCommun = (EditText) view.findViewById(R.id.descriptionNom);
        if (nomCommun.getText().toString().isEmpty()){
            Toast.makeText(this.getContext(), "Vous n'avez pas saisie le nom commun de l'animal", Toast.LENGTH_SHORT).show();
            return null;
        }
        else{
            animal.setNom_commun(nomCommun.getText().toString());
        }


        EditText nomImage = view.findViewById(R.id.descriptionNomImage);
        if (nomImage.getText().toString().isEmpty()){
            Toast.makeText(this.getContext(), "Vous n'avez pas saisie l'image de l'animal", Toast.LENGTH_SHORT).show();
            return null;
        }
        else{
            animal.setImage(nomImage.getText().toString());
        }

        EditText genre = view.findViewById(R.id.descriptionGenre);
        if (genre.getText().toString().isEmpty()){
            Toast.makeText(this.getContext(), "Vous n'avez pas saisie le genre de l'animal", Toast.LENGTH_SHORT).show();
            return null;
        }
        else{
            animal.setGenre(genre.getText().toString());
        }

        EditText espece = view.findViewById(R.id.descriptionEspece);
        if (espece.getText().toString().isEmpty()){
            Toast.makeText(this.getContext(), "Vous n'avez pas saisie l'espèce de l'animal", Toast.LENGTH_SHORT).show();
            return null;
        }
        else{
            animal.setEspece(espece.getText().toString());
        }

        EditText embranchement = view.findViewById(R.id.descriptionEmbranchement);
        if (embranchement.getText().toString().isEmpty()){
            Toast.makeText(this.getContext(), "Vous n'avez pas saisie l'embranchement de l'animal", Toast.LENGTH_SHORT).show();
            return null;
        }
        else{
            animal.setEmbranchement(embranchement.getText().toString());
        }

        EditText sousEmbranchement = view.findViewById(R.id.descriptionSousEmbranchement);
        if (sousEmbranchement.getText().toString().isEmpty()){
            Toast.makeText(this.getContext(), "Vous n'avez pas saisie le sous-embranchement de l'animal", Toast.LENGTH_SHORT).show();
            return null;
        }
        else{
            animal.setSous_embranchement(sousEmbranchement.getText().toString());
        }

        EditText ordre = view.findViewById(R.id.descriptionOrdre);
        if (ordre.getText().toString().isEmpty()){
            Toast.makeText(this.getContext(), "Vous n'avez pas saisie l'ordre de l'animal", Toast.LENGTH_SHORT).show();
            return null;
        }
        else{
            animal.setOrdre(ordre.getText().toString());
        }

        EditText uicn = view.findViewById(R.id.descriptionUicn);
        if (uicn.getText().toString().isEmpty()){
            Toast.makeText(this.getContext(), "Vous n'avez pas saisie l'uicn de l'animal", Toast.LENGTH_SHORT).show();
            return null;
        }
        else{
            animal.setUicn(uicn.getText().toString());
        }

        EditText EditPhysique = view.findViewById(R.id.descriptionPhysiques);
        if (EditPhysique.getText().toString().isEmpty()){
            Toast.makeText(this.getContext(), "Vous n'avez pas saisie les caractéristiques physiques de l'animal", Toast.LENGTH_SHORT).show();
            return null;
        }
        else{
            Physique physique = new Physique();
            physique.setDescription( EditPhysique.getText().toString().replace(",", "; "));
            List<Physique> physiqueList = new ArrayList<>();
            physiqueList.add(physique);
            animal.setPhysiques(physiqueList);
            System.out.println(physiqueList.toString());
            System.out.println(animal.toString());
        }

        EditText sexe = view.findViewById(R.id.descriptionSexes);
        if (sexe.getText().toString().isEmpty()){
            Toast.makeText(this.getContext(), "Vous n'avez pas saisie les caractéristiques du sexe de l'animal", Toast.LENGTH_SHORT).show();
            return null;
        }
        else{
            Sexe sexe1 = new Sexe();
            sexe1.setDescription(sexe.getText().toString().replace(",","; "));
            List<Sexe> sexeList = new ArrayList<>();
            sexeList.add(sexe1);
            animal.setSexes(sexeList);
        }

        EditText vie = view.findViewById(R.id.descriptionVies);
        if (vie.getText().toString().isEmpty()){
            Toast.makeText(this.getContext(), "Vous n'avez pas saisie les caractéristiques de la vie de l'animal", Toast.LENGTH_SHORT).show();
            return null;
        }
        else{
            Vie vie1 = new Vie();
            vie1.setDescription(vie.getText().toString().replace(",","; "));
            List<Vie> vieList = new ArrayList<>();
            vieList.add(vie1);
            animal.setVies(vieList);
        }

        EditText reproduction = view.findViewById(R.id.descriptionReproductions);
        if (reproduction.getText().toString().isEmpty()){
            Toast.makeText(this.getContext(), "Vous n'avez pas saisie les caractéristiques de reproduction de l'animal", Toast.LENGTH_SHORT).show();
            return null;
        }
        else{
            Reproduction reproduction1 = new Reproduction();
            List<Reproduction> reproductionList = new ArrayList<>();
            reproduction1.setDescription(reproduction.getText().toString().replace(",","; "));
            reproductionList.add(reproduction1);
            animal.setReproductions(reproductionList);
        }

        EditText geographie = view.findViewById(R.id.descriptionGeographie);
        if (geographie.getText().toString().isEmpty()){
            Toast.makeText(this.getContext(), "Vous n'avez pas saisie les caractéristiques de localisation de l'animal", Toast.LENGTH_SHORT).show();
            return null;
        }
        else{
            Geographie geographie1 = new Geographie();
            List<Geographie> geographieList = new ArrayList<>();
            geographie1.setDescription(geographie.getText().toString().replace(",","; "));
            geographieList.add(geographie1);
            animal.setGeographies(geographieList);
        }
        return animal;
    }

    public void Init(View view){
        Repository repository = new Repository(getContext());
        repository.getAnimalById(id).observe(getViewLifecycleOwner(), (animal -> {
            String texteAAfficher;

            TextView titre = view.findViewById(R.id.titre);
            titre.setText("Modification de " + animal.getNom_commun());

            EditText nomCommun = view.findViewById(R.id.descriptionNom);
            nomCommun.setText(animal.getNom_commun());

            EditText nomImage = view.findViewById(R.id.descriptionNomImage);
            nomImage.setText(animal.getImage());

            EditText genre = view.findViewById(R.id.descriptionGenre);
            genre.setText(animal.getGenre());

            EditText espece = view.findViewById(R.id.descriptionEspece);
            espece.setText(animal.getEspece());

            EditText embranchement = view.findViewById(R.id.descriptionEmbranchement);
            embranchement.setText(animal.getEmbranchement());

            EditText sousEmbranchement = view.findViewById(R.id.descriptionSousEmbranchement);
            sousEmbranchement.setText(animal.getSous_embranchement());

            EditText ordre = view.findViewById(R.id.descriptionOrdre);
            ordre.setText(animal.getOrdre());

            EditText uicn = view.findViewById(R.id.descriptionUicn);
            uicn.setText(animal.getUicn());

            texteAAfficher = "";
            EditText physique = view.findViewById(R.id.descriptionPhysiques);
            for (Physique p: animal.getPhysiques()) {
                texteAAfficher += p.getDescription().toString().replace(";", "; ");
            }
            physique.setText(texteAAfficher);

            texteAAfficher = "";
            EditText sexe = view.findViewById(R.id.descriptionSexes);
            for (Sexe s: animal.getSexes()) {
                texteAAfficher += s.getDescription().replace(";", "; ");
            }
            sexe.setText(texteAAfficher);

            texteAAfficher = "";
            EditText vie = view.findViewById(R.id.descriptionVies);
            for (Vie v: animal.getVies()) {
                texteAAfficher += v.getDescription().replace(";","; ");
            }
            vie.setText(texteAAfficher);

            texteAAfficher = "";
            EditText reproduction = view.findViewById(R.id.descriptionReproductions);
            for (Reproduction r: animal.getReproductions()) {
                texteAAfficher += r.getDescription().replace(";", "; ");
            }
            reproduction.setText(texteAAfficher);

            texteAAfficher = "";
            EditText geographie = view.findViewById(R.id.descriptionGeographie);
            for (Geographie g: animal.getGeographies()) {
                texteAAfficher += g.getDescription().replace(";", "; ");
            }
            geographie.setText(texteAAfficher);
        }));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())// on run time get id what button os click and get id
        {
            case R.id.buttonSaveAnimal:        // it mean if button1 click then this work
                Animal animal = InitAnimal(this.view);
                if (animal != null && this.id == 0){
                    ApiUtils.postAnimal(this.view.getContext(), animal);
                }
                else if(animal != null && this.id != 0 ){
                    ApiUtils.putAnimal(this.view.getContext(), animal);
                }
                break;
        }
    }
}