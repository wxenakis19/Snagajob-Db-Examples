package com.example.williamxenakis.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

/**
 * Created by william.xenakis on 6/20/17.
 */

public class sqlDBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "SQLitePeople";
    private static final String PEOPLE = "People";
    private static final String KEY_UUID = "UUID";
    private static final String KEY_NAME = "name";
    private static final String KEY_SH_ADDR = "home_address";
    private static final String KEY_MEMBER_ID = "memberId";
    private static final String KEY_JOB_SEEKER_ID = "jobSeekerId";
    private static final String KEY_AGE = "age";

    public sqlDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + PEOPLE + "("
                + KEY_UUID + " STRING PRIMARY KEY," + KEY_MEMBER_ID + " TEXT," + KEY_JOB_SEEKER_ID + " TEXT," + KEY_NAME + " TEXT,"
                + KEY_SH_ADDR + " TEXT,"  + KEY_AGE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion<7){
            db.execSQL("ALTER TABLE "+ PEOPLE + " ADD COLUMN " + "birthYear" + " TEXT;");
        }
        else{
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
            // Creating tables again
            onCreate(db);
        }
    }

    public void addPerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, person.getName());
        values.put(KEY_SH_ADDR, person.getAddress());
        values.put(KEY_AGE, person.getAge());
        values.put(KEY_JOB_SEEKER_ID, person.getJobSeekerId());
        values.put(KEY_MEMBER_ID, person.getMemberId());
        values.put(KEY_UUID, person.getUUID());
//        values.put("birthYear", person.getBirthYear);

        // Inserting Row
        db.insertOrThrow(PEOPLE, null, values);
        db.close(); // Closing database connection
    }

    public int updatePerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, person.getName());
        // updating row
        return db.update(PEOPLE, values, KEY_UUID + " = ?",
                new String[]{String.valueOf(person.getUUID())});
    }

    public Person getPerson(String UUId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from People WHERE UUID = '" + UUId + "'", null);
        if (cursor != null)
            cursor.moveToFirst();
        Person contact = new Person(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), Integer.parseInt(cursor.getString(5)));
        cursor.close();
        // return person
        return contact;
    }
}
