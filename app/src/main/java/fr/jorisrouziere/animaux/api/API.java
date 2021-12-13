package fr.jorisrouziere.animaux.api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class API {

    public static void appel(Context context) {
        RequestQueue mRequestQueue;
        StringRequest mStringRequest;
        String url = "http://10.0.2.2:8888/api/animaux";

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(context);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(context,"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("-------------------------------------------------------------","Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }
}
