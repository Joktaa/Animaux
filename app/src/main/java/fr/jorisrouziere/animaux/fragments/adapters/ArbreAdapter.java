package fr.jorisrouziere.animaux.fragments.adapters;

import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import fr.jorisrouziere.animaux.R;
import fr.jorisrouziere.animaux.fragments.ArbreFragmentDirections;

public class ArbreAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> titres;
    private final ArrayList<String> sousTitres;
    private final ArrayList<String> images;
    private final ArrayList<Integer> ids;
    private final Resources resources;

    public ArbreAdapter(Activity context, ArrayList<String> titres, ArrayList<String> sousTitres, ArrayList<String> images, ArrayList<Integer> ids) {
        super(context, R.layout.item_arbre, titres);

        this.context = context;
        this.titres = titres;
        this.sousTitres = sousTitres;
        this.images = images;
        this.ids = ids;
        this.resources = context.getResources();
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_arbre, null,true);
        final int imageId = resources.getIdentifier(images.get(position), "drawable", context.getPackageName());

        TextView titleText = rowView.findViewById(R.id.arbreTitle);
        ImageView imageView = rowView.findViewById(R.id.arbreImage);
        TextView subTitleText = rowView.findViewById(R.id.arbreSubtitle);

        titleText.setText(titres.get(position));
        imageView.setImageResource(imageId);
        if (ids.get(position) != -1) {
            imageView.setOnClickListener((v) -> {
                NavDirections action = ArbreFragmentDirections.actionArbreFragmentSelf(ids.get(position));
                Navigation.findNavController(v).navigate(action);
            });
        }
        subTitleText.setText(sousTitres.get(position));

        return rowView;

    };
}
