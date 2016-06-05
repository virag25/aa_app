package com.aadarshamdavad.app.db_model;

import com.aadarshamdavad.app.activerecordbase.ActiveRecordBase;
import com.aadarshamdavad.app.activerecordbase.ActiveRecordException;
import com.aadarshamdavad.app.activerecordbase.CamelNotationHelper;
import com.aadarshamdavad.app.common.AppController;
import com.aadarshamdavad.app.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Virag kuvadia on 05-06-2016.
 */
public class Gallarydb extends ActiveRecordBase {

    @ModelMapper(JsonKey = "id")
    public int gallary_id = 0;
    @ModelMapper(JsonKey = "name")
    public String name = "";
    @ModelMapper(JsonKey = "detail")
    public String detail = "";
    @ModelMapper(JsonKey = "detail1")
    public String detail_one = "";
    @ModelMapper(JsonKey = "detail2")
    public String detail_two = "";
    @ModelMapper(JsonKey = "type")
    public String gal_type = "";
    @ModelMapper(JsonKey = "status")
    public String gal_status = "";
    @ModelMapper(JsonKey = "date")
    public String gal_date = "";
    @ModelMapper(JsonKey = "parentid")
    public int parentid = 0;
    @ModelMapper(JsonKey = "path")
    public String path = "";


    public static ArrayList<Gallarydb> getAllGallary() {
        try {
            List<Gallarydb> list = AppController.Connection().findAll(
                    Gallarydb.class);
            if (list.size() > 0) {
                return (ArrayList<Gallarydb>) list;
            }

        } catch (ActiveRecordException e) {
            e.printStackTrace();
        }

        return null;

    }


    public static void deleteGallary_db() {
        try {
            AppController.Connection().delete(Gallarydb.class);
        } catch (ActiveRecordException e) {
            e.printStackTrace();
        }
    }

    public static Gallarydb getGallaryDataById(int gallary_id) {
        try {
            List<Gallarydb> lst = AppController.Connection().find(
                    Gallarydb.class,
                    CamelNotationHelper.toSQLName("gallary_id") + "=?",
                    new String[]{"" + gallary_id});
            if (lst != null && lst.size() > 0) {
                return lst.get(0);
            }
        } catch (ActiveRecordException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Gallarydb> getPhotoesById(int parentid) {
        try {
            List<Gallarydb> lst = AppController.Connection().find(
                    Gallarydb.class,
                    CamelNotationHelper.toSQLName("parentid") + "=?",
                    new String[]{String.valueOf(parentid)});
            if (lst != null && lst.size() > 0) {
                return (ArrayList<Gallarydb>) lst;
            }
        } catch (ActiveRecordException e) {
            e.printStackTrace();
        }
        return null;
    }


}
