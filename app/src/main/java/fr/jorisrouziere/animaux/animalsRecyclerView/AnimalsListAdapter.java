package fr.jorisrouziere.animaux.animalsRecyclerView;

import android.view.ViewGroup;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import fr.jorisrouziere.animaux.Room.models.Animal;
import lombok.NonNull;

public class AnimalsListAdapter extends ListAdapter<Animal, AnimalsViewHolder> {

    public AnimalsListAdapter(@NonNull DiffUtil.ItemCallback<Animal> diffCallback) {
        super(diffCallback);
    }

    @Override
    public AnimalsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return AnimalsViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalsViewHolder holder, int position) {
        Animal current = getItem(position);
        holder.bind(current);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getA_id();
    }

    public static class AnimalDiff extends DiffUtil.ItemCallback<Animal> {
        @Override
        public boolean areItemsTheSame(@NonNull Animal oldItem, @NonNull Animal newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Animal oldItem, @NonNull Animal newItem) {
            return oldItem.getA_id().equals(newItem.getA_id());
        }
    }
}
