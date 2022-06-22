package com.example.asus.deliveryapplication.YiqingFragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asus.deliveryapplication.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class yiqing extends AppCompatActivity {
    private ListView lvNews;
    private NewsAdapter adapter;
    private List<High_list> dataList;
    private SwipeRefreshLayout swipeRefresh;
    public TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        lvNews = (ListView)findViewById(R.id.lvNews);
        dataList = new ArrayList<High_list>();
        adapter = new NewsAdapter(this, dataList);
        lvNews.setAdapter(adapter);

        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sendRequestWithOKHttp();
            }
        });
    }

    private void sendRequestWithOKHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://apis.juhe.cn/springTravel/risk?key=8e1c38dbead85212edf4ff5f856649ba")
                            .build();
                    Response response = null;
                    response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d("测试：", responseData);
                    parseJsonWithGson(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void parseJsonWithGson(String jsonData){
        Gson gson = new Gson();
        News news = gson.fromJson(jsonData, News.class);
        List<High_list> list = news.getResult().getHigh_list();
        for (int i=0; i<list.size(); i++){
            String type = list.get(i).getType();
            String city = list.get(i).getCity();
            String province = list.get(i).getProvince();
            String county = list.get(i).getCounty();
            dataList.add(new High_list(type, city,county,province));
        }
    }
}
