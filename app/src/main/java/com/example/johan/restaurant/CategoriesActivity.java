package com.example.johan.restaurant;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {
    private ArrayList<String> categories;
    private ArrayAdapter<String> thisadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        CategoriesRequest category = new CategoriesRequest(this);
        category.getCategories(this);
        Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void gotCategories(ArrayList<String> categories) {

//        Toast.makeText(this, categories.get(0), Toast.LENGTH_LONG).show();
        ListView listView = findViewById(R.id.listview);

        // Create the array adapter to bind the array to the listView
        thisadapter = new ArrayAdapter<String>(  this,
                android.R.layout.simple_list_item_1,
                categories
        );
        listView.setAdapter(thisadapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()  {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String clickedcategory = (String) parent.getItemAtPosition(position).toString();
//                TextView test = findViewById(R.id.textView);
//                test.setText(clickedcategory);
//                Toast.makeText(this, clickedcategory, Toast.LENGTH_LONG).show();

//                String category = clickedcategory.getString(0);

                Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
                intent.putExtra("category", clickedcategory);
                startActivity(intent);

            }

        });
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
//    close app if android back button is pressed in this screen
    public void onBackPressed() {
        Intent close = new Intent(Intent.ACTION_MAIN);
        close.addCategory(Intent.CATEGORY_HOME);
        close.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(close);
    }
}
