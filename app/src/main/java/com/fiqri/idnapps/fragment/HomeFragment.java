package com.fiqri.idnapps.fragment;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.fiqri.idnapps.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class  HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    //TODO ketika view sudah dicreate
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO kenalin dan hubungin
        String url = "http://www.idn.id";
        WebView mWebVIew = view.findViewById(R.id.webView);

        //TODO set url ke webview
        mWebVIew.loadUrl(url);
        mWebVIew.getSettings().setJavaScriptEnabled(true);

        //TODO create Progress Dialog
        final ProgressDialog mDialog = ProgressDialog.show(getActivity(), "", "Loading", true);
        mWebVIew.setWebViewClient(new WebViewClient() {
            //TODO cek dulu
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Toast.makeText(getActivity(), "URL ERROR", Toast.LENGTH_SHORT).show();
            }
            //TODO start page
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mDialog.show();
            }
            //TODO finish page
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mDialog.dismiss();
            }
        });
        //TODO jangan lupa tambhan permission Internet di Manifest.xml
        //TODO <uses-permission android:name="android.permission.INTERNET"/>
    }
}