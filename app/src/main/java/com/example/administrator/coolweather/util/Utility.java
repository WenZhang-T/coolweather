package com.example.administrator.coolweather.util;

import android.text.TextUtils;
import android.util.Log;

import com.example.administrator.coolweather.db.City;
import com.example.administrator.coolweather.db.County;
import com.example.administrator.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/5/7.
 */

public class Utility {

    public static  boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allProvinces = new JSONArray(response);
                for(int i=0; i<allProvinces.length();i++){
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Log.d("Bag","the JSONObject is" + provinceObject);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    Log.d("Bag","the province name is"+(provinceObject.getString("name")));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    Log.d("Bag","the province name is"+(provinceObject.getInt("id")));
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return  false;
    }

    public static boolean handleCityResponse(String response,int provindeId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCities = new JSONArray(response);
                for(int i=0; i<allCities.length();i++){
                    JSONObject citiesObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(citiesObject.getString("name"));
                    city.setCityCode(citiesObject.getInt("id"));
                    city.setProvinceId(provindeId);
                    city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCountyResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCounty = new JSONArray(response);
                for(int i=0;i<allCounty.length();i++){
                    JSONObject countyObject = allCounty.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setCountyCode(countyObject.getInt("id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

}
