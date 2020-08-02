package fr.mm.walterwhite.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public abstract class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "walterwhite";

    private DatabaseHelper singleton=null;

    // Table name: Note.


    public DatabaseHelper(Context context)  {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Execute Script.
       /* db.execSQL(Constants.CREATE_CONSOMMATION_TABLE_SCRIPT);
        db.execSQL(Constants.CREATE_INGREDIENT_TABLE_SCRIPT);
        db.execSQL(Constants.CREATE_RECIPE_TABLE_SCRIPT);
        db.execSQL(Constants.CREATE_RECIPECONTENT_TABLE_SCRIPT);
        db.execSQL(Constants.CREATE_WEIGHT_TABLE_SCRIPT);*/
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");
        // Drop older table if existed
      /*  db.execSQL(Constants.DROP_CONSOMMATION_TABLE_SCRIPT);
        db.execSQL(Constants.DROP_INGREDIENT_TABLE_SCRIPT);
        db.execSQL(Constants.DROP_RECIPE_TABLE_SCRIPT);
        db.execSQL(Constants.DROP_RECIPECONTENT_TABLE_SCRIPT);
        db.execSQL(Constants.DROP_WEIGHT_TABLE_SCRIPT);*/

        // Create tables again
        onCreate(db);
    }


    // If Note table has no data
    // default, Insert 2 records.
    public void createDefaultIfNeed()  {
      /*  int count = this.getNotesCount();
        if(count ==0 ) {
            Note note1 = new Note("Firstly see Android ListView",
                    "See Android ListView Example in o7planning.org");
            Note note2 = new Note("Learning Android SQLite",
                    "See Android SQLite Example in o7planning.org");
            this.addNote(note1);
            this.addNote(note2);
        }*/
    }

}
