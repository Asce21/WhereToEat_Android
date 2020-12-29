package com.example.wheretoeat;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import android.app.Activity;
//import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Objects;

public class RestaurantListActivity extends Activity implements RestaurantListFragment.ItemSelected {
    //Variable Declarations
    String meals = "", username, item;
    Intent intent, intent2, intent3;
    ArrayList<String> restaurantNames;
    FragmentManager manager;
    int orientation;
    SQLiteDatabase wteDatabase;
    Cursor cursor;
    //Details Fragment Objects
    ImageView restaurantImage;
    TextView restaurantName;
    TextView daysOpen1;
    TextView daysOpen2;
    TextView hoursOpen1;
    TextView hoursOpen2;
    TextView addressLine1;
    TextView addressLine2;
    TextView city;
    TextView state;
    TextView zip;
    TextView mealsServed;
    TextView multipleLocations;
    Button btnPhone;
    Button btnURL;
    Bundle bundle;
    Fragment listFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        //Variable Initializations
        wteDatabase = openOrCreateDatabase("wte_database", MODE_PRIVATE, null);
        intent = getIntent();
        username = intent.getStringExtra("username");
        restaurantNames = new ArrayList<>();
        intent = new Intent(this, RestaurantListFragment.class);
        //Details Fragment Objects
        restaurantImage = (ImageView) findViewById(R.id.iv_restaurant_image);
        restaurantName = (TextView) findViewById(R.id.tv_restaurant_name);
        daysOpen1 = (TextView) findViewById(R.id.tv_days_open_1);
        daysOpen2 = (TextView) findViewById(R.id.tv_days_open_2);
        hoursOpen1 = (TextView) findViewById(R.id.tv_hours_open_1);
        hoursOpen2 = (TextView) findViewById(R.id.tv_hours_open_2);
        addressLine1 = (TextView) findViewById(R.id.tv_address_line_1);
        addressLine2 = (TextView) findViewById(R.id.tv_address_line_2);
        city = (TextView) findViewById(R.id.tv_city);
        state = (TextView) findViewById(R.id.tv_state);
        zip = (TextView) findViewById(R.id.tv_zip);
        mealsServed = (TextView) findViewById(R.id.tv_meals_served);
        multipleLocations = (TextView) findViewById(R.id.tv_multiple_locations);
        btnPhone = (Button) findViewById(R.id.btn_phone);
        btnURL = (Button) findViewById(R.id.btn_url);
        bundle = new Bundle();
        listFragment = new Fragment();
        SQLiteDatabase wteDatabase = openOrCreateDatabase("wte_database", MODE_PRIVATE, null);



        //Populate the list of restaurant names
        cursor = wteDatabase.rawQuery("SELECT * FROM Restaurants ORDER BY Name;", null);
        if (cursor.moveToFirst()) {
            do {
                restaurantNames.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }//End of if statement to populate a list of restaurants

        //Send the ArrayList to the fragment
        bundle.putStringArrayList("names", restaurantNames);
        listFragment.setArguments(bundle);


        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse(btnPhone.getText().toString()));
                startActivity(intent2);
            }//End of method onClick
        });//End of btnPhone.setOnClickListener

        btnURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse(btnURL.getText().toString()));
                startActivity(intent3);
            }//End of method onClick
        });//End of btnURL.setOnClickListener
    }//End of method onCreate


    @Override
    protected void onStart() {
        super.onStart();

        SQLiteDatabase wteDatabase = openOrCreateDatabase("wte_database", MODE_PRIVATE, null);
        orientation = getResources().getConfiguration().orientation;
        manager = new FragmentActivity().getSupportFragmentManager();
        if (orientation == Configuration.ORIENTATION_PORTRAIT)  {
            manager.beginTransaction()
                    .show(Objects.requireNonNull(manager.findFragmentById(R.id.list_fragment)))
                    .hide(Objects.requireNonNull(manager.findFragmentById(R.id.details_fragment)))
                    .commit();
        }//End of if statement to hide the details fragment
    }//End of method onStart

    @Override
    public void onItemSelected(int index) {
        String item = restaurantNames.get(index);
        cursor = wteDatabase.rawQuery("SELECT * FROM Restaurants WHERE Name = " + item + ";", null);

        orientation = getResources().getConfiguration().orientation;
        manager = new FragmentActivity().getSupportFragmentManager();
        if (orientation == Configuration.ORIENTATION_PORTRAIT)  {
            manager.beginTransaction()
                    .hide(Objects.requireNonNull(manager.findFragmentById(R.id.list_fragment)))
                    .show(Objects.requireNonNull(manager.findFragmentById(R.id.details_fragment)))
                    .addToBackStack(null)
                    .commit();
        }//End of if statement to hide the details fragment

        restaurantName.setText(cursor.getString(0));
        daysOpen1.setText(cursor.getString(1));
        daysOpen2.setText(cursor.getString(2));
        hoursOpen1.setText(cursor.getString(3));
        hoursOpen1.setText(cursor.getString(4));
        addressLine1.setText(cursor.getString(5));
        addressLine2.setText(cursor.getString(6));
        city.setText(cursor.getString(7));
        state.setText(cursor.getString(8));
        zip.setText(cursor.getString(9));
        btnPhone.setText(cursor.getString(10));
        btnURL.setText(cursor.getString(11));
        meals += cursor.getInt(12) == 1? "Breakfast\n" : "";
        meals += cursor.getInt(13) == 1? "Lunxh\n" : "";
        meals += cursor.getInt(14) == 1? "Dinner\n" : "";
        mealsServed.setText(meals);
    }//End of method onItemSelected


}//End of class RestaurantListActivity
