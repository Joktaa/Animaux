package fr.jorisrouziere.animaux.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.jorisrouziere.animaux.R;
import fr.jorisrouziere.animaux.Utils.JsonIOUtils;
import fr.jorisrouziere.animaux.animalsRecyclerView.AnimalsListAdapter;
import fr.jorisrouziere.animaux.animalsRecyclerView.AnimalsViewModel;
import fr.jorisrouziere.animaux.fragments.adapters.ArbreAdapter;

public class ArbreFragment extends Fragment {
    Integer id;

    public ArbreFragment(Integer id) { this.id = id; }
    public ArbreFragment() { }

    public static ArbreFragment newInstance(int id) {
        ArbreFragment fragment = new ArbreFragment(id);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        id = ArbreFragmentArgs.fromBundle(getArguments()).getId();

        ArrayList<String> titres = new ArrayList<>();
        ArrayList<String> sousTitres = new ArrayList<>();
        ArrayList<String> images = new ArrayList<>();
        ArrayList<Integer> ids = new ArrayList<>();

        String nom = "";
        StringBuilder precedentsString = new StringBuilder();
        JSONArray valeurs = new JSONArray();

        try {
            JSONArray donnees = new JSONObject(JsonIOUtils.loadJSONFromAsset(getActivity(), "arbre.json")).getJSONArray("donnees");
            JSONObject obj = JsonIOUtils.getObjectById(donnees, id);
            nom = obj.getString("nom");
            valeurs = obj.getJSONArray("valeurs");

            JSONObject precObj = JsonIOUtils.getObjectById(donnees, id);
            int precId = precObj.getInt("prec");
            precedentsString = new StringBuilder();
            while (precId != -1) {
                precObj = JsonIOUtils.getObjectById(donnees, precId);
                precedentsString.insert(0, " > ");
                precedentsString.insert(0, precObj.getString("nom"));
                precId = precObj.getInt("prec");
            }

            for (int i = 0; i < valeurs.length(); i++) {
                JSONObject o = valeurs.getJSONObject(i);
                titres.add(o.getString("nom"));
                sousTitres.add(o.getString("description"));
                images.add(o.getString("photo"));
                ids.add(o.getInt("suivant"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(valeurs.length() == 0) {
            view = inflater.inflate(R.layout.fragment_arbre_fin, container, false);

            AnimalsListAdapter adapter = new AnimalsListAdapter(new AnimalsListAdapter.AnimalDiff());
            RecyclerView recyclerView = view.findViewById(R.id.animals_list);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

            AnimalsViewModel animalsViewModel = new ViewModelProvider(this).get(AnimalsViewModel.class);
            animalsViewModel.getAnimalsByArbre(nom).observe(getViewLifecycleOwner(), adapter::submitList);
        } else {
            view = inflater.inflate(R.layout.fragment_arbre, container, false);

            ArbreAdapter adapter = new ArbreAdapter(getActivity(), titres, sousTitres, images, ids);
            ListView list = view.findViewById(R.id.listArbre);
            list.setAdapter(adapter);
        }

        TextView nomView = view.findViewById(R.id.arbreNom);
        nomView.setText(nom);
        TextView precView = view.findViewById(R.id.arbrePrecedents);
        precView.setText(precedentsString.toString());

        return view;
    }
}
