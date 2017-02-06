package com.example.studypermission;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;
import com.zhy.m.permission.ShowRequestPermissionRationale;

/**
 * 学习6.0的权限（）
 * https://github.com/hongyangAndroid/MPermissions
 */
public class MainActivity extends AppCompatActivity {
    //权限回调所用的回调码
    public static final int MY_PERMISSONS_REQUEST_CALL_PHONE = 1;
    private MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        TextView tvCallPhone = (TextView) findViewById(R.id.tvCallPhone);
        tvCallPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) 
            {
                if (!MPermissions.shouldShowRequestPermissionRationale(activity,Manifest.permission.CALL_PHONE,MY_PERMISSONS_REQUEST_CALL_PHONE))
                MPermissions.requestPermissions(activity,MY_PERMISSONS_REQUEST_CALL_PHONE,Manifest.permission.CALL_PHONE);
                /*if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.CALL_PHONE},MY_PERMISSONS_REQUEST_CALL_PHONE);

                } else
                {
                    callPhone();
                }*/
            }
        });
    }

    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "18634943364");
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
       /* if (requestCode==MY_PERMISSONS_REQUEST_CALL_PHONE)
        {
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                callPhone();
            }else 
            {
                Toast.makeText(activity, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            
        }*/
        MPermissions.onRequestPermissionsResult(activity,requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        
    }
    @PermissionDenied(MY_PERMISSONS_REQUEST_CALL_PHONE)
    public void deny()
    {
        Toast.makeText(this, "DENY ACCESS SDCARD!", Toast.LENGTH_SHORT).show();
    }
    @PermissionGrant(MY_PERMISSONS_REQUEST_CALL_PHONE)
    public void grant()
    {
        callPhone();
    }
    
    @ShowRequestPermissionRationale(MY_PERMISSONS_REQUEST_CALL_PHONE)
    public void showReason()
    {
        //说明原因后，还需要继续申请权限。
        Toast.makeText(activity, "必须获得权限", Toast.LENGTH_SHORT).show();
        MPermissions.requestPermissions(activity,MY_PERMISSONS_REQUEST_CALL_PHONE,Manifest.permission.CALL_PHONE);
    }
}
