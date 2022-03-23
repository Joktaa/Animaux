package fr.jorisrouziere.animaux.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.jorisrouziere.animaux.R;

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
        id = FicheAnimalFragmentArgs.fromBundle(getArguments()).getId();
        return view;

    }
}