package DB;

import android.app.Activity;

import Model.Person;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.IpSecAlgorithm;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class PersonDA {
    private SqliteDB sqliteDB;
    private SQLiteDatabase database;
    private String[] cls = {SqliteDB.TABLE_NAME_FULLNAME, SqliteDB.TABLE_NAME_USERNAME, SqliteDB.TABLE_NAME_USERNAME, SqliteDB.TABLE_NAME_EMAIL, SqliteDB.TABLE_NAME_PHONENUMBER};

    public PersonDA(Activity context) {
        sqliteDB = new SqliteDB(context);
    }

    public void openDB() {
        database = sqliteDB.getWritableDatabase();
    }

    public void closeDB() {
        database.close();
    }

    public boolean addNewData(Person person) {

        try {

            database.execSQL("INSERT INTO " + SqliteDB.TABLE_NAME + " (" + SqliteDB.TABLE_NAME_FULLNAME + " , "
                    + SqliteDB.TABLE_NAME_USERNAME + " , "
                    + SqliteDB.TABLE_NAME_PASSWORD + " , "
                    + SqliteDB.TABLE_NAME_PHONENUMBER + " , "
                    + SqliteDB.TABLE_NAME_EMAIL + ") "
                    + "VALUES (" + "'" + person.getFullName() + "'" + " , "
                    + "'" + person.getUsername() + "'" + " , "
                    + "'" + person.getPassword() + "'" + " , "
                    + "'" + person.getPhoneNumber() + "'" + " , "
                    + "'" + person.getEmail() + "'" + ")");

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    public List<Person> personList() {
        Cursor cursor = database.rawQuery("SELECT * FROM " + SqliteDB.TABLE_NAME, null);

        ArrayList<Person> personArrayList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String fullNme = cursor.getString(cursor.getColumnIndex(SqliteDB.TABLE_NAME_FULLNAME));
                String username = cursor.getString(cursor.getColumnIndex(SqliteDB.TABLE_NAME_USERNAME));
                String pass = cursor.getString(cursor.getColumnIndex(SqliteDB.TABLE_NAME_PASSWORD));
                String email = cursor.getString(cursor.getColumnIndex(SqliteDB.TABLE_NAME_EMAIL));
                String phoneNumber = cursor.getString(cursor.getColumnIndex(SqliteDB.TABLE_NAME_PHONENUMBER));

                personArrayList.add(new Person(fullNme, email, phoneNumber, username, pass));
            } while (cursor.moveToNext());
        }
        return personArrayList;

    }

    public Person person(String username, String password) {

        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + SqliteDB.TABLE_NAME
                    + " WHERE " + SqliteDB.TABLE_NAME_USERNAME + " = '" + username + "' and " + SqliteDB.TABLE_NAME_PASSWORD + " = '" + password + "'", null);
            Person person = null;
            if (cursor.moveToFirst()) {
                String fullNme = cursor.getString(cursor.getColumnIndex(SqliteDB.TABLE_NAME_FULLNAME));
                String email = cursor.getString(cursor.getColumnIndex(SqliteDB.TABLE_NAME_EMAIL));
                String phoneNumber = cursor.getString(cursor.getColumnIndex(SqliteDB.TABLE_NAME_PHONENUMBER));

                person = new Person(fullNme, email, phoneNumber, username, password);
            }
            return person;
        } catch (Exception e) {
            return null;
        }

    }

}
