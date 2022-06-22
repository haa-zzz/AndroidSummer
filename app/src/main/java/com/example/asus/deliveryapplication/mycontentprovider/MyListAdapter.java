package com.example.asus.deliveryapplication.mycontentprovider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asus.deliveryapplication.R;

import java.util.List;

public class MyListAdapter extends BaseAdapter {
    private Context mContext;
    private List<person> personLists;

    public MyListAdapter(Context mContext, List<person> personLists) {
        this.mContext = mContext;
        this.personLists = personLists;
    }

    @Override
    public int getCount() {
        return personLists.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    static class ViewHolder{
        private TextView te_id,te_name,te_phonenum;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=new ViewHolder();
        if(view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.contact_item,null);
            viewHolder.te_id= (TextView) view.findViewById(R.id.te_id);
            viewHolder.te_name= (TextView) view.findViewById(R.id.te_name);
            viewHolder.te_phonenum= (TextView) view.findViewById(R.id.te_phonenum);
            view.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder) view.getTag();
        }
        String idd= String.valueOf(personLists.get(i).getId());
        viewHolder.te_name.setText(personLists.get(i).getName());
        viewHolder.te_phonenum.setText(personLists.get(i).getPhonenum());
        return view;
    }

}

