package com.example.johan.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {
    MenuItem item;
    String category;
    ArrayList<MenuItem> dishes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
//        Bundle categories = getIntent().getExtras();
//        category = categories.getString("category");
        if (savedInstanceState == null) {
            Bundle categories = getIntent().getExtras();
            if (categories == null) {
                category = null;
            } else {
                category = categories.getString("category");
            }
        MenuRequest menu = new MenuRequest(this);
        menu.getMenu(this);
        Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();

        }
//        if (category != "entrees" && category != "appetizers")  {
//            Intent intent = new Intent(MenuActivity.this, CategoriesActivity.class);
//            startActivity(intent);
//        }

    }

    @Override
    public void gotMenu(ArrayList<com.example.johan.restaurant.MenuItem> menus) {
//        Stuur alleen entrees of appetizers door
        ArrayList<com.example.johan.restaurant.MenuItem> selectedmenus = new ArrayList<>();
        for (int i = 0; i<menus.size(); i++)    {
            if(menus.get(i).getCategory().equals(category)) {
                selectedmenus.add(menus.get(i));
            }
        }


        MenuAdapter adapter = new MenuAdapter(this, R.layout.menu_row, selectedmenus);
        ListView listView = findViewById(R.id.menuview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MenuItem clickedmenu = (MenuItem) parent.getItemAtPosition(position);
                Intent intent = new Intent(MenuActivity.this, FoodActivity.class);
                intent.putExtra("menu", clickedmenu);
                startActivity(intent);
            }
        });
//        for(int i = 0; i < menus.size(); i++) {
//            item = menus.get(i);
//            String currentcategory = item.getCategory();
//            if (currentcategory == category)    {
//                String name = item.getName();
//                dishes.add(name);
//                float price = item.getPrice();
//
//            }
//        }
    }

    @Override
    public void gotMenuError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
//      send user back to CategoriesActivity
    public void onBackPressed() {
        startActivity(new Intent(MenuActivity.this, CategoriesActivity.class));
    }
}
