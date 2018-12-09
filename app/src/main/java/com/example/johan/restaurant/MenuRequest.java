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
import java.math.BigDecimal;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;
    Callback activity;
    public MenuRequest (Context context)  {
        this.context = context;
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotMenuError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        ArrayList<MenuItem> menuitemlist = new ArrayList<MenuItem>();
        try {
            JSONArray items = response.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject current_menu = items.getJSONObject(i);
                String name = current_menu.getString("name");
                String description = current_menu.getString("description");
                String category = current_menu.getString("category");
                String image_url = current_menu.getString("image_url");
                float price = BigDecimal.valueOf(current_menu.getDouble("price")).floatValue();
                menuitemlist.add(new MenuItem(name, description, category, image_url, price));
            }
            activity.gotMenu(menuitemlist);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menus);
        void gotMenuError(String message);
    }
    void getMenu(Callback activity)   {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu", null, this, this);
        queue.add(jsonObjectRequest);
        this.activity = activity;

    }
}
