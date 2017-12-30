package com.zc.okhttp_utils;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn_upload)
    Button btnUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_upload)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btn_upload:
                multiFileUpload(view);
                break;
        }
    }

    //多文件上传
    public void multiFileUpload(View view) {
        String mBaseUrl = "http://192.168.1.186:8080/FileUpload/FileUploadServlet";

        File file = new File(Environment.getExternalStorageDirectory(), "icon_1.png");
        File file2 = new File(Environment.getExternalStorageDirectory(), "icon_2.png");
        if (!file.exists()) {
            Toast.makeText(MainActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
//        Map<String, String> params = new HashMap<>();
//        params.put("username", "张鸿洋");
//        params.put("password", "123");

        String url = mBaseUrl;
        OkHttpUtils.post()//
                .addFile("mFile", "server_icon_1.png", file)//
                .addFile("mFile", "server_icon_2.png", file2)//
                .url(url)
                //.params(params)//
                .build()//
                .execute(new MyStringCallback());
    }

    class MyStringCallback extends StringCallback{

        @Override
        public void onError(Call call, Exception e, int id) {
            Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResponse(String response, int id) {
            Toast.makeText(MainActivity.this,response,Toast.LENGTH_SHORT).show();
        }
    }

}
