package com.example.asus.deliveryapplication.MallFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * 看世界页面 pageView对于的Adapter。用它来协助pageView完成页面的加载，切换
 */
public class mySharePagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 5;
    //精选
    private GoodTuiFragment myFragment1;
    //跟团游
    private GoodGroupFragment myFragment2;
    //门票
    private GoodTicketFragment myFragment3;
    //出行
    private GoodTrafficFragment myFragment4;
    //住房
    private GoodHotelFragment myFragment5;

    public mySharePagerAdapter(FragmentManager fm) {
        super(fm);
        myFragment1 = new GoodTuiFragment();
        myFragment2 = new GoodGroupFragment();
        myFragment3 = new GoodTicketFragment();
        myFragment4 = new GoodTrafficFragment();
        myFragment5 = new GoodHotelFragment();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MallFragment.PAGE_ONE:
                fragment = myFragment1;
                break;
            case MallFragment.PAGE_TWO:
                fragment = myFragment2;
                break;
            case MallFragment.PAGE_THREE:
                fragment = myFragment3;
                break;
            case MallFragment.PAGE_FOUR:
                fragment = myFragment4;
                break;
            case MallFragment.PAGE_FIVE:
                fragment = myFragment5;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
