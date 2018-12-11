package com.example.johan.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

//    Get name of category of which user wants to see menus and ask for these menus
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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
    }
//  Get menus from online Json file and filter to selected category
//    DIT MOET VERANDERD WORDEN NAAR GEWOON MENUITEM, ANDERS BLIJFT HET MISGAAN
    @Override
    public void gotMenu(ArrayList<MenuItem> menus) {
        ArrayList<MenuItem> selectedmenus = new ArrayList<>();
        for (int i = 0; i<menus.size(); i++)    {
            if(menus.get(i).getCategory().equals(category)) {
                selectedmenus.add(menus.get(i));
                Log.d("hellup", "is dit gelukt?" + menus.get(i).getName());
            }
        }
//      Show menus in adapter
        MenuAdapter adapter = new MenuAdapter(this, R.layout.menu_row, selectedmenus);
        ListView listView = findViewById(R.id.menuview);
        listView.setAdapter(adapter);
        Log.d("hellup", "ben ik hier dan wel?");

//      Check which item is clicked by user and send user to FoodActivity with chosen menu
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MenuItem clickedmenu = (MenuItem) parent.getItemAtPosition(position);
                Intent intent = new Intent(MenuActivity.this, FoodActivity.class);
                String name = clickedmenu.getName();
                String description = clickedmenu.getDescription();
                float price = clickedmenu.getPrice();
                String url = clickedmenu.getImageUrl();
                Bundle values = new Bundle();
                values.putString("name", name);
                values.putString("description", description);
                values.putString("url", url);
                values.putFloat("price", price);
                intent.putExtras(values);
                Log.d("honee", "wutt" + clickedmenu.getName());
//                intent.putExtra("menu", clickedmenu);
                startActivity(intent);
            }
        });
    }
//      Print possible error
    @Override
    public void gotMenuError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
//      send user back to CategoriesActivity
    public void onBackPressed() {
        startActivity(new Intent(MenuActivity.this, CategoriesActivity.class));
    }
}
