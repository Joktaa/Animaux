package fr.jorisrouziere.animaux.Utils;

import android.content.Context;
import android.content.Intent;

import fr.jorisrouziere.animaux.activity.MainActivity;

public class ActivityUtils {
    public static String idTag = "ID";

    public static void goToMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
