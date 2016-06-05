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
public class ActivitydbInfo extends ActiveRecordBase {

    @ModelMapper(JsonKey = "id")
    public int activity_id = 0;
    @ModelMapper(JsonKey = "user_id")
    public int user_id = 0;

    @ModelMapper(JsonKey = "categoryId")
    public int categoryId = 0;


    @ModelMapper(JsonKey = "activity_title")
    public String activity_title = "";

    @ModelMapper(JsonKey = "activity_desc")
    public String activity_desc = "";

    @ModelMapper(JsonKey = "photo")
    public String photo = "";
    @ModelMapper(JsonKey = "activity_time")
    public String activity_time = "";
    @ModelMapper(JsonKey = "venue")
    public String venue = "";
    @ModelMapper(JsonKey = "trainer")
    public String trainer = "";
    @ModelMapper(JsonKey = "no_of_participants")
    public String no_of_participants = "";
    @ModelMapper(JsonKey = "no_of_beneficiary")
    public String no_of_beneficiary = "";
    @ModelMapper(JsonKey = "activity_status")
    public String activity_status = "";
    @ModelMapper(JsonKey = "status")
    public String status = "";

    @ModelMapper(JsonKey = "crdate")
    public String crdate = "";
    @ModelMapper(JsonKey = "photo_name")
    public String photo_name = "";
    @ModelMapper(JsonKey = "activity_time_form")
    public String activity_time_form = "";


    public static ArrayList<ActivitydbInfo> getAllActivity() {
        try {
            List<ActivitydbInfo> list = AppController.Connection().findAll(
                    ActivitydbInfo.class);
            if (list.size() > 0) {
                return (ArrayList<ActivitydbInfo>) list;
            }

        } catch (ActiveRecordException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static ArrayList<String> getAllActivityName() {
        try {
            List<String> arrCity = AppController.Connection()
                    .findDistinctColumn(ActivitydbInfo.class, true,
                            CamelNotationHelper.toSQLName("city_name"), null,
                            null);

            if (arrCity != null && arrCity.size() > 0)
                return new ArrayList<String>(arrCity);
        } catch (ActiveRecordException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void deleteActivity_dbInfo() {
        try {
            AppController.Connection().delete(ActivitydbInfo.class);
        } catch (ActiveRecordException e) {
            e.printStackTrace();
        }
    }

    public static ActivitydbInfo getDescriptionByOrderId(String activityTitle) {
        try {
            List<ActivitydbInfo> lst = AppController.Connection().find(
                    ActivitydbInfo.class,
                    CamelNotationHelper.toSQLName("activityTitle") + "=?",
                    new String[]{"" + activityTitle});
            if (lst != null && lst.size() > 0) {
                return lst.get(0);
            }
        } catch (ActiveRecordException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static ArrayList<ActivitydbInfo> getDescriptionByOrderId(String activityTitle) {
//        try {
//            List<ActivitydbInfo> lst = MyApplication.Connection().find(
//                    ActivitydbInfo.class,
//                    CamelNotationHelper.toSQLName("activityTitle") + "=?",
//                    new String[]{String.valueOf(activityTitle)});
//            if (lst != null && lst.size() > 0) {
//                return (ArrayList<ActivitydbInfo>) lst;
//            }
//        } catch (ActiveRecordException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}
