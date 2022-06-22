package com.example.asus.deliveryapplication.HomeFragement;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asus.deliveryapplication.R;

import com.example.asus.deliveryapplication.base.BaseBackActivity;
import com.youth.banner.util.LogUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;

public class TopSpotSearchActivity extends BaseBackActivity {

    private RecyclerView mContactView;
    private List<PhoneModel> mLists=new ArrayList<>();
    private SortAdapter mAdapter;
    private ClearEditText mClearEditText;
    private LinearLayoutManager manager;
    private List<SortModel> mDateList=new ArrayList<>();
    private List<SortModel> mDateBackup=new ArrayList<>();
    private TitleItemDecoration mDecoration;
    private PinyinComparator mComparator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_top_spot);
        initView();
    }

    private void initView()    {
        getSupportActionBar().setTitle("搜索");
        mComparator = new PinyinComparator();
        mContactView = (RecyclerView) findViewById(R.id.mContactView);
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mContactView.setLayoutManager(manager);
        mContactView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new SortAdapter(this, mDateList);
        mAdapter.setOnItemClickListener(new SortAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(String objectId, String nickName, String photoUrl, int position) {
                Intent intent = new Intent(TopSpotSearchActivity.this, SearchResultActivity.class);
                intent.putExtra("city", nickName);
                startActivity(intent);
            }
        });

        mContactView.setAdapter(mAdapter);
        loadArea();
        mDecoration = new TitleItemDecoration(this, mDateList);
        mContactView.addItemDecoration(mDecoration);
        mContactView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
        mClearEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mDateList.size() > 0) {
                    mDateList.clear();
                }
                mDateList.addAll(mDateBackup);
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    private SortModel  filledSingleData(PhoneModel lists) {
        SortModel sortModel = new SortModel();
        sortModel.setName(lists.getName());
        sortModel.setPhoto(lists.getPhoto());
        sortModel.setObjectId(lists.getObjectId());
        String pinyin = PinyinUtils.getPingYin(lists.getName());
        String sortString = pinyin.substring(0, 1).toUpperCase();

        if (sortString.matches("[A-Z]")) {
            sortModel.setLetters(sortString.toUpperCase());
        } else {
            sortModel.setLetters("#");
        }
        mDateBackup.add(sortModel);
        return sortModel;
    }
    private List<SortModel> filledData2(List<PhoneModel> lists) {
        List<SortModel> mSortList = new ArrayList<>();

        for (int i = 0; i < lists.size(); i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(lists.get(i).getName());
            sortModel.setPhoto(lists.get(i).getPhoto());
            sortModel.setObjectId(lists.get(i).getObjectId());
            String pinyin = PinyinUtils.getPingYin(lists.get(i).getName());
            String sortString = pinyin.substring(0, 1).toUpperCase();
            if (sortString.matches("[A-Z]")) {
                sortModel.setLetters(sortString.toUpperCase());
            } else {
                sortModel.setLetters("#");
            }
            mSortList.add(sortModel);
        }
        return mSortList;
    }

    private void filterData(String filterStr) {
        List<SortModel> filterDateList = new ArrayList<>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = filledData2(mLists);
        } else {
            filterDateList.clear();
            for (SortModel sortModel : mDateList) {
                String name = sortModel.getName();
                if (name.indexOf(filterStr.toString()) != -1 ||
                        PinyinUtils.getFirstSpell(name).startsWith(filterStr.toString())
                        || PinyinUtils.getFirstSpell(name).toLowerCase().startsWith(filterStr.toString())
                        || PinyinUtils.getFirstSpell(name).toUpperCase().startsWith(filterStr.toString())
                ) {
                    filterDateList.add(sortModel);
                }
            }
        }

        Collections.sort(filterDateList, mComparator);
        mDateList.clear();
        mDateList.addAll(filterDateList);
        mAdapter.notifyDataSetChanged();
    }

    private void loadArea() {
        if (mLists.size() > 0) {
            mLists.clear();
        }
        if (mDateList.size() > 0) {
            mDateList.clear();
        }
        if (mDateBackup.size() > 0) {
            mDateBackup.clear();
        }
        List<String> names= new ArrayList<>();
        names.add("武汉");
        names.add("北京");
        names.add("南京");
        names.add("上海");
        names.add("烟台");
        names.add("大连");
        names.add("成都");
        names.add("厦门");
        names.add("杭州");
        names.add("拉萨");
        names.add("苏州");
        List<String> photos= new ArrayList<>();
        photos.add("https://bkimg.cdn.bcebos.com/pic/b3b7d0a20cf431ad0f3fdec54d36acaf2fdd9859?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto");
        photos.add("https://bkimg.cdn.bcebos.com/pic/b90e7bec54e736d1773da78090504fc2d562690f?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
        photos.add("https://bkimg.cdn.bcebos.com/pic/5d6034a85edf8db1a2a366c30f23dd54574e7483?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto");
        photos.add("https://bkimg.cdn.bcebos.com/pic/d000baa1cd11728b8a8954eec4fcc3cec2fd2c52?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto");
        photos.add("https://bkimg.cdn.bcebos.com/pic/b03533fa828ba61eee1e99084634970a304e5920?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
        photos.add("https://bkimg.cdn.bcebos.com/pic/eac4b74543a98226565e428d8082b9014b90ebf9?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto");
        photos.add("https://bkimg.cdn.bcebos.com/pic/77094b36acaf2eddb3cb26f3801001e9390193f5?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxNTA=,g_7,xp_5,yp_5/format,f_auto");
        photos.add("https://bkimg.cdn.bcebos.com/pic/0b46f21fbe096b630a932d0708338744eaf8acdb?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto");
        photos.add("https://bkimg.cdn.bcebos.com/pic/d058ccbf6c81800a739c8fb1bf3533fa828b4717?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto");
        photos.add("https://bkimg.cdn.bcebos.com/pic/f636afc379310a555422f8feb74543a9822610b1?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U2MA==,g_7,xp_5,yp_5/format,f_auto");
        photos.add("https://bkimg.cdn.bcebos.com/pic/a2cc7cd98d1001e9a513f915bf0e7bec54e797f9?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");

        for(int i=0;i<11;++i) {
            PhoneModel phonemodel = new PhoneModel();
            phonemodel.setName(names.get(i));
            phonemodel.setPhoto(photos.get(i));
            mLists.add(phonemodel);
            mAdapter.notifyDataSetChanged();
            mDateList.add(filledSingleData(phonemodel));
            Collections.sort(mDateList, mComparator);
        }
    }
}
