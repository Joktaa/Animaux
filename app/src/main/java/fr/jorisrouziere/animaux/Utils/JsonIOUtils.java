package fr.jorisrouziere.animaux.Utils;

import androidx.fragment.app.FragmentActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class JsonIOUtils {

    public static final Gson GSON = new GsonBuilder()
            .create();

    public static String loadJSONFromAsset(FragmentActivity fragment, String url) {
        String json = null;
        try {
            InputStream is = fragment.getAssets().open(url);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static JSONObject getObjectById(JSONArray array, int id) throws JSONException {
        JSONObject obj = null;
        for (int i = 0; i < array.length(); i++) {
            obj = array.getJSONObject(i);
            if (obj.getInt("id") == id) {
                return obj;
            }
        }

        return null;
    }
}