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
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import fr.jorisrouziere.animaux.R;
import fr.jorisrouziere.animaux.Room.Repository;
import fr.jorisrouziere.animaux.Room.models.Animal;
import fr.jorisrouziere.animaux.Utils.ApiUtils;
import fr.jorisrouziere.animaux.animalsRecyclerView.AnimalsListAdapter;
import fr.jorisrouziere.animaux.animalsRecyclerView.AnimalsViewModel;
import fr.jorisrouziere.animaux.model.Vie;
import lombok.val;

public class AnimalsListFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    View view;
    private Menu menu;
    public AnimalsListFragment() {}

    public static AnimalsListFragment newInstance() {
        AnimalsListFragment fragment = new AnimalsListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    final AnimalsListAdapter adapter = new AnimalsListAdapter(new AnimalsListAdapter.AnimalDiff());


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_animals, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        if (!CheckUser()){
            FloatingActionButton btnAddAnimal = view.findViewById(R.id.button_add_animal);
            btnAddAnimal.setVisibility(View.INVISIBLE);
        }
        RecyclerView recyclerView = view.findViewById(R.id.animals_list);
        if (!adapter.hasObservers()) {
            adapter.setHasStableIds(true);
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        AnimalsViewModel animalsViewModel = new ViewModelProvider(this).get(AnimalsViewModel.class);
        animalsViewModel.getAnimals().observe(getViewLifecycleOwner(), adapter::submitList);
        animalsViewModel.getAnimals().observe(getViewLifecycleOwner(), adapter::setFullList);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                var ref = new Object() {
                    boolean deleted = true;
                };
                Repository repository = new Repository(getContext());
                List<Animal> animaux = adapter.getCurrentList();
                int position = viewHolder.getAdapterPosition();
                Animal deletedAnimal = animaux.get(position);
                repository.deleteById(deletedAnimal.getA_id());
                adapter.notifyItemRemoved(position);
                Snackbar.make(view, deletedAnimal.getNom_commun(), Snackbar.LENGTH_LONG).setAction("Annuler", v -> {
                    repository.insertOneAnimal(deletedAnimal);
                    adapter.notifyItemInserted(position);
                    ref.deleted = false;
                }).addCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        super.onDismissed(transientBottomBar, event);
                        System.out.println();
                        if (ref.deleted) {
                            ApiUtils.deleteAnimal(deletedAnimal.getA_id());
                        }
                    }
                }).show();
            }
        }).attachToRecyclerView(recyclerView);
        setHasOptionsMenu(true);

        FloatingActionButton btnAddAnimal = view.findViewById(R.id.button_add_animal);
        NavController navController = NavHostFragment.findNavController(this);
        btnAddAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_animalsListFragment_to_createUpdateAnimal);
            }
        });

        return view;
    }

    private boolean CheckUser() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        boolean connected = true;
        if (firebaseUser == null){
            connected = false;
        }
        return connected;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_bar, menu);
        this.menu = menu;
        if (!CheckUser()){
            menu.findItem(R.id.action_logout).setVisible(false);
        }
        else{
            menu.findItem(R.id.action_connexion).setVisible(false);
        }
        super.onCreateOptionsMenu(menu, inflater);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(getContext().SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    Log.i(TAG, "onQueryTextChange: EMPTY");
                    adapter.getFilter().filter("");
                } else {
                    Log.i(TAG, "onQueryTextChange: search text is "+newText.toString());
                    adapter.getFilter().filter(newText);
                }
                return true;
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.action_connexion){

            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_animalsListFragment_to_loginFragment, null,null);
        }
        if (id == R.id.action_logout){
            firebaseAuth.signOut();
            FloatingActionButton btnAddAnimal = view.findViewById(R.id.button_add_animal);
            btnAddAnimal.setVisibility(View.INVISIBLE);
            menu.findItem(R.id.action_connexion).setVisible(true);
            menu.findItem(R.id.action_logout).setVisible(false);
        }

        return super.onOptionsItemSelected(item);
    }
}
