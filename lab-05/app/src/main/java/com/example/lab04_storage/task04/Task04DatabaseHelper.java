package com.example.lab04_storage.task04;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lab04_storage.utils.MD5Utils;

public class Task04DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "StudentManagement.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_NAME = "tbllop";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CLASS_CODE = "malop";
    public static final String COLUMN_CLASS_NAME = "tenlop";
    public static final String COLUMN_STUDENT_COUNT = "siso";

    public static final String TABLE_USERS = "tblusers";
    public static final String COL_USER_ID = "_id";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";

    public Task04DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CLASS_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CLASS_CODE + " TEXT UNIQUE, " +
                COLUMN_CLASS_NAME + " TEXT, " +
                COLUMN_STUDENT_COUNT + " INTEGER" + ")";

        db.execSQL(CREATE_CLASS_TABLE);

        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS + " (" +
                COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USERNAME + " TEXT UNIQUE, " +
                COL_PASSWORD + " TEXT" + ")";

        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public boolean registerUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String hashedPassword = MD5Utils.encrypt(password);

        values.put(COL_USERNAME, username);
        values.put(COL_PASSWORD, hashedPassword);

        long result = db.insert(TABLE_USERS, null, values);
        db.close();

        return result != -1;
    }

    public boolean checkUserLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String hashedPassword = MD5Utils.encrypt(password);

        String[] columns = { COL_USER_ID };
        String selection = COL_USERNAME + "=? AND " + COL_PASSWORD + "=?";
        String[] args = { username, hashedPassword };

        Cursor cursor = db.query(TABLE_USERS, columns, selection, args, null, null, null);

        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count > 0;
    }

    public void insertClass(String code, String name, int count) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_CLASS_CODE, code);
        values.put(COLUMN_CLASS_NAME, name);
        values.put(COLUMN_STUDENT_COUNT, count);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void updateClass(String code, String name, int count) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_CLASS_NAME, name);
        values.put(COLUMN_STUDENT_COUNT, count);

        db.update(TABLE_NAME, values, COLUMN_CLASS_CODE + "=?", new String[]{code});
        db.close();
    }

    public void deleteClass(String code) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_CLASS_CODE + "=?", new String[]{code});
        db.close();
    }

    public Cursor getAllClasses() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query =
                "SELECT rowid AS " + COLUMN_ID +
                        ", " + COLUMN_CLASS_CODE +
                        ", " + COLUMN_CLASS_NAME +
                        ", " + COLUMN_STUDENT_COUNT +
                        " FROM " + TABLE_NAME;

        return db.rawQuery(query, null);
    }
}
