package com.aadarshamdavad.app.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.aadarshamdavad.app.BuildConfig;
import com.aadarshamdavad.app.R;
import com.aadarshamdavad.app.activerecordbase.ActiveRecordException;
import com.aadarshamdavad.app.adapter.PhotoGalleryListAdapter;
import com.aadarshamdavad.app.db_model.Gallarydb;
import com.aadarshamdavad.app.db_model.PhotoGallarydb;
import com.aadarshamdavad.app.modelmapper.ModelMapHelper;
import com.aadarshamdavad.app.models.PhotoGalleryInfo;
import com.aadarshamdavad.app.models.PhotoInfo;
import com.aadarshamdavad.app.webservice.RestApi;
import com.aadarshamdavad.app.webservice.Webservices_Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PhotoGalleryFragment extends Fragment {

    private static final String TAG = "ActivitiesFragment";
    Context mContext;
    Gson gson;

    private RecyclerView rcvPhotoGallery;
    ArrayList<PhotoInfo> photoInfoArrayList = new ArrayList<>();
    List<PhotoGalleryInfo> photoGalleryInfoArrayList = new ArrayList<>();
    private PhotoGalleryListAdapter photoGalleryListAdapter;

    ArrayList<Gallarydb> arr_galary = new ArrayList<>();
    ArrayList<PhotoGallarydb> arr_photo = new ArrayList<>();
    PhotoGallarydb info;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_photo_gallery, container, false);
        mContext = getActivity();

        rcvPhotoGallery = (RecyclerView) view.findViewById(R.id.rcvPhotoGallery);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        assert rcvPhotoGallery != null;
        rcvPhotoGallery.setLayoutManager(mLayoutManager);
        rcvPhotoGallery.setItemAnimator(new DefaultItemAnimator());
        gson = new Gson();

        arr_photo = new ArrayList<>();
        arr_photo = PhotoGallarydb.getAllGallary();

        if (arr_photo == null || arr_photo.size() == 0) {
            getPhotoList();
        } else {
            photoGalleryListAdapter = new PhotoGalleryListAdapter(mContext, arr_photo);
            rcvPhotoGallery.setAdapter(photoGalleryListAdapter);
        }

        return view;
    }


    private void getPhotoList() {
        // Tag used to cancel the request
        String tag_string_req = "string_req";

        RestApi restApi = new RestApi();
        String url = restApi.getPhoto();
        if (BuildConfig.DEBUG)
            Log.e(TAG, "URL>> " + url);

        Webservices_Volley webservices_volley = new Webservices_Volley();

        webservices_volley.callWS(getActivity(), url, new Webservices_Volley.WebServicesCallback() {
            @Override
            public void onSuccess(String TAG, String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    // clear data for second time calling the webservice
                    photoInfoArrayList.clear();
                    arr_galary = new ArrayList<Gallarydb>();
                    arr_photo = new ArrayList<PhotoGallarydb>();
                    Gallarydb.deleteGallary_db();
                    PhotoGallarydb.deletePhotoGallary_db();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject mainJsonObject = jsonArray.getJSONObject(i);

                        if (mainJsonObject != null) {
                            // JSONObject catobj = data.getJSONObject("0");
                            ModelMapHelper<PhotoGallarydb> mapper = new ModelMapHelper<PhotoGallarydb>();
                            info = mapper.getObject(PhotoGallarydb.class,
                                    mainJsonObject);
                            if (info != null) {
                                info.save();
                                arr_photo.add(info);
                            }

                            JSONArray jarr = mainJsonObject.optJSONArray("gallery");
                            if (jarr != null) {
                                for (int j = 0; j < jarr.length(); j++) {
                                    JSONObject galary_obj = jarr.getJSONObject(j);
                                    ModelMapHelper<Gallarydb> gal_mapper = new ModelMapHelper<Gallarydb>();
                                    Gallarydb gal_info = gal_mapper.getObject(Gallarydb.class,
                                            galary_obj);
                                    if (gal_info != null) {
                                        gal_info.save();
                                    }
                                }
                            }
                        }


//                        PhotoGalleryInfo photoGalleryInfo = gson.fromJson(mainJsonObject.toString(), PhotoGalleryInfo.class);
//                        photoGalleryInfoArrayList.add(photoGalleryInfo);
                    }

                    // Set Data in adapter


                    photoGalleryListAdapter = new PhotoGalleryListAdapter(mContext, arr_photo);
                    rcvPhotoGallery.setAdapter(photoGalleryListAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ActiveRecordException e) {
                    e.printStackTrace();
                }
            }
        }, tag_string_req);
    }

//    private void getPhotoList2() {
//        // Tag used to cancel the request
//        String tag_string_req = "string_req";
//
//        RestApi restApi = new RestApi();
//        String url = restApi.getPhoto();
//        if (BuildConfig.DEBUG)
//            Log.e(TAG, "URL>> " + url);
//
//        Webservices_Volley webservices_volley = new Webservices_Volley();
//
//        webservices_volley.callWS(getActivity(), url, new Webservices_Volley.WebServicesCallback() {
//            @Override
//            public void onSuccess(String TAG, String response) {
//                try {
//                    JSONArray jsonArray = new JSONArray(response);
//                    if (jsonArray != null) {
//                        // clear data for second time calling the webservice
//                        photoInfoArrayList.clear();
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            JSONObject mainJsonObject = jsonArray.getJSONObject(i);
//
//
//                            JSONArray galleryJsonArray = mainJsonObject.getJSONArray("gallery");
//                            galleryInfoArrayList.clear();
//                            if (galleryJsonArray != null) {
//                                for (int j = 0; j < galleryJsonArray.length(); j++) {
//                                    JSONObject galleryJsonObject = galleryJsonArray.getJSONObject(j);
//                                    galleryInfoArrayList.add(new GalleryInfo(galleryJsonObject));
//                                }
//                            }
//                            photoInfoArrayList.add(new PhotoInfo(mainJsonObject, galleryInfoArrayList));
//                        }
//                        // Set Data in adapter
//                        photoGalleryListAdapter = new PhotoGalleryListAdapter(mContext, photoInfoArrayList);
//                        rcvPhotoGallery.setAdapter(photoGalleryListAdapter);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, tag_string_req);
//    }


}