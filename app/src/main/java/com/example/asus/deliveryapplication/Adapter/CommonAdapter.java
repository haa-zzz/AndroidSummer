package com.example.asus.deliveryapplication.Adapter;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.example.asus.deliveryapplication.utils.CommonUtils;
import java.util.List;

/**
 * listView RecyclerView对应的Adapter,用于协助列表完成数据加载和展示
 * @param <T>
 */
public class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {

    private List<T> mList;

    private OnBindDataListener<T> onBindDataListener;
    private OnMoreBindDataListener<T> onMoreBindDataListener;

    public CommonAdapter(List<T> mList, OnBindDataListener<T> onBindDataListener) {
        this.mList = mList;
        this.onBindDataListener = onBindDataListener;
    }

    public CommonAdapter(List<T> mList, OnMoreBindDataListener<T> onMoreBindDataListener) {
        this.mList = mList;
        this.onBindDataListener = onMoreBindDataListener;
        this.onMoreBindDataListener = onMoreBindDataListener;
    }

    public interface OnBindDataListener<T> {
        void onBindViewHolder(T model, CommonViewHolder viewHolder, int type, int position);

        int getLayoutId(int type);
    }

    public interface OnMoreBindDataListener<T> extends OnBindDataListener<T> {
        int getItemType(int position);
    }
    @Override
    public int getItemViewType(int position) {
        if (onMoreBindDataListener != null) {
            return onMoreBindDataListener.getItemType(position);
        }
        return 0;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = onBindDataListener.getLayoutId(viewType);//抛给外部提供layoutId,
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(parent, layoutId);//创建viewHolder
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        onBindDataListener.onBindViewHolder(
                mList.get(position), holder, getItemViewType(position), position);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }
    public void add(List<T> addMessageList) {
        int position=0;
        if(CommonUtils.isEmpty(mList)){
            position = mList.size();
            mList.addAll(position, addMessageList);
        }else{
            position=0;
            mList.addAll( addMessageList);
        }
        notifyDataSetChanged();
    }

    public void refresh(List<T> newList) {
        mList.clear();
        mList.addAll(newList);
        notifyDataSetChanged();
    }
}
