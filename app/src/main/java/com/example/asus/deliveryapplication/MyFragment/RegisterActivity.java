package com.example.asus.deliveryapplication.MyFragment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.asus.deliveryapplication.Bmob.MyUser;
import com.example.asus.deliveryapplication.R;
import com.example.asus.deliveryapplication.utils.Logger;
import com.example.asus.deliveryapplication.utils.SpUtils;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * 注册页面
 */
public class RegisterActivity extends AppCompatActivity {
    private Button bt1;
    private EditText et_User,et_Psw,et_Psw2;
    private MySQLiteOpenHelper openHelper;
    private SQLiteDatabase writableDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bt1 = findViewById(R.id.btn_login);
        et_User = findViewById(R.id.et_user_name);
        et_Psw = findViewById(R.id.et_psw);
        et_Psw2 = findViewById(R.id.et_psw2);
        openHelper = new MySQLiteOpenHelper(RegisterActivity.this, "user.db", null, 1);

        //点击注册，进行简单判断后，如果符合规范，注册用户，添加到本地数据库SQLite
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
                if (et_User.getText().toString() == null){
                    Toast.makeText(RegisterActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                }else if(et_Psw.getText().toString() == null){
                    Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }else if(et_Psw2.getText().toString()== null){
                    Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }else if(!et_Psw2.getText().toString().equals(et_Psw.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"两次输入密码不一致",Toast.LENGTH_SHORT).show();
                }else{
                    //这个就是往数据库里写数据了
                    writableDatabase = openHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("name",et_User.getText().toString());
                    values.put("password",et_Psw.getText().toString());
                    writableDatabase.insert("user",null,values);
                    finish();
                    Intent intent1=new Intent();
                    intent1.setAction("com.example.asus.deliveryapplication.broadcast.abc");
                    sendBroadcast(intent1);
                }
            }
        });
    }
    private void register() {
        String userName2 = et_User.getText().toString();
        String passWord2 = et_Psw.getText().toString();
        if (TextUtils.isEmpty(userName2)) {
            Toast.makeText(RegisterActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(passWord2)) {
            Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        signUp(userName2, passWord2,"123");
    }

    private void signUp(final String myAccount, final String password,String nickName) {
        MyUser user = new MyUser();
        user.setUsername(myAccount);
        user.setPassword(password);
        user.setTokenNickName(nickName);
        user.signUp(new SaveListener<MyUser>() {
            @Override
            public void done(MyUser user, BmobException e) {
                if (e == null) {
                    Logger.i("注册成功");
                    loginByAccount(myAccount, password);
                } else {
                    Logger.i("注册失败：" + e.toString());
                }
            }
        });
    }

    private void loginByAccount(final String username, String password) {
        BmobUser.loginByAccount(username, password, new LogInListener<MyUser>() {
            @Override
            public void done(MyUser user, BmobException e) {
                if (e == null) {
                    Logger.i("登录成功");
                } else {
                    Logger.i("登录失败：" + e.toString());
                }
            }
        });
    }
}
