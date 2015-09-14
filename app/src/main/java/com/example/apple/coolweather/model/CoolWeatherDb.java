package com.example.apple.coolweather.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.apple.coolweather.db.CoolWeatherOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2015/9/14.
 */
public class CoolWeatherDb {
    /**
     * ���ݿ���
     */
    public static final String DB_NAME = "cool_weather";

    /**
     * ���ݿ�汾
     */
    public static final int VERSION = 1;

    private static CoolWeatherDb coolWeatherDb;

    private SQLiteDatabase db;

    /**
     * �����췽����ʼ��
     */
    private CoolWeatherDb(Context context) {
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context,
                DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * ��ȡCoolWeatherDB��ʵ��
     * synchronized������ǰֻ��һ���߳���ʹ���������
     */
    public synchronized static CoolWeatherDb getInstance(Context context) {
        if (coolWeatherDb == null) {
            coolWeatherDb = new CoolWeatherDb(context);
        }
        return coolWeatherDb;
    }

    /**
     * ��Provinceʵ���洢�����ݿ�
     */
    public void saveProvince(Province province) {
        if(province != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("province_name", province.getProvinceName());
            contentValues.put("province_code", province.getProvinceCode());
            db.insert("Province", null , contentValues);
        }
    }

    /**
     * �����ݿ��ж�ȡȫ����ʡ�ݵ���Ϣ
     */
    public List<Province> loadProvince() {
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("Province", null, null, null, null, null, null);
        if(cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("provinceCode")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("provinceName")));
                list.add(province);
            }while(cursor.moveToNext());//ֱ����굽���һ��
        }
        return list;
    }

    /**
     * ��City�浽���ݿ�
     */
    public void saveCity(City city) {
        if(city != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("city_name", city.getCityName());
            contentValues.put("city_code", city.getCityCode());
            contentValues.put("province_id", city.getProvinceId());
            db.insert("City", null, contentValues);
        }
    }

    /**
     * �����ݿ��ж�ȡĳʡ���еĳ�����Ϣ
     */

    public List<City> loadCities(int provinceId) {
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("City", null, "province_id = ?",
                new String[] {String.valueOf(provinceId)}, null, null, null);
        if(cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("code_name")));
                city.setProvinceId(provinceId);
                list.add(city);
            } while(cursor.moveToNext());
        }
        return list;
    }

    /**
     * �����ݴ洢��County���ݿ���
     */
     public void saveCounty(County county) {
         if(county != null) {
             ContentValues contentValues = new ContentValues();
             contentValues.put("county_code", county.getCountyCode());
             contentValues.put("county_name", county.getCountName());
             contentValues.put("city_id", county.getCityId());
             db.insert("County", null, contentValues);
         }
     }

    /**
     * �����ݿ��ж�ȡĳ�����������ص���Ϣ
     */
    public List<County> loadCounty(int cityId) {
        List<County> list = new ArrayList<County>();
        Cursor cursor = db.query("County", null, "city_id = ?",
                new String[] {String.valueOf(cityId)}, null, null, null);
        if(cursor.moveToFirst()) {
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCityId(cursor.getInt(cursor.getColumnIndex("city_id")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("city_code")));
                county.setCountName(cursor.getString(cursor.getColumnIndex("city_name")));
                list.add(county);
            } while(cursor.moveToNext());
            return list;
        }

    }
}
