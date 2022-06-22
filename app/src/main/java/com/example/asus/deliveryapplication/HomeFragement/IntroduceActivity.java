package com.example.asus.deliveryapplication.HomeFragement;

import android.os.Bundle;

import com.example.asus.deliveryapplication.R;
import com.example.asus.deliveryapplication.ExpandTextView;
import com.example.asus.deliveryapplication.base.BaseBackActivity;

public class IntroduceActivity extends BaseBackActivity {
    private String introduceStr="天安门，坐落在中华人民共和国首都北京市的中心、故宫的南端，与天安门广场以及人民英雄纪念碑、毛主席纪念堂、人民大会堂、中国国家博物馆隔长安街相望，占地面积4800平方米，以杰出的建筑艺术和特殊的政治地位为世人所瞩目。\n" +
            "\n";
    private ExpandTextView expendTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        initView();
    }
    private void initView(){
        expendTextView=findViewById(R.id.tv_introduce);
        expendTextView.setText(introduceStr);
    }
}
