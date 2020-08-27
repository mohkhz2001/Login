package DB;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqliteDB extends SQLiteOpenHelper {

    public static final String DB_NAME = "info.db";
    public static final int DB_VERSION = 1;

    public SqliteDB(Activity context ){
        super(context, DB_NAME, null, DB_VERSION);
    }
        //DB TABLE
    public static final String TABLE_NAME = "user";
    public static final String TABLE_NAME_FULLNAME = "fullName";
    public static final String TABLE_NAME_EMAIL = "email";
    public static final String TABLE_NAME_USERNAME = "username";
    public static final String TABLE_NAME_PASSWORD = "password";
    public static final String TABLE_NAME_PHONENUMBER = "phoneNumber";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query  = "CREATE TABLE " +  TABLE_NAME + "("
                + TABLE_NAME_FULLNAME + " TEXT ," + TABLE_NAME_EMAIL + " TEXT ,"
                + TABLE_NAME_USERNAME + " TEXT , "
                + TABLE_NAME_PASSWORD + " TEXT , "
                + TABLE_NAME_PHONENUMBER + " TEXT )";
        sqLiteDatabase.execSQL( "CREATE TABLE IF NOT EXISTS " +  TABLE_NAME + "("
                + TABLE_NAME_FULLNAME + " TEXT ," + TABLE_NAME_EMAIL + " TEXT ,"
                + TABLE_NAME_USERNAME + " TEXT , "
                + TABLE_NAME_PASSWORD + " TEXT , "
                + TABLE_NAME_PHONENUMBER + " TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

}
