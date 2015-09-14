package com.example.apple.coolweather.model;

/**
 * Created by apple on 2015/9/14.
 */
public class County {
    private int id;
    private String countyCode;
    private String countName;
    private int cityId;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCountName(String countName) {
        this.countName = countName;
    }

    public String getCountName() {
        return countName;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getCityId() {
        return cityId;
    }
}
