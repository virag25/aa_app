package com.aadarshamdavad.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aadarshamdavad.app.R;
import com.aadarshamdavad.app.activity.WebViewActivity;

/**
 * Created by SONY on 04-06-2016.
 */
public class AboutUsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about_us,container,false);

        Bundle bundle = getArguments();
        final String title = bundle.getString("title"); // comes from < NavigationMainActivity >

        TextView tvTrustees = (TextView) view.findViewById(R.id.tvTrustees);
        TextView tvConvener = (TextView) view.findViewById(R.id.tvConvener);
        TextView tvVolunteer = (TextView) view.findViewById(R.id.tvVolunteer);

        tvTrustees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             WebviewActivity(title,"trustee");
            }
        });
        tvConvener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             WebviewActivity(title,"convener");
            }
        });
        tvVolunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             WebviewActivity(title,"volunteer");
            }
        });

        return view;
    }

    private void WebviewActivity(String title,String param){
        Intent intent = new Intent(getActivity(),WebViewActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("param",param);
        startActivity(intent);
    }

//        String url = "http://www.aadarshamdavad.org/index.php?page=trustee";
//        String url = "http://www.aadarshamdavad.org/index.php?page=convener";
//        String url = "http://www.aadarshamdavad.org/index.php?page=volunteer";
}
