package fr.jorisrouziere.animaux.fragments;

import static android.content.ContentValues.TAG;

import android.app.SearchManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

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
    public FicheAnimalFragment() { }

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

        id = FicheAnimalFragmentArgs.fromBundle(getArguments()).getId();

        Repository repository = new Repository(getContext());
        repository.getAnimalById(id).observe(getViewLifecycleOwner(), (animal -> {
            String texteAAfficher;

            String uri = "@drawable/";
            String nomImage = animal.getImage();
            //TODO: Penser à enlever les replaces une fois les donnees dans la bdd sont correctes
            nomImage = nomImage.replace(".png", "");
            nomImage = nomImage.replace(".jpg", "");
            ImageView imageAnimal = view.findViewById(R.id.imageAnimal);
            int resID = getResources().getIdentifier(uri + nomImage, "drawable", getActivity().getPackageName());
            imageAnimal.setImageResource(resID);

            TextView name = view.findViewById(R.id.textNomAnimal);
            name.setText(animal.getNom_commun());

            TextView genre = view.findViewById(R.id.descriptionGenre);
            genre.setText(animal.getGenre());

            TextView espece = view.findViewById(R.id.descriptionEspece);
            espece.setText(animal.getEspece());

            TextView embranchement = view.findViewById(R.id.descriptionEmbranchement);
            embranchement.setText(animal.getEmbranchement());

            TextView sousEmbranchement = view.findViewById(R.id.descriptionSousEmbranchement);
            sousEmbranchement.setText(animal.getSous_embranchement());

            TextView ordre = view.findViewById(R.id.descriptionOrdre);
            ordre.setText(animal.getOrdre());

            TextView uicn = view.findViewById(R.id.descriptionUicn);
            uicn.setText(animal.getUicn());

            texteAAfficher = "";
            TextView physique = view.findViewById(R.id.descriptionPhysiques);
            for (Physique p: animal.getPhysiques()) {
                texteAAfficher += "- " + p.getDescription() + "\n";
            }
            physique.setText(texteAAfficher);

            texteAAfficher = "";
            TextView sexe = view.findViewById(R.id.descriptionSexes);
            for (Sexe s: animal.getSexes()) {
                texteAAfficher += "- " + s.getDescription() + "\n";
            }
            sexe.setText(texteAAfficher);

            texteAAfficher = "";
            TextView vie = view.findViewById(R.id.descriptionVies);
            for (Vie v: animal.getVies()) {
                texteAAfficher += "- " + v.getDescription() + "\n";
            }
            vie.setText(texteAAfficher);

            texteAAfficher = "";
            TextView reproduction = view.findViewById(R.id.descriptionReproductions);
            for (Reproduction r: animal.getReproductions()) {
                texteAAfficher += "- " + r.getDescription() + "\n";
            }
            reproduction.setText(texteAAfficher);

            texteAAfficher = "";
            TextView geographie = view.findViewById(R.id.descriptionGeographie);
            for (Geographie g: animal.getGeographies()) {
                texteAAfficher += "- " + g.getDescription() + "\n";
            }
            geographie.setText(texteAAfficher);
        }));
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fiche_animal, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.action_update_animal){
            Bundle bundle = new Bundle();
            bundle.putLong("ID",this.id);
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_ficheAnimalFragment_to_createUpdateAnimal, bundle, null,null);        }
        return super.onOptionsItemSelected(item);
    }

}
