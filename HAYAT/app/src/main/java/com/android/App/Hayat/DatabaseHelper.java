package com.android.App.Hayat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database ------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "HayatApp.db";

    /*
    ------------------------------------------------------------------------------------------------
    ------------------------------------------------------------------------------------------------
    Tables Creation --------------------------------------------------------------------------------
    ------------------------------------------------------------------------------------------------
    ------------------------------------------------------------------------------------------------
    */

    // User table ----------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    // User table name
    private static final String TABLE_USER = "user";
    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_FULLNAME = "user_fullname";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PHONE = "user_phone";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_SEXE = "user_sexe";
    private static final String COLUMN_USER_AGE = "user_age";
    private static final String COLUMN_USER_BLOOD = "user_blood";
    private static final String COLUMN_USER_ADRESSE = "user_adresse";
    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_FULLNAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT,"
            + COLUMN_USER_PHONE + " TEXT,"
            + COLUMN_USER_PASSWORD + " TEXT,"
            + COLUMN_USER_SEXE + " TEXT,"
            + COLUMN_USER_AGE + " TEXT,"
            + COLUMN_USER_BLOOD + " TEXT,"
            + COLUMN_USER_ADRESSE + " TEXT" + ")";
    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    // Request table -------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    // Request table name
    private static final String TABLE_REQUEST = "request";
    // Request Table Columns names
    private static final String COLUMN_REQUEST_ID = "request_id";
    private static final String COLUMN_REQUEST_FULLNAME = "request_fullname";
    private static final String COLUMN_REQUEST_PHONE = "request_phone";
    private static final String COLUMN_REQUEST_BLOODGROUP = "request_bloodgroup";
    private static final String COLUMN_REQUEST_AGE = "request_age";
    private static final String COLUMN_REQUEST_ADRESSE = "request_adresse";
    private static final String COLUMN_REQUEST_USERID = "request_userid";
    // create table sql query
    private String CREATE_REQUEST_TABLE = "CREATE TABLE " + TABLE_REQUEST + "("
            + COLUMN_REQUEST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_REQUEST_FULLNAME + " TEXT,"
            + COLUMN_REQUEST_PHONE + " TEXT,"
            + COLUMN_REQUEST_BLOODGROUP + " TEXT,"
            + COLUMN_REQUEST_AGE + " TEXT,"
            + COLUMN_REQUEST_ADRESSE + " TEXT,"
            + COLUMN_REQUEST_USERID + " INTEGER" + ")";
    // drop table sql query
    private String DROP_REQUEST_TABLE  = "DROP TABLE IF EXISTS " + TABLE_REQUEST;

    // Request table -------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    // Request table name
    private static final String TABLE_DONNATION = "donnation";
    // Request Table Columns names
    private static final String COLUMN_DONNATION_ID = "donnation_id";
    private static final String COLUMN_DONNATION_FULLNAME = "donnation_fullname";
    private static final String COLUMN_DONNATION_PHONE = "donnation_phone";
    private static final String COLUMN_DONNATION_BLOODGROUP = "donnation_bloodgroup";
    private static final String COLUMN_DONNATION_AGE = "donnation_age";
    private static final String COLUMN_DONNATION_ADRESSE = "donnation_adresse";
    private static final String COLUMN_DONNATION_USERID = "donnation_userid";
    // create table sql query
    private String CREATE_DONNATION_TABLE = "CREATE TABLE " + TABLE_DONNATION + "("
            + COLUMN_DONNATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_DONNATION_FULLNAME + " TEXT,"
            + COLUMN_DONNATION_PHONE + " TEXT,"
            + COLUMN_DONNATION_BLOODGROUP + " TEXT,"
            + COLUMN_DONNATION_AGE + " TEXT,"
            + COLUMN_DONNATION_ADRESSE + " TEXT,"
            + COLUMN_DONNATION_USERID + " INTEGER" + ")";
    // drop table sql query
    private String DROP_DONNATION_TABLE  = "DROP TABLE IF EXISTS " + TABLE_DONNATION;
    /*
    ------------------------------------------------------------------------------------------------
    ------------------------------------------------------------------------------------------------
    Constructor --------------------------------------------------------------------------------
    ------------------------------------------------------------------------------------------------
    ------------------------------------------------------------------------------------------------
    */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_REQUEST_TABLE);
        db.execSQL(CREATE_DONNATION_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        //Drop Resuest Table if exist
        db.execSQL(DROP_REQUEST_TABLE);
        //Drop Resuest Table if exist
        db.execSQL(DROP_DONNATION_TABLE);
        // Create tables again
        onCreate(db);
    }

    /*
    ------------------------------------------------------------------------------------------------
    ------------------------------------------------------------------------------------------------
    User Table Functions --------------------------------------------------------------------------------
    ------------------------------------------------------------------------------------------------
    ------------------------------------------------------------------------------------------------
    */

    // This method is to create user record---------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public Boolean addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_FULLNAME, user.getFullName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PHONE, user.getPhone());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_SEXE, user.getSexe());
        values.put(COLUMN_USER_AGE, user.getAge());
        values.put(COLUMN_USER_BLOOD, user.getBlood());
        values.put(COLUMN_USER_ADRESSE, user.getAdresse());
        // Inserting Row
        long result = db.insert(TABLE_USER, null, values);
        db.close();
        if(result == -1) return false;
        return true;
    }
    // This method is to update user record---------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_FULLNAME, user.getFullName());
        values.put(COLUMN_USER_PHONE, user.getPhone());
        values.put(COLUMN_USER_SEXE, user.getSexe());
        values.put(COLUMN_USER_AGE, user.getAge());
        values.put(COLUMN_USER_BLOOD, user.getBlood());
        values.put(COLUMN_USER_ADRESSE, user.getAdresse());
        // updating row
        int nbrUpdatedRows = db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
        return nbrUpdatedRows;
    }
    // This method is to delete user record---------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }
    // This method to check user exist or not by email ---------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public boolean checkUserByEmail(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";
        // selection argument
        String[] selectionArgs = {email};
        // query user table with condition
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
    // This method to check user exist or not by email and password --------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public boolean checkUser(String email, String password) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        // selection arguments
        String[] selectionArgs = {email, password};
        // query user table with conditions
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
    // This method to check user exist or not ------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public User getUser(String email, String password) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_FULLNAME,
                COLUMN_USER_EMAIL,
                COLUMN_USER_PHONE,
                COLUMN_USER_SEXE,
                COLUMN_USER_AGE,
                COLUMN_USER_BLOOD,
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        // selection arguments
        String[] selectionArgs = {email, password};
        // query user table with condition
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        User user = new User();
        if (cursor.moveToFirst()){
            do {
                // Passing values
                user.setId(cursor.getInt(0));
                user.setFullName(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setPhone(cursor.getString(3));
                user.setSexe(cursor.getString(4));
                user.setAge(cursor.getString(5));
                user.setBlood(cursor.getString(6));
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return user;
    }
    /*
    ------------------------------------------------------------------------------------------------
    ------------------------------------------------------------------------------------------------
    Request Table Functions --------------------------------------------------------------------------------
    ------------------------------------------------------------------------------------------------
    ------------------------------------------------------------------------------------------------
    */

    // This method is to create request record---------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public Boolean addRequest(Request request) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_REQUEST_FULLNAME, request.getFullName());
        values.put(COLUMN_REQUEST_PHONE, request.getPhoneNumber());
        values.put(COLUMN_REQUEST_BLOODGROUP, request.getBloodGroup());
        values.put(COLUMN_REQUEST_AGE, request.getAge());
        values.put(COLUMN_REQUEST_ADRESSE, request.getAdresse());
        values.put(COLUMN_REQUEST_USERID, request.getUserId());
        // Inserting Row
        long result = db.insert(TABLE_REQUEST, null, values);
        db.close();
        if(result == -1) return false;
        return true;
    }
    // This method is to get all request records----------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public ArrayList<Request> getAllRequests(){
        // array of columns to fetch
        String[] columns = {
                COLUMN_REQUEST_ID,
                COLUMN_REQUEST_FULLNAME,
                COLUMN_REQUEST_PHONE,
                COLUMN_REQUEST_BLOODGROUP,
                COLUMN_REQUEST_AGE,
                COLUMN_REQUEST_ADRESSE,
                COLUMN_REQUEST_USERID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // query user table with condition
        Cursor cursor = db.query(TABLE_REQUEST, //Table to query
                columns,                    //columns to return
                null,                       //columns for the WHERE clause
                null,                       //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        ArrayList<Request> RequestList = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                // Passing values
                RequestList.add(new Request(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getInt(6)
                ));
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return RequestList;
    }
    // This method is to get a request by Id--------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public Request getRequestById(String requestId){
        // array of columns to fetch
        String[] columns = {
                COLUMN_REQUEST_ID,
                COLUMN_REQUEST_FULLNAME,
                COLUMN_REQUEST_PHONE,
                COLUMN_REQUEST_BLOODGROUP,
                COLUMN_REQUEST_AGE,
                COLUMN_REQUEST_ADRESSE,
                COLUMN_REQUEST_USERID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_REQUEST_ID + " = ?";
        // selection arguments
        String[] selectionArgs = {requestId};
        // query user table with condition
        Cursor cursor = db.query(TABLE_REQUEST, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        Request request = new Request(-1,"","","","","",-1);
        if (cursor.moveToFirst()){
            do {
                // Passing values
                request.setId(cursor.getInt(0));
                request.setFullName(cursor.getString(1));
                request.setPhoneNumber(cursor.getString(2));
                request.setBloodGroup(cursor.getString(3));
                request.setAge(cursor.getString(4));
                request.setAdresse(cursor.getString(5));
                request.setUserId(cursor.getInt(6));
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return request;
    }
    // This method is to delete request record------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public Boolean deleteRequest(int RequestId) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete request record by id
        int nbrDeletedRowsdb = db.delete(TABLE_REQUEST, COLUMN_REQUEST_ID + " = ?",
                new String[]{String.valueOf(RequestId)});
        db.close();
        if(nbrDeletedRowsdb>0) return true;
        return false;
    }

    /*
    ------------------------------------------------------------------------------------------------
    ------------------------------------------------------------------------------------------------
    Request Table Functions --------------------------------------------------------------------------------
    ------------------------------------------------------------------------------------------------
    ------------------------------------------------------------------------------------------------
    */

    // This method is to create donnation record---------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public Boolean addDonnation(Donnation donnation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DONNATION_FULLNAME, donnation.getFullName());
        values.put(COLUMN_DONNATION_PHONE, donnation.getPhoneNumber());
        values.put(COLUMN_DONNATION_BLOODGROUP, donnation.getBloodGroup());
        values.put(COLUMN_DONNATION_AGE, donnation.getAge());
        values.put(COLUMN_DONNATION_ADRESSE, donnation.getAdresse());
        values.put(COLUMN_DONNATION_USERID, donnation.getUserId());
        // Inserting Row
        long result = db.insert(TABLE_DONNATION, null, values);
        db.close();
        if(result == -1) return false;
        return true;
    }
    // This method is to get all request records----------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public ArrayList<Donnation> getAllDonnations(){
        // array of columns to fetch
        String[] columns = {
                COLUMN_DONNATION_ID,
                COLUMN_DONNATION_FULLNAME,
                COLUMN_DONNATION_PHONE,
                COLUMN_DONNATION_BLOODGROUP,
                COLUMN_DONNATION_AGE,
                COLUMN_DONNATION_ADRESSE,
                COLUMN_DONNATION_USERID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // query user table with condition
        Cursor cursor = db.query(TABLE_DONNATION, //Table to query
                columns,                    //columns to return
                null,                       //columns for the WHERE clause
                null,                       //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        ArrayList<Donnation> DonnationList = new ArrayList<Donnation>();
        if (cursor.moveToFirst()){
            do {
                // Passing values
                DonnationList.add(new Donnation(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getInt(6)
                        ));
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return DonnationList;
    }
    // This method is to get a donnation by Id------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public Donnation getDonnationById(String donnationId){
        // array of columns to fetch
        String[] columns = {
                COLUMN_DONNATION_ID,
                COLUMN_DONNATION_FULLNAME,
                COLUMN_DONNATION_PHONE,
                COLUMN_DONNATION_BLOODGROUP,
                COLUMN_DONNATION_AGE,
                COLUMN_DONNATION_ADRESSE,
                COLUMN_DONNATION_USERID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_DONNATION_ID + " = ?";
        // selection arguments
        String[] selectionArgs = {donnationId};
        // query user table with condition
        Cursor cursor = db.query(TABLE_DONNATION, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        Donnation donnation = new Donnation(-1,"","","","","",-1);
        if (cursor.moveToFirst()){
            do {
                // Passing values
                donnation.setId(cursor.getInt(0));
                donnation.setFullName(cursor.getString(1));
                donnation.setPhoneNumber(cursor.getString(2));
                donnation.setBloodGroup(cursor.getString(3));
                donnation.setAge(cursor.getString(4));
                donnation.setAdresse(cursor.getString(5));
                donnation.setUserId(cursor.getInt(6));
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return donnation;
    }
    // This method is to delete donnation record----------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public Boolean deleteDonnation(int DonnationId) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete request record by id
        int nbrDeletedRowsdb = db.delete(TABLE_DONNATION, COLUMN_DONNATION_ID + " = ?",
                new String[]{String.valueOf(DonnationId)});
        db.close();
        if(nbrDeletedRowsdb>0) return true;
        return false;
    }
}
