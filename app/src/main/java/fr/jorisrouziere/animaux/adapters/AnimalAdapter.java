package fr.jorisrouziere.animaux.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import java.util.List;

import fr.jorisrouziere.animaux.R;
import fr.jorisrouziere.animaux.Room.models.Animal;

public class AnimalAdapter extends BaseAdapter {

    static class ViewHolder {
        TextView title;
        ImageView image;
    }

    private final Context context;
    private final LayoutInflater inflater;
    private FragmentManager fragmentManager;

    private final List<Animal> animaux;

    public AnimalAdapter(Context _context, FragmentManager _fragmentManager, List<Animal> _animaux) {
        context = _context;
        fragmentManager = _fragmentManager;
        animaux = _animaux;
        inflater = (LayoutInflater.from(_context));
    }

    @Override
    public int getCount() {
        return animaux.size();
    }

    @Override
    public Object getItem(int position) {
        return animaux.get(position);
    }

    @Override
    public long getItemId(int position) {
        return animaux.get(position).getA_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View view = convertView;
        Animal animal = animaux.get(position);

        if(convertView == null) {
            view = this.inflater.inflate(R.layout.item_animal, null);
            viewHolder = new ViewHolder();
            viewHolder.title = view.findViewById(R.id.item_title);
            viewHolder.image = view.findViewById(R.id.item_image);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        // TODO : set by animal
        viewHolder.title.setText(animal.getNom_commun());
        // viewHolder.image

        return view;
    }
}
