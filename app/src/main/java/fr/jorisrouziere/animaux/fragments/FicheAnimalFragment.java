package fr.jorisrouziere.animaux.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fr.jorisrouziere.animaux.R;
import fr.jorisrouziere.animaux.Room.Repository;
import fr.jorisrouziere.animaux.model.Geographie;
import fr.jorisrouziere.animaux.model.Physique;
import fr.jorisrouziere.animaux.model.Reproduction;
import fr.jorisrouziere.animaux.model.Sexe;
import fr.jorisrouziere.animaux.model.Vie;

public class FicheAnimalFragment extends Fragment {
    private Long id;

    public FicheAnimalFragment(Long _id) {
        id = _id;
    }

    public static FicheAnimalFragment newInstance(Long _id) {
        FicheAnimalFragment fragment = new FicheAnimalFragment(_id);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fiche_animal, container, false);

        Repository repository = new Repository(getContext());
        repository.getAnimalById(id).observe(getViewLifecycleOwner(), (animal -> {
            String texteAAfficher;

            TextView name = view.findViewById(R.id.textNomAnimal);
            name.setText(animal.getNom_commun());

            TextView genre = view.findViewById(R.id.descpritionGenre);
            genre.setText(animal.getGenre());

            TextView espece = view.findViewById(R.id.descpritionEspece);
            espece.setText(animal.getEspece());

            TextView embranchement = view.findViewById(R.id.descpritionEmbranchement);
            embranchement.setText(animal.getEmbranchement());

            TextView sousEmbranchement = view.findViewById(R.id.descpritionSousEmbranchement);
            sousEmbranchement.setText(animal.getSous_embranchement());

            TextView ordre = view.findViewById(R.id.descpritionOrdre);
            ordre.setText(animal.getOrdre());

            TextView uicn = view.findViewById(R.id.descpritionUicn);
            uicn.setText(animal.getUicn());

            texteAAfficher = "";
            TextView physique = view.findViewById(R.id.descpritionPhysiques);
            for (Physique p: animal.getPhysiques()) {
                texteAAfficher = "- " + p.getDescription() + "\n";
            }
            physique.setText(texteAAfficher);

            texteAAfficher = "";
            TextView sexe = view.findViewById(R.id.descpritionSexes);
            for (Sexe s: animal.getSexes()) {
                texteAAfficher = "- " + s.getDescription() + "\n";
            }
            sexe.setText(texteAAfficher);

            texteAAfficher = "";
            TextView vie = view.findViewById(R.id.descpritionVies);
            for (Vie v: animal.getVies()) {
                texteAAfficher = "- " + v.getDescription() + "\n";
            }
            vie.setText(texteAAfficher);

            texteAAfficher = "";
            TextView reproduction = view.findViewById(R.id.descpritionReproductions);
            for (Reproduction r: animal.getReproductions()) {
                texteAAfficher = "- " + r.getDescription() + "\n";
            }
            reproduction.setText(texteAAfficher);

            texteAAfficher = "";
            TextView geographie = view.findViewById(R.id.descpritionGeographie);
            for (Geographie g: animal.getGeographies()) {
                texteAAfficher = "- " + g.getDescription() + "\n";
            }
            geographie.setText(texteAAfficher);
        }));

        return view;
    }
}
