package com.aadarshamdavad.app.db_model;


import com.aadarshamdavad.app.activerecordbase.ActiveRecordBase;
import com.aadarshamdavad.app.activerecordbase.ActiveRecordException;
import com.aadarshamdavad.app.activerecordbase.CamelNotationHelper;
import com.aadarshamdavad.app.common.AppController;
import com.aadarshamdavad.app.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;


public class CityInfo extends ActiveRecordBase {

    @ModelMapper(JsonKey = "city_id")
    public int city_id = 0;
    @ModelMapper(JsonKey = "city_name")
    public String city_name = "";

    public static ArrayList<CityInfo> getAllCity() {
        try {
            List<CityInfo> list = AppController.Connection().findAll(
                    CityInfo.class);
            if (list.size() > 0) {
                return (ArrayList<CityInfo>) list;
            }

        } catch (ActiveRecordException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static ArrayList<String> getAllCityName() {
        try {
            List<String> arrCity = AppController.Connection()
                    .findDistinctColumn(CityInfo.class, true,
                            CamelNotationHelper.toSQLName("city_name"), null,
                            null);

            if (arrCity != null && arrCity.size() > 0)
                return new ArrayList<String>(arrCity);
        } catch (ActiveRecordException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void deleteCityInfo() {
        try {
            AppController.Connection().delete(CityInfo.class);
        } catch (ActiveRecordException e) {
            e.printStackTrace();
        }
    }

}
