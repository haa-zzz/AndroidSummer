package com.example.asus.deliveryapplication.YiqingFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asus.deliveryapplication.R;

import java.util.List;

public class NewsAdapter extends BaseAdapter {

    private Context context;
    private List<High_list> dataList;
    public NewsAdapter(Context context, List<High_list> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.news_item, null);
        }
        TextView tv_city = (TextView) convertView.findViewById(R.id.tv_city);
        TextView tv_province = (TextView) convertView.findViewById(R.id.tv_province);
        TextView tv_county = (TextView) convertView.findViewById(R.id.tv_county);
        High_list data = dataList.get(position);
        tv_city.setText(data.getCity());
        tv_province.setText(data.getProvince());
        tv_county.setText(data.getCounty());
        return convertView;
    }
}

