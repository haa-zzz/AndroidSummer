package com.example.asus.deliveryapplication.YiqingFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.asus.deliveryapplication.MyFragment.LoginActivity;
import com.example.asus.deliveryapplication.R;

/**
 * 疫情查询Fragment
 */
public class YiqingFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private TextView tv_yq;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_yiqing,container,false);
        tv_yq=view.findViewById(R.id.tv_yq);
        //设置点击事件，点击后跳转到查询疫情的页面
        tv_yq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), yiqing.class);
                startActivityForResult(intent,10);
            }
        });
        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

}
