package com.example.asus.deliveryapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.asus.deliveryapplication.HomeFragement.HomeFragment;
import com.example.asus.deliveryapplication.YiqingFragment.YiqingFragment;
import com.example.asus.deliveryapplication.MallFragment.MallFragment;
import com.example.asus.deliveryapplication.MyFragment.MyFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BeginningActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private Fragment HomeFragment;
    private Fragment MallFragment;
    private Fragment OrderFragment;
    private Fragment MyFragment;
    public Fragment[] fragmentlist;
    private int lastFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginning);
        makeStatusBarTransparent(BeginningActivity.this);
        initFragment();
    }

    private void initFragment() {
        navigationView = findViewById(R.id.bnv_main);
        navigationView.setItemIconTintList(null);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        HomeFragment = new HomeFragment();
        MallFragment = new MallFragment();
        OrderFragment = new YiqingFragment();
        MyFragment = new MyFragment();
        fragmentlist = new Fragment[]{HomeFragment,MallFragment,OrderFragment,MyFragment};
        lastFragment = 0;
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_main,HomeFragment).show(HomeFragment).commit();
        navigationView.setSelectedItemId(R.id.navigation_home);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            resetToDefaultIcon();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (lastFragment != 0) {
                        switchFragment(lastFragment, 0);
                        lastFragment = 0;
                    }
                    item.setIcon(R.drawable.homeb);
                    return true;
                case R.id.navigation_mall:
                    if (lastFragment != 1) {
                        switchFragment(lastFragment, 1);
                        lastFragment = 1;
                    }
                    item.setIcon(R.drawable.mallb);
                    return true;
                case R.id.navigation_order:
                    if (lastFragment != 2) {
                        switchFragment(lastFragment, 2);
                        lastFragment = 2;
                    }
                    item.setIcon(R.drawable.yiqingb);
                    return true;
                case R.id.navigation_my:
                    if (lastFragment != 3) {
                        switchFragment(lastFragment, 3);
                        lastFragment =3;
                    }
                    item.setIcon(R.drawable.myb);
                    return true;
            }
            return false;
        }
    };

    private void resetToDefaultIcon() {
        navigationView.getMenu().findItem(R.id.navigation_home).setIcon(R.drawable.homeb);
        navigationView.getMenu().findItem(R.id.navigation_mall).setIcon(R.drawable.mallb);
        navigationView.getMenu().findItem(R.id.navigation_order).setIcon(R.drawable.yiqingb);
        navigationView.getMenu().findItem(R.id.navigation_my).setIcon(R.drawable.myb);
    }

    private void switchFragment(int lastFragment, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragmentlist[lastFragment]);
        if (fragmentlist[index].isAdded() == false) {
            transaction.add(R.id.fl_main, fragmentlist[index]);
        }
        transaction.show(fragmentlist[index]).commitAllowingStateLoss();
    }

    public static void makeStatusBarTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int option = window.getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            window.getDecorView().setSystemUiVisibility(option);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
