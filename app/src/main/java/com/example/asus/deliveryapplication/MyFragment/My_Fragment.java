package com.example.asus.deliveryapplication.MyFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.deliveryapplication.R;

import androidx.fragment.app.Fragment;

public class My_Fragment extends Fragment {
    public String content;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.config_show,container,false);
        return view;
    }
}
