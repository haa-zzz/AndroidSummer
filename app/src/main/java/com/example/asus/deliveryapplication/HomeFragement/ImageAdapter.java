package com.example.asus.deliveryapplication.HomeFragement;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.deliveryapplication.Bmob.Jingdian;
import com.example.asus.deliveryapplication.R;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class ImageAdapter extends BannerAdapter<Jingdian, ImageAdapter.BannerViewHolder> {

    private Context context;
    public ImageAdapter(Context context, List<Jingdian> mDatas) {
        super(mDatas);
        this.context=context;

    }

    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerViewHolder holder, Jingdian data, int position, int size) {

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.img_glide_load_ing)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
                .load(data.getImg())
                .apply(options)
                .into(holder.imageView);

    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BannerViewHolder(@NonNull ImageView view) {
            super(view);
            this.imageView = view;
        }
    }
}