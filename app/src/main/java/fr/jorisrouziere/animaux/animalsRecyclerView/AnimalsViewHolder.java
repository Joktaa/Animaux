package fr.jorisrouziere.animaux.animalsRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import fr.jorisrouziere.animaux.R;
import fr.jorisrouziere.animaux.Room.models.Animal;
import fr.jorisrouziere.animaux.fragments.AnimalsListFragmentDirections;
import fr.jorisrouziere.animaux.fragments.ArbreFragmentDirections;
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
        image.setImageResource(getResImage(animal));

        animalCard.setOnClickListener((view) -> {
            try {
                NavDirections action = AnimalsListFragmentDirections.actionAnimalsListFragmentToFicheAnimalFragment(animal.getA_id());
                Navigation.findNavController(view).navigate(action);
            } catch (IllegalArgumentException e) {
                NavDirections action = ArbreFragmentDirections.actionArbreFragmentToFicheAnimalFragment(animal.getA_id());
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    static AnimalsViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animal, parent, false);
        return new AnimalsViewHolder(view);
    }

    private int getResImage(Animal animal) {
        String uri = "@drawable/";
        String nomImage = animal.getImage();
        //TODO: Penser à enlever les replaces une fois les donnees dans la bdd sont correctes
        nomImage = nomImage.replace(".png", "");
        nomImage = nomImage.replace(".jpg", "");
        return itemView.getContext().getResources().getIdentifier(uri + nomImage, "drawable",  itemView.getContext().getPackageName());
    }
}
