package com.example.libven;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME= "users.db";
    public static final int DATABASE_VERSION=1;
    public static  final String TABLE_NAME="all_users";
    public static  final String NAME="name";
    public static  final String NICK="nick";
    public static  final String PASSWORD="password";
    public static  final String PHONE="phone";
    public static  final String EMAIL="email";

    public String SQL_Create="",SQL_Delete="";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        SQL_Create="CREATE TABLE "+ TABLE_NAME + " (";
        SQL_Create+=NAME+" TEXT, ";
        SQL_Create+=NICK+" TEXT, ";
        SQL_Create+=PASSWORD+" TEXT, ";
        SQL_Create+=PHONE+" TEXT, ";
        SQL_Create+=EMAIL+" TEXT);";
        sqLiteDatabase.execSQL(SQL_Create);

        //TODO add new tables here
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        SQL_Delete="DROP TABLE IF EXISTS "+TABLE_NAME;
        sqLiteDatabase.execSQL(SQL_Delete);

        //TODO add new tables here

        onCreate(sqLiteDatabase);

    }
}
