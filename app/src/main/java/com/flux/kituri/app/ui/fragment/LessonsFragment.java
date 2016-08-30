package com.flux.kituri.app.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flux.kituri.R;
import com.flux.kituri.app.flux.Store;
import com.flux.kituri.app.utils.UserUtils;

import java.util.ArrayList;

public class LessonsFragment extends BaseFragment implements View.OnClickListener {

    static public final String TAG = "LessonsFragment";

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
        View fragmentLayout = inflater.inflate(R.layout.fr_lessons, container, false);
        fragmentLayout.findViewById(R.id.btn_clear_user).setOnClickListener(this);
                //clearUserData()
        return fragmentLayout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_clear_user:
                UserUtils.clearUserData();
                break;
        }
    }
}
