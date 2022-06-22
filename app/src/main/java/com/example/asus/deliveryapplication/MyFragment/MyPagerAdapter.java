package com.example.asus.deliveryapplication.MyFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 4;
    private My_Fragment myOrderFragment1, myOrderFragment2, myOrderFragment3, myOrderFragment4;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
        myOrderFragment1 = new My_Fragment();
        myOrderFragment2 = new My_Fragment();
        myOrderFragment3 = new My_Fragment();
        myOrderFragment4 = new My_Fragment();
        myOrderFragment1.content = "1";
        myOrderFragment2.content = "2";
        myOrderFragment3.content = "3";
        myOrderFragment4.content = "4";
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MyFragment.PAGE_ONE:
                fragment = myOrderFragment1;
                break;
            case MyFragment.PAGE_TWO:
                fragment = myOrderFragment2;
                break;
            case MyFragment.PAGE_THREE:
                fragment = myOrderFragment3;
                break;
            case MyFragment.PAGE_FOUR:
                fragment = myOrderFragment4;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}

