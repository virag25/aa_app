package com.aadarshamdavad.app.db_model;

import com.aadarshamdavad.app.activerecordbase.ActiveRecordBase;
import com.aadarshamdavad.app.activerecordbase.ActiveRecordException;
import com.aadarshamdavad.app.common.AppController;
import com.aadarshamdavad.app.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Virag kuvadia on 05-06-2016.
 */
public class PhotoGallarydb extends ActiveRecordBase {


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


    public static ArrayList<PhotoGallarydb> getAllGallary() {
        try {
            List<PhotoGallarydb> list = AppController.Connection().findAll(
                    PhotoGallarydb.class);
            if (list.size() > 0) {
                return (ArrayList<PhotoGallarydb>) list;
            }

        } catch (ActiveRecordException e) {
            e.printStackTrace();
        }

        return null;

    }


    public static void deletePhotoGallary_db() {
        try {
            AppController.Connection().delete(PhotoGallarydb.class);
        } catch (ActiveRecordException e) {
            e.printStackTrace();
        }
    }

//    public static PhotoGallarydb getDescriptionByOrderId(String activityTitle) {
//        try {
//            List<PhotoGallarydb> lst = AppController.Connection().find(
//                    PhotoGallarydb.class,
//                    CamelNotationHelper.toSQLName("activityTitle") + "=?",
//                    new String[]{"" + activityTitle});
//            if (lst != null && lst.size() > 0) {
//                return lst.get(0);
//            }
//        } catch (ActiveRecordException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    public static ArrayList<PhotoGallarydb> getDescriptionByOrderId(String activityTitle) {
//        try {
//            List<PhotoGallarydb> lst = AppController.Connection().find(
//                    PhotoGallarydb.class,
//                    CamelNotationHelper.toSQLName("activityTitle") + "=?",
//                    new String[]{String.valueOf(activityTitle)});
//            if (lst != null && lst.size() > 0) {
//                return (ArrayList<PhotoGallarydb>) lst;
//            }
//        } catch (ActiveRecordException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


}
