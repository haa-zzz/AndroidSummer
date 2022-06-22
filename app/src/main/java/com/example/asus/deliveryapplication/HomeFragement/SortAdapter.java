package com.example.asus.deliveryapplication.HomeFragement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


import com.example.asus.deliveryapplication.R;
import com.example.asus.deliveryapplication.utils.GlideHelper;

import java.util.List;

public class SortAdapter extends RecyclerView.Adapter<SortAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<SortModel> mData;
    private Context mContext;

    public SortAdapter(Context context, List<SortModel> data) {
        mInflater = LayoutInflater.from(context);
        mData = data;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_name, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(mData.get(position).getObjectId(),mData.get(position).getName(),
                            mData.get(position).getPhoto(),position);
                }
            });

        }
        GlideHelper.loadUrl(mContext, this.mData.get(position).getPhoto(), holder.iv_photo);
        holder.tvName.setText(this.mData.get(position).getName());

        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, mData.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnItemClickListener {
        void onItemClick(String objectId, String nickName, String photoUrl, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void updateList(List<SortModel> list){
        this.mData = list;
        notifyDataSetChanged();
    }

    public Object getItem(int position) {
        return mData.get(position);
    }

    public int getSectionForPosition(int position) {
        return mData.get(position).getLetters().charAt(0);
    }

    public int getPositionForSection(int section) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = mData.get(i).getLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_photo;
        private   TextView tvName;

        public ViewHolder(View itemView ) {
            super(itemView);
            iv_photo=itemView.findViewById(R.id.iv_photo);
            tvName=itemView.findViewById(R.id.tvName);

        }
    }
}
