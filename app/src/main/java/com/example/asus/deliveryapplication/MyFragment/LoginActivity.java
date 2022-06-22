package com.example.asus.deliveryapplication.MyFragment;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.asus.deliveryapplication.R;

/**
 * 登录页面
 * 使用本地数据库SQLite来存储用户信息。登录时检查name，password是否存在和匹配
 * 同时还有一个查询手机联系人的功能
 */
public class LoginActivity extends AppCompatActivity {
    private EditText et1,et2;
    private TextView tv1;
    private Button bt1;
    private MySQLiteOpenHelper openHelper;
    private SQLiteDatabase writableDatabase;
    private EditText ed_num;
    private Button btn_query,btn_add;
    private ContentResolver mContentResolver;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==RESULT_OK){
            ed_num.setText(data.getStringExtra("number"));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et1=findViewById(R.id.et_user_name);
        et2=findViewById(R.id.et_psw);
        tv1 = findViewById(R.id.tv_register);
        bt1=findViewById(R.id.btn_login);
        //点击注册账号，跳转到RegisterActivity
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        //点击登录按钮
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //用户名为空，给个提示
                if (et1.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();

                }else if(et2.getText().toString().equals("")){   //密码为空，给个提示
                    Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                }else{
                    //读取数据库用户信息，看看匹不匹配
                    openHelper = new MySQLiteOpenHelper(LoginActivity.this, "user.db", null, 1);
                    writableDatabase = openHelper.getWritableDatabase();
                    Cursor user = writableDatabase.query("user", new String[]{"name", "password"}, "name=?", new String[]{et1.getText().toString()}, null, null, null);
                    if (user.moveToFirst()){
                        if (!user.getString(user.getColumnIndex("name")).equals(et1.getText().toString())){
                            Toast.makeText(LoginActivity.this,"用户名不存在",Toast.LENGTH_SHORT).show();
                        }else if(!user.getString(user.getColumnIndex("password")).equals(et2.getText().toString())){
                            Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                        }else{
                            //这种就是匹配上了，携带用户名跳转到我的页面
                            Intent data = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putString("name",et1.getText().toString());
                            bundle.putString("password",et2.getText().toString());
                            data.putExtras(bundle);
                            Intent intent = new Intent("com.lcg.update");
                            Intent intent1=new Intent();
                            intent1.setAction("com.example.asus.deliveryapplication.broadcast.abc");

                            data.setClass(LoginActivity.this, MyFragment.class);
                            setResult(RESULT_OK, data);
                            finish();
                        }
                    }else{
                        Toast.makeText(LoginActivity.this,"用户名不存在",Toast.LENGTH_SHORT).show();
                    }
                    user.close();
                }
            }
        });

        ed_num= (EditText) findViewById(R.id.ed_num);
        btn_query= (Button) findViewById(R.id.btn_query);
        btn_add= (Button) findViewById(R.id.btn_add);
        requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},1);
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,com.example.asus.deliveryapplication.mycontentprovider.ContactListActivity.class);
                startActivityForResult(intent,1);
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values=new ContentValues();
                mContentResolver=getContentResolver();
                Uri insertUri=mContentResolver.insert(ContactsContract.RawContacts.CONTENT_URI,values);
                long id= ContentUris.parseId(insertUri);
                values.clear();
                values.put(ContactsContract.Data.RAW_CONTACT_ID,id);
                values.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
                values.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,"张三");
                mContentResolver.insert(ContactsContract.Data.CONTENT_URI,values);
                values.clear();
                values.put(ContactsContract.Data.RAW_CONTACT_ID,id);
                values.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                String phonenumber=ed_num.getText().toString();;
                values.put(ContactsContract.CommonDataKinds.Phone.NUMBER,phonenumber);
                mContentResolver.insert(ContactsContract.Data.CONTENT_URI,values);
                Toast.makeText(LoginActivity.this,"插入成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
