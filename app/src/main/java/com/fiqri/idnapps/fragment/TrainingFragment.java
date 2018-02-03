package com.fiqri.idnapps.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fiqri.idnapps.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainingFragment extends Fragment {


    public TrainingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_training, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO kenalin dan hubungin
        String url = "http://idn.id/training/";
        WebView mwebView = view.findViewById(R.id.webView);

        //TODO set url ke web view
        mwebView.loadUrl(url);
        mwebView.getSettings().setJavaScriptEnabled(true);
        mwebView.setWebViewClient(new WebViewClient());
        //TODO jangan lupa tambahkan permission internet di manifest
    }

}
