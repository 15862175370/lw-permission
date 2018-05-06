package com.example.asus.permissionproject;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.permission.sdk.CheckSelfPermissionSDK;
import com.permission.sdk.listener.IPermissionCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IPermissionCallback {
    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_PHONE_STATE};
    private static final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_main01).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_main01:
              /*  // 缺少权限时, 进入权限配置页面
                if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
                    PermissioActivity.startActivityForResult(MainActivity.this, REQUEST_CODE, PERMISSIONS);
                    Toast.makeText(MainActivity.this,"权限没有通过",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,"权限通过",Toast.LENGTH_SHORT).show();
                }*/
                new CheckSelfPermissionSDK.Builder()
                        .permissions(new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA})
                        .build()
                        .start(this, this);
                break;
          /*  case R.id.btn_main02:
               *//* new CheckSelfPermissionSDK.Builder()
                        .permissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE
                                , Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO
                                , Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_PHONE_STATE})
                        .build()
                        .start(this, this);*//*
                break;*/
        }
    }

    @Override
    public void permissionSuccess(String[] permissionStr) {
        Toast.makeText(this, "success", Toast.LENGTH_LONG).show();
    }

    @Override
    public void permissionDeny(String[] permissionStr) {
        Toast.makeText(this, "fail", Toast.LENGTH_LONG).show();
    }
}
