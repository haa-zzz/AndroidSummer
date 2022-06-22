package com.example.asus.deliveryapplication.HomeFragement;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.deliveryapplication.Bmob.Jingdian;
import com.example.asus.deliveryapplication.R;
import com.example.asus.deliveryapplication.utils.CommonUtils;


import java.text.SimpleDateFormat;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Jingdian> data;
    private final int N_TYPE = 0;
    private OnItemClickListener mOnItemClickListener;

    private SimpleDateFormat dateFormat;

    public HomeAdapter(Context context, List<Jingdian> data) {
        this.context = context;
        this.data = data;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_top_spot, viewGroup, false);
        return new RecyclerViewHolder(view, N_TYPE);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        final RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) viewHolder;
        Jingdian st = data.get(position);
        recyclerViewHolder.tv_desc.setText(st.getDes());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.img_glide_load_ing)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
                .load(st.getImg())
                .apply(options)
                .into(recyclerViewHolder.iv_top_spot);

        recyclerViewHolder.iv_top_spot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(st, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_desc;

        public ImageView iv_top_spot;

        public RecyclerViewHolder(View view, int view_type) {
            super(view);
            tv_desc = view.findViewById(R.id.tv_desc);
            iv_top_spot = view.findViewById(R.id.iv_top_spot);
        }
    }

    public void add(List<Jingdian> addMessageList) {
        int position = 0;
        if (CommonUtils.isEmpty(data)) {
            position = data.size();
            data.addAll(position, addMessageList);
        } else {
            position = 0;
            data.addAll(addMessageList);
        }
        notifyDataSetChanged();
    }

    public void refresh(List<Jingdian> newList) {
        data.clear();
        data.addAll(newList);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Jingdian textData, int position);

    }
}

