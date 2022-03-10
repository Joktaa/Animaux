package fr.jorisrouziere.animaux.animalsRecyclerView;

import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import fr.jorisrouziere.animaux.Room.models.Animal;
import lombok.NonNull;

public class AnimalsListAdapter extends ListAdapter<Animal, AnimalsViewHolder> implements Filterable {

    private List<Animal> fullList;


    public AnimalsListAdapter(@NonNull DiffUtil.ItemCallback<Animal> diffCallback) {
        super(diffCallback);
        fullList = new ArrayList<>();
    }

    public void setFullList(List<Animal> fullList) {
        this.fullList = fullList;
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

    @Override
    public Filter getFilter() {
        return animalFilter;
    }

    private Filter animalFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Animal> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(fullList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Animal animal : fullList) {
                    if (animal.getNom_commun().toLowerCase().contains(filterPattern)) {
                        filteredList.add(animal);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            //differ.getCurrentList().clear();
            //differ.getCurrentList().addAll((List) results.values);
            submitList((List) results.values);
            notifyDataSetChanged();
        }
    };

}
