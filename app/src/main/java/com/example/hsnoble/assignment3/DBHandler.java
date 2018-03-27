package com.example.hsnoble.assignment3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Stephen Noble on 3/26/2018.
 */

public class DBHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Team_DB";
    //table name
    private static final String TABLE_STUDENT_DETAIL = "teamDeatails";

    //table column names
    private static final String PK = "id";
    private static final String AUTHOR  = "author";
    private static final String TEAM_NAME = "teamName";
    private static final String CITY = "city";
    private static final String SPORT = "sport";
    private static final String MVP = "mvp";
    private static final String STADIUM = "stadium";

    SQLiteDatabase db;

    //constructor
    public DBHandler(Context c) {super(c, DATABASE_NAME, null, DATABASE_VERSION);}
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_TEAM_TABLE = "CREATE TABLE " + TABLE_STUDENT_DETAIL + "("
                + PK + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AUTHOR + " TEXT, "
                + TEAM_NAME + " TEXT, "
                + CITY + " TEXT NOT NULL, "
                + SPORT  + " TEXT, "
                + MVP + " TEXT, "
                + STADIUM + " TEXT" + " )";
        //execute SQL
        db.execSQL(CREATE_TEAM_TABLE);
        this.db = db;
    }
    public void insert(Team t)
    {
        db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        //add columns
        v.put(AUTHOR, t.getAuthorName());
        v.put(TEAM_NAME, t.getTeamName());
        v.put(CITY, t.getCity());
        v.put(SPORT, t.getSport());
        v.put(MVP, t.getMvp());
        v.put(STADIUM, t.getStadium());

        long num = db.insert(TABLE_STUDENT_DETAIL, null, v);
        System.out.println("ENTRY IS " + num);
        db.close();
    }
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String q = "DROP TABLE IF EXISTS " + TABLE_STUDENT_DETAIL;
        db.execSQL(q);
        this.onCreate(db);
    }
}
