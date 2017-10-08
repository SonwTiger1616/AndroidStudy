package com.example.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendNotice = (Button) findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_notice:
                Intent intent = new Intent(this, NotificationActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                notificationManager.cancel(1); //点击通知后取消显示，1表示通知的编号
                Notification notification = new NotificationCompat.Builder(this)
                        .setContentTitle("This is content title") //设置标题
                        .setContentText("This is content text") //设置通知内容
                        .setWhen(System.currentTimeMillis()) //设置通知时间
                        .setSmallIcon(R.mipmap.ic_launcher) //设置通知在状态栏显示的小图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setContentIntent(pendingIntent) //设置点击通知后调用的Intent
                        .setAutoCancel(true) // 点击通知后取消显示
                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Ding.ogg"))) //设置声音
                        .setVibrate(new long[]{0, 1000, 1000, 1000}) // 设置振动，需要添加权限声明
                        .setLights(Color.GREEN, 1000, 1000) //设置呼吸灯闪烁
//                        .setDefaults(Notification.DEFAULT_ALL) //设置默认，系统自己决定，包括：振动、响铃、呼吸灯
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("Learn how to build notifications, send " +
                                "and sync data, and use voice actions. Get the official Android IDE and developer tools to" +
                                " build apps for Android."))
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.big_image)))
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .build();
                notificationManager.notify(1, notification);
                break;
            default:
                break;
        }
    }
}
