package fr.jorisrouziere.animaux.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import fr.jorisrouziere.animaux.Room.models.Animal;
import fr.jorisrouziere.animaux.Utils.JsonIOUtils;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class API {

    private static final String BASE_URL = "https://animaux-api.herokuapp.com/api";
    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    private static API sAPI;
    private final OkHttpClient mHttpClient;

    private API() {
        mHttpClient = new OkHttpClient
                .Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(40, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    public static API getInstance() {
        if (sAPI == null) {
            sAPI = new API();
        }
        return sAPI;
    }

    private Response getSynchronous(String path) throws IOException {
        return mHttpClient.newCall(buildGet(path)).execute();
    }

    private void postAsynchronous(String path, RequestBody body, Callback callback) {
        mHttpClient.newCall(buildPost(path, body)).enqueue(callback);
    }

    private Response deleteSynchronous(String path) throws IOException {
        return mHttpClient.newCall(buildDelete(path)).execute();
    }

    private Request buildGet(String path) {
        return new Request
                .Builder()
                .url(String.format("%s%s", BASE_URL, path))
                .build();
    }

    private Request buildPost(String path, RequestBody body) {
        return new Request
                .Builder()
                .url(String.format("%s%s", BASE_URL, path))
                .post(body)
                .build();
    }

    private Request buildDelete(String path) {
        return new Request
                .Builder()
                .url(String.format("%s%s", BASE_URL, path))
                .delete()
                .build();
    }

    private void handleApiError(Response response) throws ApiErrorException, IOException {
        if (response.code() >= HttpsURLConnection.HTTP_BAD_REQUEST) {
            throw new ApiErrorException(Objects.requireNonNull(response.body()).string());
        }
    }

    private List<?> handleApiErrorList(Response response) throws ApiErrorException, IOException {
        if (HttpsURLConnection.HTTP_NOT_FOUND == response.code()) {
            response.close();
            return new ArrayList<>();
        } else {
            throw new ApiErrorException(Objects.requireNonNull(response.body()).string());
        }
    }

    public static class ApiErrorException extends Exception {
        public ApiErrorException(String message) {
            super(message);
        }
    }

    public List<Animal> getAnimaux() throws ApiErrorException, IOException {
        Response listResponse = getSynchronous("/animaux");

        if (HttpsURLConnection.HTTP_OK == listResponse.code()) {
            return JsonIOUtils.GSON.fromJson(listResponse.body().string(), new TypeToken<List<Animal>>() {}.getType());
        } else {
            return (List<Animal>) handleApiErrorList(listResponse);
        }
    }

    public Animal getAnimal(int id) throws ApiErrorException, IOException {
        Response response = getSynchronous(String.format(Locale.FRANCE, "/animal/%d", id));

        if (HttpsURLConnection.HTTP_OK == response.code()) {
            return JsonIOUtils.GSON.fromJson(response.body().string(), Animal.class);
        } else {
            return (Animal) handleApiErrorList(response);
        }
    }

    public boolean deleteAnimal(Long id) throws IOException {
        Response response = deleteSynchronous(String.format(Locale.FRANCE, "/animal/%d", id));

        return HttpsURLConnection.HTTP_OK == response.code();
    }

    public void postAnimal(Animal animal, Callback callback) {
        postAsynchronous(String.format(Locale.FRANCE, "/animal/"),
                RequestBody.create(new Gson().toJson(animal), JSON_MEDIA_TYPE),
                callback);
    }
}
