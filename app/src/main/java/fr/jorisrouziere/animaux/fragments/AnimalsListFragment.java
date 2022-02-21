package fr.jorisrouziere.animaux.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import fr.jorisrouziere.animaux.R;
import fr.jorisrouziere.animaux.animalsRecyclerView.AnimalsListAdapter;
import fr.jorisrouziere.animaux.animalsRecyclerView.AnimalsViewModel;

public class AnimalsListFragment extends Fragment {
    public AnimalsListFragment() {}

    public static AnimalsListFragment newInstance() {
        AnimalsListFragment fragment = new AnimalsListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_animals, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.animals_list);
        final AnimalsListAdapter adapter = new AnimalsListAdapter(new AnimalsListAdapter.AnimalDiff());
        adapter.setHasStableIds(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        AnimalsViewModel animalsViewModel = new ViewModelProvider(this).get(AnimalsViewModel.class);
        animalsViewModel.getAnimals().observe(getViewLifecycleOwner(), adapter::submitList);

        return view;
    }
}
