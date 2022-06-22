package com.example.asus.deliveryapplication.Adapter;

import android.content.Context;
import android.text.SpannableString;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.asus.deliveryapplication.utils.GlideHelper;
import com.example.asus.deliveryapplication.ExpandTextView;

import java.io.File;

public class CommonViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private View mContentView;

    public CommonViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
        mContentView = itemView;
    }
    public View getRootView(){
        return mContentView;
    }

    public static CommonViewHolder getViewHolder(ViewGroup parent, int layoutId) {
        return new CommonViewHolder(View.inflate(parent.getContext(), layoutId, null));
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mContentView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public CommonViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }
    public CommonViewHolder setExpandText(int viewId, String text) {
        ExpandTextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }
    public CommonViewHolder setText(int viewId, SpannableString text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public CommonViewHolder setImageUrl(Context mContext, int viewId, String url) {
        ImageView iv = getView(viewId);
        GlideHelper.loadUrl(mContext, url, iv);
        return this;
    }

    public CommonViewHolder setImageUrl(Context mContext, int viewId, String url, int w, int h) {
        ImageView iv = getView(viewId);
        GlideHelper.loadSmollUrl(mContext, url, w, h, iv);
        return this;
    }

    public CommonViewHolder setImageFile(Context mContext, int viewId, File file) {
        ImageView iv = getView(viewId);
        GlideHelper.loadFile(mContext, file, iv);
        return this;
    }

    public CommonViewHolder setImageResource(int viewId, int resId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

    public CommonViewHolder setBackgroundColor(int viewId, int color) {
        ImageView iv = getView(viewId);
        iv.setBackgroundColor(color);
        return this;
    }

    public CommonViewHolder setTextColor(int viewId, int color) {
        TextView tv = getView(viewId);
        tv.setTextColor(color);
        return this;
    }

    public CommonViewHolder setVisibility(int viewId, int visibility) {
        TextView tv = getView(viewId);
        tv.setVisibility(visibility);
        return this;
    }
    public CommonViewHolder setExpandTextviewVisibility(int viewId, int visibility) {
        ExpandTextView tv = getView(viewId);
        tv.setVisibility(visibility);
        return this;
    }
}
