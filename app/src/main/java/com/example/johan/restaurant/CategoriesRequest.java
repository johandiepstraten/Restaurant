package com.example.johan.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    private Context context;
    Callback activity;
    public CategoriesRequest (Context context)  {
        this.context = context;
    }
//    send error message if percieved
    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotCategoriesError(error.getMessage());
    }
//    Get list of categorie strings from online Json file
    @Override
    public void onResponse(JSONObject response) {
        ArrayList<String> categorylist = new ArrayList<String>();
        try {
            JSONArray categories = response.getJSONArray("categories");
            for (int i = 0; i < categories.length(); i++) {
                String category = categories.getString(i);

                categorylist.add(category);
            }
            activity.gotCategories(categorylist);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
//  Send gotten string list or error message back to CategoriesActivity
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }
//    Set url from which to get online Json file
    void getCategories(Callback activity)   {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonObjectRequest);
        this.activity = activity;
    }
}
