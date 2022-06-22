package com.example.asus.deliveryapplication.HomeFragement;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.asus.deliveryapplication.Bmob.BmobManager;
import com.example.asus.deliveryapplication.Bmob.Jingdian;
import com.example.asus.deliveryapplication.R;
import com.example.asus.deliveryapplication.base.BaseBackActivity;
import com.example.asus.deliveryapplication.utils.CommonUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SearchResultActivity extends BaseBackActivity {
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private List<Jingdian> data=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.mRecyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        homeAdapter=new HomeAdapter(this,data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(Jingdian jingdian, int position) {
                Intent intent = new Intent(SearchResultActivity.this, TopSpotIntroduceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("topSpotData", (Serializable) jingdian);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        String cityName= intent.getStringExtra("city");
        getSupportActionBar().setTitle(cityName);
        BmobManager.getInstance().queryChinaTopSpot("city",cityName,new FindListener<Jingdian>(){
            @Override
            public void done(List<Jingdian> list, BmobException e) {
                if(CommonUtils.isEmpty(data)) {
                    data.clear();
                }
                data.addAll(list);
                homeAdapter.notifyDataSetChanged();
            }
        });
    }
}
