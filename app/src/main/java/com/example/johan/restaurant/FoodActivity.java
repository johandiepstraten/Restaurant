package com.example.johan.restaurant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FoodActivity extends AppCompatActivity {
    MenuItem item;
    Context context;

//    Get all information of passed MenuItem object and set layout to show information about the dish
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        Intent intent = getIntent();
        item = (MenuItem) intent.getSerializableExtra("clickedmenu");
        String name = item.getName();
        String description = item.getDescription();
        String image = item.getImageUrl();
        float price = item.getPrice();
        ((TextView) findViewById(R.id.finalname)).setText(name);
        ((TextView) findViewById(R.id.finaldescription)).setText(description);
        ((TextView) findViewById(R.id.finalprice)).setText("€ " + price);
        ImageView imageurl = findViewById(R.id.url_image);
        Picasso.with(context).load(image).into(imageurl);
    }
//    send user back to MenuActivity
    public void onBackPressed() {
        startActivity(new Intent(FoodActivity.this, MenuActivity.class));
    }
}
