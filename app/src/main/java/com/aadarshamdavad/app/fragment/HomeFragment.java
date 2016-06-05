package com.aadarshamdavad.app.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.aadarshamdavad.app.R;

public class HomeFragment extends Fragment {
    WebView webview;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        webview = (WebView) view.findViewById(R.id.webview);

        Bundle bundle = getArguments();
        String title = bundle.getString("title"); // comes from < NavigationMainActivity >

        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);

        assert title != null;
        if (!title.equalsIgnoreCase("Home"))
            tvTitle.setText(title);
        else
            tvTitle.setText(getResources().getString(R.string.home));

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview.loadUrl("file:///android_asset/ttt.html");
        if (Build.VERSION.SDK_INT >= 19) {
            webview.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        return view;
    }
}
