package fr.jorisrouziere.animaux.animalsRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import fr.jorisrouziere.animaux.R;
import fr.jorisrouziere.animaux.Room.models.Animal;
import fr.jorisrouziere.animaux.fragments.FicheAnimalFragment;
import lombok.NonNull;

public class AnimalsViewHolder extends RecyclerView.ViewHolder{

    private final TextView title;
    private final ImageView image;
    private final CardView animalCard;

    public AnimalsViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.item_title);
        image = itemView.findViewById(R.id.item_image);
        animalCard = itemView.findViewById(R.id.animalCard);
    }

    public void bind(Animal animal) {
        title.setText(animal.getNom_commun());

        animalCard.setOnClickListener((view) -> {
            Fragment fragment = FicheAnimalFragment.newInstance(animal.getA_id());
            FragmentTransaction transaction = ((AppCompatActivity) itemView.getContext()).getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_fragment, fragment).commit();
        });
    }

    static AnimalsViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animal, parent, false);
        return new AnimalsViewHolder(view);
    }
}
