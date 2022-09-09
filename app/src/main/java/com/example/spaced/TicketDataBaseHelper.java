package com.example.spaced;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TicketDataBaseHelper extends SQLiteOpenHelper {


    public static final String COLUMN_CUSTOMER = "CUSTOMER";
    public static final String CUSTOMER_TABLE = COLUMN_CUSTOMER + "_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_CUSTOMER_EMAIL = "CUSTOMER_EMAIL";
    public static final String COLUMN_CUSTOMER_PHONENUMBER = "CUSTOMER_PHONENUMBER";
    public static final String COLUMN_CUSTOMER_CREDITCARD = "CUSTOMER_CREDITCARD";
    public static final String COLUMN_CUSTOMER_DEBITCARD = "CUSTOMER_DEBITCARD";
    public static final String COLUMN_CUSTOMER_DOE = "CUSTOMER_DOE";
    public static final String COLUMN_CUSTOMER_CVC = "CUSTOMER_CVC";
    public static final String COLUMN_CUSTOMER_PLANET = "CUSTOMER_PLANET";
    public static final String COLUMN_CUSTOMER_LAUNCHPAD = "CUSTOMER_LAUNCHPAD";

    public TicketDataBaseHelper(@Nullable Context context) {
        super(context, "ticket.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTicketTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER + " TEXT, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_AGE + " TEXT, " + COLUMN_CUSTOMER_EMAIL + " TEXT, " + COLUMN_CUSTOMER_PHONENUMBER + " TEXT, " + COLUMN_CUSTOMER_CREDITCARD + " TEXT, " + COLUMN_CUSTOMER_DEBITCARD + " TEXT, " + COLUMN_CUSTOMER_DOE + " TEXT, " + COLUMN_CUSTOMER_CVC + " TEXT, " + COLUMN_CUSTOMER_PLANET + " TEXT, " + COLUMN_CUSTOMER_LAUNCHPAD + " TEXT)";

        db.execSQL(createTicketTableStatement);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOneCustomer(TicketModel ticketModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMER, ticketModel.getCustomer());
        cv.put(COLUMN_CUSTOMER_NAME, ticketModel.getCustomerName());
        cv.put(COLUMN_CUSTOMER_AGE, ticketModel.getAge());
        cv.put(COLUMN_CUSTOMER_EMAIL, ticketModel.getEmail());
        cv.put(COLUMN_CUSTOMER_PHONENUMBER, ticketModel.getPhoneNumber());
        cv.put(COLUMN_CUSTOMER_CREDITCARD, ticketModel.getCreditCard());
        cv.put(COLUMN_CUSTOMER_DEBITCARD, ticketModel.getDebitCard());
        cv.put(COLUMN_CUSTOMER_DOE, ticketModel.getDateOfExpiration());
        cv.put(COLUMN_CUSTOMER_CVC, ticketModel.getCvc());
        cv.put(COLUMN_CUSTOMER_PLANET, ticketModel.getPlanet());
        cv.put(COLUMN_CUSTOMER_LAUNCHPAD, ticketModel.getLaunchPad());


        long insert = db.insert(CUSTOMER_TABLE, null, cv);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean DeleteOneCustomer(TicketModel ticketModel)
    {
        // find ticketModel in the database. if it found, delete it and return true.
        // if it is not found, return false.

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM" + CUSTOMER_TABLE + "WHERE" + COLUMN_ID + " = " + ticketModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }
    }

    public List<TicketModel> getEveryCustomer()
    {
        List<TicketModel> returnList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + CUSTOMER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new customer objects. Put them into the return list.
            do {
                int customerID = cursor.getInt(0);
                String customer = cursor.getString(1);
                String customerName = cursor.getString(2);
                String customerAge = cursor.getString(3);
                String customerEmail = cursor.getString(4);
                String customerPhoneNumber = cursor.getString(5);
                String customerCreditCard = cursor.getString(6);
                String customerDebitCard = cursor.getString(7);
                String customerDOE = cursor.getString(8);
                String customerCvc = cursor.getString(9);
                String customerPlanet = cursor.getString(10);
                String customerLaunchPad = cursor.getString(11);

                TicketModel newCustomer = new TicketModel(customerID, customer, customerName, customerAge, customerEmail, customerPhoneNumber, customerCreditCard, customerDebitCard, customerDOE,
                        customerCvc, customerPlanet, customerLaunchPad);
                returnList.add(newCustomer);
            } while (cursor.moveToNext());
        }
        else {
            // failure. do not add anything to the list.
        }

        // close both the cursor and the db when done.
        cursor.close();
        db.close();
        return returnList;
    }
}
