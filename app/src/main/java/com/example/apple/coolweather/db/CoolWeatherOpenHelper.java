package com.example.apple.coolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by apple on 2015/9/14.
 */
public class CoolWeatherOpenHelper extends SQLiteOpenHelper {
    /**
     *Province�������
     */
    public static final String CREATE_PROVINCE = "create table Province ("
            + "id interger primary key autoincrement, "
            + "province_name text, "
            + "province_code text)";

    /**
     * City�������
     */
    public static final String CREATE_CITY = "create table City ("
            + "id integer primary key autoincrement"
            + "city_name text, "
            + "city_code text, "
            + "province_id integer)";

    /**
     * County�������
     */
    public static final String CREATE_COUNTY = "create table County ("
            + "id integer primary key autoincrement"
            + "county_name text, "
            + "county_id text, "
            + "city_id integer)";

    public CoolWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PROVINCE);//����province��
        sqLiteDatabase.execSQL(CREATE_CITY);//����city��
        sqLiteDatabase.execSQL(CREATE_COUNTY);//����county��

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
