package fr.jorisrouziere.animaux.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.jorisrouziere.animaux.R;
import fr.jorisrouziere.animaux.Utils.JsonIOUtils;
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
        View view = inflater.inflate(R.layout.fragment_arbre, container, false);

        id = ArbreFragmentArgs.fromBundle(getArguments()).getId();

        ArrayList<String> titres = new ArrayList<>();
        ArrayList<String> sousTitres = new ArrayList<>();
        ArrayList<Integer> images = new ArrayList<>();
        ArrayList<Integer> ids = new ArrayList<>();

        try {
            JSONArray donnees = new JSONObject(JsonIOUtils.loadJSONFromAsset(getActivity(), "arbre.json")).getJSONArray("donnees");
            JSONObject obj = JsonIOUtils.getObjectById(donnees, id);
            System.out.println(id);
            JSONArray valeurs = obj.getJSONArray("valeurs");

            for (int i = 0; i < valeurs.length(); i++) {
                JSONObject o = valeurs.getJSONObject(i);
                titres.add(o.getString("nom"));
                sousTitres.add(o.getString("description"));
                images.add(R.drawable.coelacanthe);
                ids.add(o.getInt("suivant"));
            }

            ArbreAdapter adapter=new ArbreAdapter(getActivity(), titres, sousTitres, images, ids);
            ListView list = view.findViewById(R.id.listArbre);
            list.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
}
