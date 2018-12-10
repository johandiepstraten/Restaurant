//package com.example.johan.restaurant;
//
//import android.content.Context;
//import android.support.annotation.LayoutRes;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import org.w3c.dom.Text;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static java.security.AccessController.getContext;
//
//public class CategoryAdapter extends ArrayList<String> {
//
//    private Context context;
//    private ArrayList<String> categories;
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(R.layout.category_row, parent, false);
//        }
//        String currentcategory = categories.get(position);
//        ((TextView) convertView.findViewById(R.id.Category)).setText(currentcategory);
//        return convertView;
//    }
//
//    public CategoryAdapter(@NonNull Context context, int resource, @NonNull ArrayList<String> list) {
//        super(context, resource, list);
//        categories = list;
//    }
//}
