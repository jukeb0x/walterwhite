package fr.mm.walterwhite.dao;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import fr.mm.walterwhite.dao.impl.IConsommationDao;
import fr.mm.walterwhite.dao.impl.IIngredientDao;
import fr.mm.walterwhite.dao.impl.IRecipeContentDao;
import fr.mm.walterwhite.dao.impl.IRecipeDao;
import fr.mm.walterwhite.dao.impl.IWeightDao;
import fr.mm.walterwhite.models.Consommation;
import fr.mm.walterwhite.models.Ingredient;
import fr.mm.walterwhite.models.Recipe;
import fr.mm.walterwhite.models.RecipeContent;
import fr.mm.walterwhite.models.Weight;

@Database(entities = {Consommation.class, Ingredient.class, Recipe.class, RecipeContent.class, Weight.class}, version = 7, exportSchema = false)
public abstract class DatabaseHelper extends RoomDatabase {



        // --- SINGLETON ---
        private static volatile DatabaseHelper INSTANCE;

        // --- DAO ---
        public abstract IConsommationDao consoDao();
        public abstract IIngredientDao ingredientDao();
        public abstract IRecipeContentDao recipeContentDao();
        public abstract IRecipeDao recipeDao();
        public abstract IWeightDao weightDao();

    static final Migration MIGRATION_2_3 = new Migration(6, 7) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("DELETE FROM consommation");
                      ContentValues contentValues = new ContentValues();
            contentValues.put("name", "Lait");
            contentValues.put("meal", "PETIT-DEJEUNER");
            contentValues.put("points", 3);
            contentValues.put("date", "02/08/2020");
            contentValues.put("portion", 25);
            database.insert("Consommation", OnConflictStrategy.IGNORE, contentValues);
        }
    };


    // --- INSTANCE ---
        public static DatabaseHelper getInstance(Context context) {
            if (INSTANCE == null) {
                synchronized (DatabaseHelper.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                DatabaseHelper.class, "walterwhite.db")
                                .addCallback(prepopulateDatabase())
                                .addMigrations(MIGRATION_2_3)
                                .build();
                    }
                }
            }
            return INSTANCE;
        }

        // ---

        private static Callback prepopulateDatabase(){
            return new Callback() {

                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);

                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name", "Banane");
                    contentValues.put("meal", "PETIT-DEJEUNER");
                    contentValues.put("points", 0);
                    contentValues.put("date", "02/08/2020");
                    contentValues.put("portion", 100);
                    db.insert("Consommation", OnConflictStrategy.IGNORE, contentValues);
                }
            };
        }
    }
