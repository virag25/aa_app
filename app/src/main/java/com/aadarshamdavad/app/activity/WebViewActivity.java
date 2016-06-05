package com.aadarshamdavad.app.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.aadarshamdavad.app.R;


public class WebViewActivity extends AppCompatActivity {

    private WebView webInfo;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String param = intent.getStringExtra("param");

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null){
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }else {
            actionBar.setTitle("Info");
        }

        String url = "http://www.aadarshamdavad.org/index.php?page="+param;
//        String url = "http://www.aadarshamdavad.org/index.php?page=trustee";
//        String url = "http://www.aadarshamdavad.org/index.php?page=convener";
//        String url = "http://www.aadarshamdavad.org/index.php?page=volunteer";
//        String url = "http://www.aadarshamdavad.org/index.php?page=contact_us";
//        String url = "http://www.aadarshamdavad.org/index.php?page=our_center";
        webInfo = (WebView) findViewById(R.id.webInfo);
        startWebView(url);
//        WebSettings webSettings = webInfo.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        webSettings.setDomStorageEnabled(true);
//        webInfo.loadUrl(url);
    }

    private void startWebView(String url) {

        WebSettings settings = webInfo.getSettings();

        settings.setJavaScriptEnabled(true);
        webInfo.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        webInfo.getSettings().setBuiltInZoomControls(true);
        webInfo.getSettings().setUseWideViewPort(true);
        webInfo.getSettings().setLoadWithOverviewMode(true);

        progressDialog = new ProgressDialog(WebViewActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        webInfo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(WebViewActivity.this, "Error:" + description, Toast.LENGTH_SHORT).show();

            }
        });
        webInfo.loadUrl(url);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
