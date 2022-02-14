package fr.jorisrouziere.animaux.Utils;

import android.content.Context;
import android.content.Intent;

import fr.jorisrouziere.animaux.activity.FicheAnimal;
import fr.jorisrouziere.animaux.activity.MainActivity;

public class ActivityUtils {
    public static String idTag = "ID";

    public static void goToMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void goToAnimalSheet(Context context, Long id) {
        Intent intent = new Intent(context, FicheAnimal.class);
        intent.putExtra(idTag, id);
        context.startActivity(intent);
    }
}
