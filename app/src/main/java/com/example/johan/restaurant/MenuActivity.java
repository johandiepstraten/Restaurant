package com.example.johan.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {
    com.example.johan.restaurant.MenuItem item;
    String category;
    ArrayList<MenuItem> dishes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        MenuRequest menu = new MenuRequest(this);
        menu.getMenu(this);
        Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();
        Bundle categories = getIntent().getExtras();
        category = categories.getString("category");
//        if (savedInstanceState == null) {
//            Bundle categories = getIntent().getExtras();
//            if (categories == null) {
//                category = null;
//            } else {
//                category = categories.getString("category");
//            }
//        }
//        if (category != "entrees" || category != "appetizers")  {
//            Intent intent = new Intent(MenuActivity.this, CategoriesActivity.class);
//            startActivity(intent);
//        }

    }

    @Override
    public void gotMenu(ArrayList<com.example.johan.restaurant.MenuItem> menus) {
        for(int i = 0; i < menus.size(); i++) {
//            item = menus(i);
            String currentcategory = item.getCategory();
            if (currentcategory == category)    {
                String name = item.getName();
//                dishes.add(name);
//                float price = item.getPrice();

            }
        }
    }

    @Override
    public void gotMenuError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
