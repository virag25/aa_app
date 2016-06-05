package com.aadarshamdavad.app.common;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.aadarshamdavad.app.activerecordbase.ActiveRecordBase;
import com.aadarshamdavad.app.activerecordbase.ActiveRecordException;
import com.aadarshamdavad.app.activerecordbase.Database;
import com.aadarshamdavad.app.activerecordbase.DatabaseBuilder;
import com.aadarshamdavad.app.activity.Const;
import com.aadarshamdavad.app.db_model.ActivitydbInfo;
import com.aadarshamdavad.app.db_model.Gallarydb;
import com.aadarshamdavad.app.db_model.PhotoGallarydb;

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();
    private RequestQueue mRequestQueue;

    private static AppController mInstance;

    private static AppController _intance = null;
    private static ActiveRecordBase mDatabase;


    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseBuilder builder = new DatabaseBuilder(Const.DATABASE_NAME);
        builder.addClass(ActivitydbInfo.class);
        builder.addClass(Gallarydb.class);
        builder.addClass(PhotoGallarydb.class);
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

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public AppController() {
        _intance = this;
    }


    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }


    public static ActiveRecordBase Connection() {
        return mDatabase;
    }

    public static Context getContext() {
        return _intance;
    }


    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
