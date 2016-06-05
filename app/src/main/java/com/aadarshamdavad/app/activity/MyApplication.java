package com.aadarshamdavad.app.activity;

import android.app.Application;
import android.content.Context;

import com.aadarshamdavad.app.activerecordbase.ActiveRecordBase;
import com.aadarshamdavad.app.activerecordbase.ActiveRecordException;
import com.aadarshamdavad.app.activerecordbase.Database;
import com.aadarshamdavad.app.activerecordbase.DatabaseBuilder;
import com.aadarshamdavad.app.common.My_Utils;
import com.aadarshamdavad.app.db_model.CityInfo;


/**
 * Created by Virag kuvadia on 18-05-2016.
 */
public class MyApplication extends Application {
    private static MyApplication _intance = null;
    private static ActiveRecordBase mDatabase;


    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseBuilder builder = new DatabaseBuilder(Const.DATABASE_NAME);
        builder.addClass(CityInfo.class);
        Database.setBuilder(builder);
        try {
            mDatabase = ActiveRecordBase.open(this,
                    Const.DATABASE_NAME, Const.DATABASE_VERSION);
        } catch (ActiveRecordException e) {
            e.printStackTrace();
        } catch (Exception e) {
            My_Utils.LogException(e);
        }

    }


    public MyApplication() {
        _intance = this;
    }

    public static ActiveRecordBase Connection() {
        return mDatabase;
    }

    public static Context getContext() {
        return _intance;
    }

}
