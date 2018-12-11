package com.example.johan.restaurant;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class MenuAdapter extends ArrayAdapter<MenuItem> {

    private ArrayList<MenuItem> menus;
    private Context context;

//    Set adapter to view certain values of menus of chosen category in listview
    public View getView(int position, View listView, ViewGroup parent) {
        Log.d("test", "ik ben hier7");
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.menu_row, parent, false);
        }
        MenuItem currentmenu = menus.get(position);
        String name = currentmenu.getName();
//        String image = currentmenu.getImageUrl();
        float price = currentmenu.getPrice();
        ((TextView) listView.findViewById(R.id.dishname)).setText(name);
        ((TextView) listView.findViewById(R.id.amount)).setText("€ " + price);
        ImageView imageurl = listView.findViewById(R.id.url_image);
        Picasso.with(context).load(currentmenu.getImageUrl()).into(imageurl);
        return listView;
    }
//  Initiate adapter
    public MenuAdapter(Context context, int resource, ArrayList<MenuItem> menus) {
        super(context, resource, menus);
        this.menus = menus;
        this.context = context;
        Log.d("test", "ik ben hier6");

    }
}
