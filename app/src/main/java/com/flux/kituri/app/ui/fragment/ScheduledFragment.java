package com.flux.kituri.app.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flux.kituri.R;
import com.flux.kituri.app.flux.Store;

import java.util.ArrayList;


public class ScheduledFragment extends BaseFragment {

    private View fragmentLayout;

    static public final String TAG = "ScheduledFragment";

    @Override
    public ArrayList<Store> initFlux() {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentLayout = inflater.inflate(R.layout.fr_scheduled, container, false);
        return fragmentLayout;
    }

}
