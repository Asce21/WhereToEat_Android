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
import java.util.List;
import java.util.Objects;

public class RestaurantListActivity extends FragmentActivity implements RestaurantListFragment.ItemSelected {
    //Variable Declarations
    String meals = "", username, item;
    String mealsS = "", fullAddress = "";
    Intent intent, intent2, intent3;
    ArrayList<String> restaurantNames;
    ArrayList<Restaurant> allRestaurants;
    FragmentManager manager;
    int orientation;
    SQLiteDatabase wteDatabase;
    Cursor cursor;
    //Details Fragment Objects
    ImageView restaurantImage;
    TextView restaurantName;
    TextView daysOpen1;
    TextView hoursOpen1;
    TextView addressFull;
    TextView mealsServed;
    Button btnPhone;
    Button btnURL;
    Bundle bundle;
    Fragment listFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        //Variable Initializations
        SQLiteDatabase wteDatabase = openOrCreateDatabase("wte_database.db", MODE_PRIVATE, null);
        intent = getIntent();
        username = intent.getStringExtra("username");
        restaurantNames = new ArrayList<String>();
        allRestaurants = new ArrayList<>();
        intent = new Intent(this, RestaurantListFragment.class);
        //Details Fragment Objects
        restaurantImage = (ImageView) findViewById(R.id.iv_restaurant_image);
        restaurantName = (TextView) findViewById(R.id.tv_restaurant_name);
        daysOpen1 = (TextView) findViewById(R.id.tv_days_open_1);
        hoursOpen1 = (TextView) findViewById(R.id.tv_hours_open_1);
        addressFull = (TextView) findViewById(R.id.tv_address_line_1);
        mealsServed = (TextView) findViewById(R.id.tv_meals_served);
        btnPhone = (Button) findViewById(R.id.btn_phone);
        btnURL = (Button) findViewById(R.id.btn_url);
        bundle = new Bundle();
        listFragment = new Fragment();

        //Populate the list of restaurant names
        cursor = wteDatabase.rawQuery("SELECT * FROM Restaurants ORDER BY Name;", null);
        if (cursor.moveToFirst()) {
            
            do {
                restaurantNames.add(cursor.getString(0));
                allRestaurants.add(new Restaurant(cursor.getString(0), cursor.getString(1), cursor.getString(3),
                        cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8),
                        cursor.getString(9), cursor.getString(10), cursor.getString(11),
                        cursor.getString(12), cursor.getString(13), cursor.getString(14)));
            } while (cursor.moveToNext());
        }//End of if statement to populate a list of restaurants

        //Send the ArrayList to the fragment
        //bundle.putStringArrayList("names", restaurantNames);
        //listFragment.setArguments(bundle);


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


/*
    @Override
    protected void onStart() {
        super.onStart();

        //SQLiteDatabase wteDatabase = openOrCreateDatabase("wte_database", MODE_PRIVATE, null);
        orientation = getResources().getConfiguration().orientation;
        manager = new FragmentActivity().getSupportFragmentManager();
        if (orientation == Configuration.ORIENTATION_PORTRAIT)  {
            manager.beginTransaction()
                    .show(Objects.requireNonNull(manager.findFragmentById(R.id.list_fragment)))
                    .hide(Objects.requireNonNull(manager.findFragmentById(R.id.details_fragment)))
                    .commit();
        }//End of if statement to hide the details fragment
    }//End of method onStart
 */

    @Override
    public void onItemSelected(int index) {
        String item = restaurantNames.get(index), sqlStatement = "SELECT * FROM Restaurants WHERE Name = " + item + " ORDER BY Name;";
        /*
        DatabaseHelper dbh = new DatabaseHelper(this);
        wteDatabase = dbh.getReadableDatabase();
        cursor = wteDatabase.rawQuery(sqlStatement, null);
        cursor = wteDatabase.rawQuery("SELECT * FROM Restaurants WHERE Name = ? AND ", new String[] {"item",});

        //Get the data for the restaurant
        restaurauntName = cursor.getString(0);
        daysOpen = cursor.getString(1);
        hoursOpen = cursor.getString(3);
        addressL1 = cursor.getString(5);
        addressL2 = cursor.getString(6);
        cityS = cursor.getString(7);
        stateS = cursor.getString(8);
        zipcode = cursor.getString(9);
        phoneNumber = cursor.getString(10);
        url = cursor.getString(11);
        breakfast = cursor.getString(12);
        lunch = cursor.getString(13);
        dinner = cursor.getString(14);
         */

        /*
        orientation = getResources().getConfiguration().orientation;
        manager = new FragmentActivity().getSupportFragmentManager();
        if (orientation == Configuration.ORIENTATION_PORTRAIT)  {
            manager.beginTransaction()
                    .hide(Objects.requireNonNull(manager.findFragmentById(R.id.list_fragment)))
                    .show(Objects.requireNonNull(manager.findFragmentById(R.id.details_fragment)))
                    .addToBackStack(null)
                    .commit();
        }//End of if statement to hide the details fragment
         */

        fullAddress = allRestaurants.get(index).getAddressOne() + '\n' + (!allRestaurants.get(index).getAddressTwo().isEmpty() ? allRestaurants.get(index).getAddressTwo() + '\n': "") +
                allRestaurants.get(index).getCity() + ", " + allRestaurants.get(index).getState() + ' ' + allRestaurants.get(index).getZip();
        mealsS = (allRestaurants.get(index).getBreakfast().equals("T") ? "Breakfast\n" : "") + (allRestaurants.get(index).getLunch().equals("T") ? "Lunch\n" : "") +
                (allRestaurants.get(index).getDinner().equals("T") ? "Dinner\n" : "");

        restaurantName.setText(allRestaurants.get(index).getName());
        daysOpen1.setText(allRestaurants.get(index).getDaysOne());
        hoursOpen1.setText(allRestaurants.get(index).getHoursOne());
        addressFull.setText(fullAddress);
        btnPhone.setText(allRestaurants.get(index).getPhone());
        btnURL.setText(allRestaurants.get(index).getWebsite());
        mealsServed.setText(mealsS);
        /*
        meals += cursor.getInt(12) == 'T'? "Breakfast\n" : "";
        meals += cursor.getInt(13) == 'T'? "Lunch\n" : "";
        meals += cursor.getInt(14) == 'T'? "Dinner\n" : "";
        mealsServed.setText(meals);
         */
    }//End of method onItemSelected




}//End of class RestaurantListActivity
