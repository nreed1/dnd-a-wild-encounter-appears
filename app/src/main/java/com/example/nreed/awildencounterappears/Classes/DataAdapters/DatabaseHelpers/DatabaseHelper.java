package com.example.nreed.awildencounterappears.Classes.DataAdapters.DatabaseHelpers;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nreed.awildencounterappears.Classes.DataAdapters.Database.MonsterTable;
import com.example.nreed.awildencounterappears.Classes.DataAdapters.Database.XPThresholdsByCharacterTable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by nreed on 7/7/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static String DB_PATH = "/data/data/com.example.nreed.awildencounterappears/databases/AWildEncounterAppears.db";
    private static final String DATABASE_NAME = "AWildEncounterAppears.db";
    private static final int DATABASE_VERSION = 2;
    private SQLiteDatabase myDataBase;

    private final Context myContext;

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        myContext=context;
        DB_PATH=myContext.getDatabasePath(DATABASE_NAME).toString();
    }

    public void createDataBase() throws IOException{

        boolean dbExist = checkDataBase();

        if(dbExist){
            //do nothing - database already exist
        }else{

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
            this.close();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH ;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH ;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }
        @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
      //  MonsterTable.onCreate(sqLiteDatabase);
       // XPThresholdsByCharacterTable.onCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i1 > i){
            //sqLiteDatabase.();
        }else {
            //MonsterTable.onUpgrade(sqLiteDatabase, i, i1);
           // XPThresholdsByCharacterTable.onUpgrade(sqLiteDatabase, i, i1);
        }
    }
}
