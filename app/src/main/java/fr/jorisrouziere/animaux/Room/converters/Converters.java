package fr.jorisrouziere.animaux.Room.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import com.google.gson.reflect.TypeToken;

import fr.jorisrouziere.animaux.model.Geographie;
import fr.jorisrouziere.animaux.model.Physique;
import fr.jorisrouziere.animaux.model.Reproduction;
import fr.jorisrouziere.animaux.model.Sexe;
import fr.jorisrouziere.animaux.model.Vie;

public class Converters {
    static Gson gson = new Gson();

    ///// Physiques /////

    @TypeConverter
    public static List<Physique> stringToPhysiques(String data) {
        if(data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Physique>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String physiqueListToString(List<Physique> liste) {
        return gson.toJson(liste);
    }

    ///// Sexes /////

    @TypeConverter
    public static List<Sexe> stringToSexes(String data) {
        if(data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Sexe>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String sexeListToString(List<Sexe> liste) {
        return gson.toJson(liste);
    }

    ///// Vies /////

    @TypeConverter
    public static List<Vie> stringToVies(String data) {
        if(data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Vie>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String vieListToString(List<Vie> liste) {
        return gson.toJson(liste);
    }

    ///// Reproduction /////

    @TypeConverter
    public static List<Reproduction> reproductionsToVies(String data) {
        if(data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Reproduction>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String reproductionListToString(List<Reproduction> liste) {
        return gson.toJson(liste);
    }

    ///// Geographie /////

    @TypeConverter
    public static List<Geographie> stringToGeographies(String data) {
        if(data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Geographie>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String geographieListToString(List<Geographie> liste) {
        return gson.toJson(liste);
    }
}
