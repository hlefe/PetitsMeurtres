package projet.jegrenier.lefevre.petitsmeutres;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jegrenier on 11/03/16.
 */
public class AssistantBdd extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    //Création table Jour
    private static final String DICTIONARY_TABLE_JOUR = "jour";
    private static final String DICTIONARY_TABLE_JOUR_CREATE =
            "CREATE TABLE " + DICTIONARY_TABLE_JOUR + " (" +
                    "id" + " INTEGER PRIMARY KEY, " +
                    "date" + " TEXT, " +
                    "description" + " TEXT);";

    //Création table Rôle
    private static final String DICTIONARY_TABLE_ROLE = "role";
    private static final String DICTIONARY_TABLE_ROLE_CREATE =
            "CREATE TABLE " + DICTIONARY_TABLE_ROLE + " (" +
                    "id" + " INTEGER PRIMARY KEY, " +
                    "titre" + " TEXT, " +
                    "description" + " TEXT, " +
                    "coupable" + " INTEGER, " +
                    "idJour" + " INTEGER," +
                    "FOREIGN KEY(idJour) REFERENCES jour(id));";

    //Création table ListeMots
    private static final String DICTIONARY_TABLE_LISTEMOTS = "listeMots";
    private static final String DICTIONARY_TABLE_LISTEMOTS_CREATE =
            "CREATE TABLE " + DICTIONARY_TABLE_LISTEMOTS + " (" +
                    "id" + " INTEGER PRIMARY KEY, " +
                    "mot1" + " TEXT, " +
                    "mot2" + " TEXT, " +
                    "mot3" + " TEXT, " +
                    "mot4" + " TEXT, " +
                    "mot5" + " TEXT, " +
                    "mot6" + " TEXT, " +
                    "mot7" + " TEXT, " +
                    "mot8" + " TEXT, " +
                    "mot9" + " TEXT, " +
                    "mot10" + " TEXT, " +
                    "mot11" + " TEXT, " +
                    "mot12" + " TEXT, " +
                    "idJour" + " INTEGER," +
                    "FOREIGN KEY(idJour) REFERENCES jour(id));";


    AssistantBdd(Context context) {
        super(context, "pmfd", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DICTIONARY_TABLE_JOUR_CREATE);
        db.execSQL(DICTIONARY_TABLE_ROLE_CREATE);
        db.execSQL(DICTIONARY_TABLE_LISTEMOTS_CREATE);
        db.execSQL("INSERT INTO jour (id,date,description)" +
                "VALUES (1,'Mardi 1 Mars','Test insertion BDd');");
        db.execSQL("INSERT INTO jour (id,date,description)" +
                "VALUES (2,'Mardi 2 Mars','Test insertion');");
        db.execSQL("INSERT INTO jour (id,date,description)" +
                "VALUES (3,'Mardi 3 Mars','Test insertid');");

    }


    //INUTILE
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}