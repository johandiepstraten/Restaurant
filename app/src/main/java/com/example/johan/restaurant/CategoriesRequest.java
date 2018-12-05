package com.example.johan.restaurant;

import android.content.Context;

import java.util.ArrayList;

public class CategoriesRequest {

    private Context context;
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }
}
