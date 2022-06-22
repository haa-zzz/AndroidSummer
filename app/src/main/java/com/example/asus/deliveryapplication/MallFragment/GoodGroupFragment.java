package com.example.asus.deliveryapplication.MallFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.asus.deliveryapplication.Adapter.CommonAdapter;
import com.example.asus.deliveryapplication.Adapter.CommonViewHolder;
import com.example.asus.deliveryapplication.R;
import com.example.asus.deliveryapplication.Bmob.TourGroup;
import com.example.asus.deliveryapplication.utils.CommonUtils;
import com.example.asus.deliveryapplication.utils.Logger;
import com.example.asus.deliveryapplication.ExpandTextView;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
/**
 * 跟团游
 * 用一个RecyclerView去容纳数据库中的精选的数据
 */
public class GoodGroupFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<TourGroup> data = new ArrayList<>();
    private RefreshLayout refreshLayout;
    private CommonAdapter adapter;
    private static final int STATE_REFRESH = 0;
    private static final int STATE_MORE = 1;
    private int limit = 10;
    private int curPage = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_mall, container, false);
        initView(view);
        return view;
    }

    /**
     * 初始化View, 主要就是对recyclerView进行初始化，设置Manager，设置adapter
     * @param view
     */
    private void initView(View view) {
        recyclerView = view.findViewById(R.id.mRecyclerView);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        adapter = new CommonAdapter<>(data, new CommonAdapter.OnBindDataListener<TourGroup>() {
            @Override
            public void onBindViewHolder(final TourGroup model, final CommonViewHolder viewHolder, int type, final int position) {
                Logger.i("图片；"+model.getPic());
                try {
                    String pic = model.getPic();
                    viewHolder.setImageUrl(getActivity(), R.id.iv_photo, pic);
                }catch(Exception e){
                    Logger.i("图片 错误 信息："+e.toString());
                }
                ((TextView)viewHolder.getView(R.id.tv_nickname)).setText(model.getCommentor());
                ((TextView)viewHolder.getView(R.id.tv_time)).setText(model.getPrice());
                ((ExpandTextView)viewHolder.getView(R.id.tv_text)).setText(model.getComment());
            }
            @Override
            public int getLayoutId(int type) {
                return R.layout.item_food;
            }
        });
        recyclerView.setAdapter(adapter);
        refreshLayout = view.findViewById(R.id.mSquareSwipeLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                queryData(0, STATE_REFRESH);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000);
                queryData(curPage, STATE_MORE);
            }
        });
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.autoRefresh();
        queryData(0, STATE_REFRESH);
    }

    /**
     * 去BMob中查询数据，并吧结果同步给adapter，去刷新数据
     * @param page
     * @param actionType
     */
    private void queryData(int page, final int actionType) {

        BmobQuery<TourGroup> query = new BmobQuery<>();
        if (actionType == STATE_MORE) {
            query.setSkip(page * limit );
        } else {
            page = 0;
            query.setSkip(page);
        }
        query.setLimit(limit);
        query.order("-createdAt");
        query.findObjects(new FindListener<TourGroup>() {
            @Override
            public void done(List<TourGroup> list, BmobException e) {
                if(e==null){
                    if(CommonUtils.isEmpty(list)){
                        if (actionType == STATE_REFRESH) {
                            curPage = 0;
                            if(CommonUtils.isEmpty(data)) {
                                data.clear();
                            }
                            data.addAll(list);
                            adapter.refresh(list);
                        }else{
                            adapter.add(list);
                        }
                        curPage++;
                    }else if (actionType == STATE_MORE) {
                        refreshLayout.finishLoadMoreWithNoMoreData();
                    } else if (actionType == STATE_REFRESH) {
                        Logger.i("没有数据");
                    }
                    refreshLayout.finishRefresh();
                }else{
                    refreshLayout.finishRefresh(false);
                }
            }
        });
    }
}