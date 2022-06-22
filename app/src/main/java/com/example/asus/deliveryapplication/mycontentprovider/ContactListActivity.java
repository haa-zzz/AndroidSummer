package com.example.asus.deliveryapplication.mycontentprovider;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.asus.deliveryapplication.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class ContactListActivity extends AppCompatActivity {
    private ListView listView;
    private List<com.example.asus.deliveryapplication.mycontentprovider.person> personList=new ArrayList<>();
    private ContentResolver mContentResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        listView= (ListView) findViewById(R.id.listview);

        mContentResolver=getContentResolver();
        Cursor cursor=mContentResolver.query(ContactsContract.RawContacts.CONTENT_URI,null,null,null,null);
        while (cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndex("_id"));
            String name=cursor.getString(cursor.getColumnIndex("display_name"));
            Cursor phone=mContentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,"raw_contact_id=?",new String[]{id+""},null);
            while (phone.moveToNext()){
                String phonenum=phone.getString(phone.getColumnIndex("data1"));
                com.example.asus.deliveryapplication.mycontentprovider.person person=new com.example.asus.deliveryapplication.mycontentprovider.person(id,name,phonenum);
                personList.add(person);
            }
            phone.close();
        }
        cursor.close();

        MyListAdapter myadapter=new MyListAdapter(this,personList);
        listView.setAdapter(myadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent();
                intent.putExtra("number",personList.get(i).getPhonenum());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
