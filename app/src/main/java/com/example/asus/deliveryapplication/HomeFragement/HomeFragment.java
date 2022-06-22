package com.example.asus.deliveryapplication.HomeFragement;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.asus.deliveryapplication.Bmob.Jingdian;
import com.example.asus.deliveryapplication.R;
import com.example.asus.deliveryapplication.utils.CommonUtils;
import com.example.asus.deliveryapplication.utils.Logger;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 首页，
 */
public class HomeFragment extends Fragment implements MyScrollView.OnScrollListener, View.OnClickListener {
    private MyScrollView myScrollView;
    private View view;
    private LinearLayout option2;
    private int searchLayoutTop;
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private List<Jingdian> data = new ArrayList<>();
    private RefreshLayout refreshLayout;
    private Button btn_op1, btn_op2, btn_op3,m1,m2,m3,m4;
    private ImageView iv_seek;

    private static final int STATE_REFRESH = 0;
    private static final int STATE_MORE = 1;
    private int limit = 10;
    private int curPage = 0;

    private int clickPos = 1;
    LinearLayout search01, option1;
    RelativeLayout rlayout;
    private Banner banner;
    private int queryType = 1;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        view.getViewTreeObserver().addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() {
            @Override
            public void onWindowFocusChanged(final boolean hasFocus) {
                if (hasFocus) {
                    searchLayoutTop = rlayout.getBottom();
                }
            }
        });
        queryType = 1;
        banner = view.findViewById(R.id.banner);
        init(view);
        recyclerView = view.findViewById(R.id.RecyclerView);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setReverseLayout(false);
        homeAdapter = new HomeAdapter(getActivity(), data);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(homeAdapter);

        homeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Jingdian jingdian, int position) {
                Intent intent = new Intent(getActivity(), TopSpotIntroduceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("topSpotData", (Serializable) jingdian);//序列化,要注意转化(Serializable)对象也要序列化
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        refreshLayout = view.findViewById(R.id.mSwipeLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                queryData(0, STATE_REFRESH, "country", clickPos, queryType);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000);
                queryData(curPage, STATE_MORE, "country", clickPos, queryType);
            }
        });
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.autoRefresh();
        queryData(0, STATE_REFRESH, "country", clickPos, queryType);
        return view;
    }

    private void init(View view) {
        btn_op1 = view.findViewById(R.id.btn_op1);
        btn_op2 = view.findViewById(R.id.btn_op2);
        btn_op3 = view.findViewById(R.id.btn_op3);
        btn_op1.setOnClickListener(this);
        btn_op2.setOnClickListener(this);
        btn_op3.setOnClickListener(this);
        iv_seek = view.findViewById(R.id.iv_seek);
        iv_seek.setOnClickListener(this);
        m1=view.findViewById(R.id.m1);
        m1.setOnClickListener(this);
        m2=view.findViewById(R.id.m2);
        m2.setOnClickListener(this);
        m3=view.findViewById(R.id.m3);
        m3.setOnClickListener(this);
        option2 = view.findViewById(R.id.option2);
        myScrollView = view.findViewById(R.id.myScrollView);
        search01 = view.findViewById(R.id.search01);
        option1 = view.findViewById(R.id.option1);
        rlayout = view.findViewById(R.id.rlayout);
        myScrollView.setOnScrollListener(this);
    }

    @Override
    public void onScroll(int scrollY) {
        if (scrollY >= searchLayoutTop) {
            if (option2.getParent() != search01) {
                option1.removeView(option2);
                search01.addView(option2);
            }
        } else {
            if (option2.getParent() != option1) {
                search01.removeView(option2);
                option1.addView(option2);
            }
        }
    }

    private void queryData(int page, final int actionType, String keyword, int value, int type) {
        Logger.i("现在下载的是：pageN:" + page + " limit:" + limit + " actionType:"
                + actionType);
        BmobQuery<Jingdian> query = new BmobQuery<>();
        if (actionType == STATE_MORE) {
            query.setSkip(page * limit);
        } else {
            page = 0;
            query.setSkip(page);
        }
        query.setLimit(limit);
        query.order("-createdAt");
        if (type == 1) {
            query.addWhereEqualTo(keyword, value);
        } else if (type == 2) {
            query.addWhereEqualTo("type", 2);
        }
        query.findObjects(new FindListener<Jingdian>() {
            @Override
            public void done(List<Jingdian> list, BmobException e) {
                if (e == null) {
                    if (CommonUtils.isEmpty(list)) {
                        if (actionType == STATE_REFRESH) {
                            curPage = 0;
                            if (CommonUtils.isEmpty(data)) {
                                data.clear();
                            }
                            data.addAll(list);
                            homeAdapter.refresh(list);
                        } else {
                            homeAdapter.add(list);
                        }
                        curPage++;
                    } else if (actionType == STATE_MORE) {
                        refreshLayout.finishLoadMoreWithNoMoreData();
                    } else if (actionType == STATE_REFRESH) {
                        Logger.i("没有数据");
                    }
                    refreshLayout.finishRefresh();
                } else {
                    refreshLayout.finishRefresh(false);
                }
            }
        });
        useBanner();
    }

    private void useBanner() {

        banner.addBannerLifecycleObserver(this)
                .setAdapter(new ImageAdapter(getActivity(), data))
                .setIndicator(new CircleIndicator(getActivity()))
                .start();
    }

    @Override
    public void onClick(View v) {
        Intent intent1=new Intent(getActivity(),MusicPlayer.class);
        switch (v.getId()) {
            case R.id.btn_op1:
                clickPos = 1;
                queryType = 1;
                queryData(0, STATE_REFRESH, "country", clickPos, queryType);
                break;
            case R.id.btn_op2:
                clickPos = 2;
                queryType = 2;
                queryData(0, STATE_REFRESH, "country", clickPos, queryType);
                break;
            case R.id.btn_op3:
                clickPos = 0;
                queryType = 1;
                queryData(0, STATE_REFRESH, "country", clickPos, queryType);
                break;
            case R.id.iv_seek:
                startActivity(new Intent(getActivity(), TopSpotSearchActivity.class));
                break;
            case R.id.m1:
                Toast.makeText(getActivity(),"播放音乐", Toast.LENGTH_SHORT).show();
                intent1.putExtra("action","play");
                getActivity().startService(intent1);
                break;
            case R.id.m2:
                Toast.makeText(getActivity(),"暂停播放", Toast.LENGTH_SHORT).show();
                intent1.putExtra("action","pause");
                getActivity().startService(intent1);
                break;
            case R.id.m3:
                Toast.makeText(getActivity(),"停止播放", Toast.LENGTH_SHORT).show();
                intent1.putExtra("action","stop");
                getActivity().startService(intent1);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        banner.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        banner.destroy();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stop();
    }
}

