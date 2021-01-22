package com.example.wheretoeat;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;


public class RestaurantListFragment extends androidx.fragment.app.ListFragment {
    //Class Variables
    ListView lvRestaurants;
    ItemSelected activity;
    Intent intent;
    Bundle bundle;
    Cursor cursor;
    ArrayList<String> restaurantNames;
    RestaurantListActivity rla;
    DatabaseHelper dbh;
    SQLiteDatabase wteDatabase;

    public interface ItemSelected   {
        void onItemSelected(int index);
    }//End of interface itemSelected

    public RestaurantListFragment() {
        // Required empty public constructor
    }//End of default constructor method

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        activity = (ItemSelected) context;
    }//End of method onAttach

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dbh = new DatabaseHelper(getActivity());
        wteDatabase = dbh.getReadableDatabase();
        restaurantNames = new ArrayList<>(30);
        cursor = wteDatabase.rawQuery("SELECT * FROM Restaurants ORDER BY Name;", null);
        if (cursor.moveToFirst()) {
            do {
                restaurantNames.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }//End of if statement to populate a list of restaurants

        //restaurantNames = new ArrayList<>(30);
        //assert getArguments() != null;
        //restaurantNames = getArguments().getStringArrayList("names");

        /*
        rla = (RestaurantListActivity) getActivity();
        assert rla != null;
        restaurantNames = rla.restaurantNames;
         */
        //Add values to the list
        setListAdapter(new ArrayAdapter<String>(Objects.requireNonNull(getActivity()), android.R.layout.simple_list_item_1, restaurantNames));

    }//End of method onActivityCreated

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //dbh = new DatabaseHelper(getActivity());
        //wteDatabase = dbh.getReadableDatabase();
        activity.onItemSelected(position);
    }//End of method onListItemClick
}//End of fragment RestaurantListFragment
