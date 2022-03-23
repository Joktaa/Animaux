package fr.jorisrouziere.animaux.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;

import fr.jorisrouziere.animaux.R;
import fr.jorisrouziere.animaux.Room.Repository;
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
public class CreateUpdateAnimal extends Fragment {

    private Long id;

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
        View view = inflater.inflate(R.layout.fragment_create_update_animal, container, false);
        id = getArguments().getLong("ID");
        if (id != 0){
            Init(view);
        }
        else{
            TextView titre = view.findViewById(R.id.titre);
            titre.setText("Création d'un animal");
        }
        return view;

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
                texteAAfficher = "- " + p.getDescription() + "\n";
            }
            physique.setText(texteAAfficher);

            texteAAfficher = "";
            EditText sexe = view.findViewById(R.id.descriptionSexes);
            for (Sexe s: animal.getSexes()) {
                texteAAfficher = "- " + s.getDescription() + "\n";
            }
            sexe.setText(texteAAfficher);

            texteAAfficher = "";
            EditText vie = view.findViewById(R.id.descriptionVies);
            for (Vie v: animal.getVies()) {
                texteAAfficher = "- " + v.getDescription() + "\n";
            }
            vie.setText(texteAAfficher);

            texteAAfficher = "";
            EditText reproduction = view.findViewById(R.id.descriptionReproductions);
            for (Reproduction r: animal.getReproductions()) {
                texteAAfficher = "- " + r.getDescription() + "\n";
            }
            reproduction.setText(texteAAfficher);

            texteAAfficher = "";
            EditText geographie = view.findViewById(R.id.descriptionGeographie);
            for (Geographie g: animal.getGeographies()) {
                texteAAfficher = "- " + g.getDescription() + "\n";
            }
            geographie.setText(texteAAfficher);
        }));
    }
}