package com.example.asus.deliveryapplication.HomeFragement;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.deliveryapplication.Bmob.Jingdian;
import com.example.asus.deliveryapplication.R;
import com.example.asus.deliveryapplication.ExpandTextView;
import com.example.asus.deliveryapplication.base.BaseBackActivity;

public class TopSpotIntroduceActivity extends BaseBackActivity {
    private ExpandTextView expendTextView;
    private TextView tv_address,tv_opentime,tv_phone,tv_ticket;
    private ImageView iv_top_spot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        initView();
    }
    private void initView(){
        expendTextView = findViewById(R.id.tv_introduce);
        tv_ticket = findViewById(R.id.tv_ticket);
        tv_opentime = findViewById(R.id.tv_opentime);
        tv_address = findViewById(R.id.tv_address);
        tv_phone = findViewById(R.id.tv_phone);
        iv_top_spot = findViewById(R.id.iv_top_spot);
        loadData();
    }

    private void loadData() {
        Intent intent = getIntent();
        Jingdian jingdian = (Jingdian) intent.getSerializableExtra("topSpotData");//获取list方式
        expendTextView.setText(jingdian.getSummary());
        tv_address.setText(jingdian.getAddress());
        tv_opentime.setText(jingdian.getOpentime());
        tv_phone.setText(jingdian.getPhone());
        tv_ticket.setText(jingdian.getTicket());
        getSupportActionBar().setTitle(jingdian.getCity()+" - "+ jingdian.getDes());
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.img_glide_load_ing)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(this)
                .load(jingdian.getImg())
                .apply(options)
                .into(iv_top_spot);
    }
}
