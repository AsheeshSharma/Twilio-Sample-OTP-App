package com.knetworktask.asheeshsharma.kisaannetwork.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.knetworktask.asheeshsharma.kisaannetwork.Model.SentMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asheesh.Sharma on 12-11-2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "messageManager";

    // Contacts table name
    private static final String TABLE_CONTACTS = "messages";

    // Contacts Table Columns names
    private static final String KEY_ID = "number";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE ="created_at";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MESSAGE_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + " ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+ KEY_ID + " TEXT," + KEY_DATE  + " DATETIME," + KEY_MESSAGE + " TEXT,"
                + KEY_NAME + " TEXT" + ")";
        db.execSQL(CREATE_MESSAGE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void addContact(SentMessage sentMessage) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, sentMessage.getmNumber());
        values.put(KEY_NAME, sentMessage.getmName());
        values.put(KEY_MESSAGE, sentMessage.getmText());
        values.put(KEY_DATE, sentMessage.getmDate());
        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public SentMessage getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_MESSAGE, KEY_DATE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        SentMessage contact = new SentMessage(cursor.getString(0),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return contact
        return contact;
    }

    // Getting All Contacts
    public List<SentMessage> getAllContacts() {
        List<SentMessage> contactList = new ArrayList<SentMessage>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                /*Log.d(" Values ", cursor.getString(0) + "-" + cursor.getString(3) + "-" + cursor.getString(2) + "-" + cursor.getString(1));*/
                SentMessage contact = new SentMessage();
                contact.setmNumber(cursor.getString(1));
                contact.setmName(cursor.getString(4));
                contact.setmText(cursor.getString(3));
                contact.setmDate(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Updating single contact
    public int updateContact(SentMessage contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getmName());
        values.put(KEY_MESSAGE, contact.getmText());
        values.put(KEY_ID, contact.getmNumber());
        values.put(KEY_DATE, contact.getmDate());
        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getmNumber()) });
    }

    // Deleting single contact
    public void deleteContact(SentMessage contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getmNumber()) });
        db.close();
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
