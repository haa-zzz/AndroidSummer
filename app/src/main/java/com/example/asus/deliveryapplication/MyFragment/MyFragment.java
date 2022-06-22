package com.example.asus.deliveryapplication.MyFragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import com.example.asus.deliveryapplication.R;

import static android.app.Activity.RESULT_OK;

public class MyFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private Button bt_out;
    private TextView te_login;
    private View view;
    private MySQLiteOpenHelper openHelper;
    private SQLiteDatabase writableDatabase;
    private RadioGroup radioGroup;
    private RadioButton radioButtonShow, radioButtonClock, radioButtonTime, radioButtonMy;
    private ViewPager viewPager;
    private MyPagerAdapter myPagerAdapter;

    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_my,container,false);
        bt_out = view.findViewById(R.id.bt_1);
        te_login = view.findViewById(R.id.tv_1);
        te_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                startActivityForResult(intent,10);
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.lcg.update");
        int mCurrentOrientation = getResources().getConfiguration().orientation;
        if (mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            myPagerAdapter = new MyPagerAdapter(getFragmentManager());
            bindViews();
            radioButtonShow.setChecked(true);
        }
        return view;
    }

    private void bindViews() {
        radioGroup = view.findViewById(R.id.rg_tab_bar);
        radioGroup.setOnCheckedChangeListener(this);
        radioButtonShow = view.findViewById(R.id.show);
        radioButtonClock = view.findViewById(R.id.clock);
        radioButtonTime = view.findViewById(R.id.time);
        radioButtonMy = view.findViewById(R.id.my);
        viewPager = view.findViewById(R.id.vpager);
        viewPager.setAdapter(myPagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK){
            if (requestCode == 10){
                Bundle bundle = data.getExtras();
                String name = (String) bundle.get("name");
                te_login.setText(name);
                openHelper = new MySQLiteOpenHelper(view.getContext(), "user.db", null, 1);
                bt_out.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        writableDatabase = openHelper.getWritableDatabase();
                        writableDatabase.delete("user","name=?",new String[]{name});
                        Intent intent = new Intent(view.getContext(), LoginActivity.class);
                        te_login.setText("点击登录");
                        bt_out.setText("登录");
                        bt_out.setBackgroundColor(Color.parseColor("#3787c9"));
                        startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.show:
                viewPager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.clock:
                viewPager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.time:
                viewPager.setCurrentItem(PAGE_THREE);
                break;
            case R.id.my:
                viewPager.setCurrentItem(PAGE_FOUR);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, @Px int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 2) {
            switch (viewPager.getCurrentItem()) {
                case PAGE_ONE:
                    radioButtonShow.setChecked(true);
                    break;
                case PAGE_TWO:
                    radioButtonClock.setChecked(true);
                    break;
                case PAGE_THREE:
                    radioButtonTime.setChecked(true);
                    break;
                case PAGE_FOUR:
                    radioButtonMy.setChecked(true);
                    break;
            }
        }
    }



}
