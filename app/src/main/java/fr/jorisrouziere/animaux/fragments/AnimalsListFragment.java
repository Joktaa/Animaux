package fr.jorisrouziere.animaux.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import fr.jorisrouziere.animaux.R;
import fr.jorisrouziere.animaux.Room.models.Animal;
import fr.jorisrouziere.animaux.Utils.ApiUtils;
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

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                List<Animal> animaux = adapter.getCurrentList();
                Animal deletedAnimal = animaux.get(viewHolder.getAdapterPosition());
                int position = viewHolder.getAdapterPosition();
                ApiUtils.deleteAnimal(getContext(), viewHolder.getItemId());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());  // TODO : dépend du résultat de l'appel API
                Snackbar.make(view, deletedAnimal.getNom_commun(), Snackbar.LENGTH_LONG).setAction("Annuler", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO : Reposter l'animal
                        //animaux.add(position, deletedAnimal);
                        //adapter.notifyItemInserted(position);
                    }
                }).show();
            }
        }).attachToRecyclerView(recyclerView);

        return view;
    }
}
