package com.example.dbadapter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stetho.initializeWithDefaults(this) ;

        new OkHttpClient.Builder ()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        //Database

        DBAdapter db = new DBAdapter(this);
        db.open();

        //Count rows in food
        int numberRows = db.count("food");

        if(numberRows < 1) {
            //Run setup
            db.insert("food", "food_id, food_name, food_manufactor_name", "NULL, 'Ham', 'Glide'");
            db.insert("food", "food_id, food_name, food_manufactor_name", "NULL, 'rote', 'inc'");

        }

        DBSetupInsert setupInsert = new DBSetupInsert(this);
        setupInsert.setupInsertToFood("NULL, 'Nottemiks', 'first price', '600', 'gram', '1', 'pakke', '512', '16.1', '37.1', '32.3', '3 072', '97', '223', '194', 'NULL', 'NULL', '42', 'first_price', 'first_price_nootmiks_a_jpg', 'first_price_nootmiks_b_jpg', 'first_price_nootmiks_c_jpg', 'NULL'");


        db.close();

    }
}
