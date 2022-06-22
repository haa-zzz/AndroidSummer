package com.example.asus.deliveryapplication.MallFragment;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.asus.deliveryapplication.R;
/**
 * 看世界
 *
 * 主要就是使用了一个ViewPager，包含精选，跟团游，门票。。。。
 */
public class MallFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private RadioGroup radioGroup;
    private RadioButton radioButtonShow, radioButtonClock,radioButtonqita,radioButtonTime, radioButtonMy;
    private ViewPager viewPager;
    private View view;
    private com.example.asus.deliveryapplication.MallFragment.mySharePagerAdapter mySharePagerAdapter;
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    public static final int PAGE_FIVE = 4;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_mall,container,false);
        int mCurrentOrientation = getResources().getConfiguration().orientation;
        if (mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            mySharePagerAdapter = new mySharePagerAdapter(getFragmentManager());
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
        radioButtonqita = view.findViewById(R.id.qita);
        radioButtonMy = view.findViewById(R.id.my);
        viewPager =view.findViewById(R.id.vpager_share);
        viewPager.setAdapter(mySharePagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);
    }

    //精选，跟团游，门票。。。。这些点击之后，更新下面的ViewPager页面
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
            case R.id.qita:
                viewPager.setCurrentItem(PAGE_FIVE);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }
    //滑动ViewPager的监听，滑动之后，上面的精选，跟团游，门票。。。。选中状态要随之改变
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
                case PAGE_FIVE:
                    radioButtonqita.setChecked(true);
                    break;
            }
        }
    }
}
