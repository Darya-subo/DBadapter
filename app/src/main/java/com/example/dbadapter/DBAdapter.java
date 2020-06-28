package com.example.dbadapter;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

    private static final String databaseName = "fitCalc";
    private static  final int databaseVersion = 7;

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
    public DBAdapter (Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper (context);
    }


    //DatabaseHelper
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super (context, databaseName, null, databaseVersion);
        }

        @Override
        public void onCreate (SQLiteDatabase db) {
            try {
                   //Create tables
                db.execSQL("CREATE TABLE IF NOT EXISTS food (" +
                        " food_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " food_name VARCHAR, " +
                        " food_manufactor_name VARCHAR, " +
                        " food_serving_size DOUBLE, " +
                        " food_serving_mesurment VARCHAR, " +
                        " food_serving_name_number DOUBLE, " +
                        " food_serving_name_word VARCHAR, " +
                        " food_energy DOUBLE, " +
                        " food_proteins DOUBLE, " +
                        " food_carbohydrates DOUBLE, " +

                        " food_fat DOUBLE, " +
                        " food_energy_calculated DOUBLE, " +
                        " food_proteins_calculated DOUBLE, " +
                        " food_carbohydrates_calculated DOUBLE, " +
                        " food_fat_calculated DOUBLE, " +
                        " food_user_id INT, " +
                        " food_barcode VARCHAR, " +
                        " food_category_id INT, " +
                        " food_thumb VARCHAR, " +
                        " food_image_a VARCHAR, " +

                        " food_image_b VARCHAR, " +
                        " food_image_c VARCHAR, " +
                        " food_note VARCHAR);");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
          //All tables that are going to be dropped need to be lested here
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS food");
            onCreate(db);
            String TAG = "Tag";
            Log.w(TAG, "Upgrading database from version " + oldVersion + "to " + newVersion + ", which will destroy all old data" );
        }

    }

    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close() {DBHelper.close(); }

    /* Insert data to the database----------*/
    public  void  insert(String table, String fields, String values) {
        db.execSQL("INSERT INTO " + table + "(" + fields + ") VALUES (" + values + ")");
    }

    //Count
     public int count(String table)
     {
         Cursor mCount = db.rawQuery("SELECT COUNT(*) FROM " + table + "", null);
         mCount.moveToFirst();
         int count = mCount.getInt(0);
         mCount.close();
         return count;
     }
}
