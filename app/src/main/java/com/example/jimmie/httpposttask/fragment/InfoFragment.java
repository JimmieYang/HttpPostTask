package com.example.jimmie.httpposttask.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jimmie.httpposttask.R;
import com.example.jimmie.httpposttask.utils.JsonUtil;
import com.example.jimmie.httpposttask.utils.PreferencesUtil;

/**
 * Created by jimmie on 2016/1/25.
 */
public class InfoFragment extends Fragment {

    // Lifecycle methods ///////////////////////////////////////////////////////////////////////////////////////////
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_fragment, container, false);
        init(view);
        return view;
    }

    // Initialization ///////////////////////////////////////////////////////////////////////////////////////////////
    private void init(View view) {
        TextView tv = (TextView) view.findViewById(R.id.info_tv);
        tv.setText(JsonUtil.getJsonUserInfoString(PreferencesUtil.getJsonUserInfo(getActivity(), PreferencesUtil.USER_INFO)));
    }

}
