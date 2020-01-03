package com.example.hw19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        HandlerThread mThread = new HandlerThread("name");
        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();

        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        Handler mThreadHandler=new Handler(mThread.getLooper());
        //請經紀人指派工作名稱 r，給工人做
        mThreadHandler.post(r1);
    }
    private Runnable r1=new Runnable () {
        public void run() {
            Log.d("test", "1118 New Thread Runing..");
            //請經紀人指派工作名稱 r，給工人做
            mUI_HandlerMSG.postDelayed(r2, 2000);
            mUI_HandlerMSG.sendEmptyMessage(MSG_UPLOAD_OK);
        }
    };

    //工作名稱 r2 的工作內容
    private Runnable r2=new Runnable () {

        public void run() {

            // TODO Auto-generated method stub
            //.............................
            //顯示畫面的動作
            TextView tv = findViewById(R.id.TextView);
            tv.setText("Got it");
        }

    };
    final int MSG_UPLOAD_OK = 1;
    final int MSG_UPLOAD_ERR = 2;
    private Handler mUI_HandlerMSG = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case MSG_UPLOAD_OK:
                    TextView tv = findViewById(R.id.TextView);
                    tv.setText("Got Message");

                    break;
                case MSG_UPLOAD_ERR:
                    TextView tv1 = findViewById(R.id.TextView);
                    tv1.setText("Message err");

                    break;
            }
        }
    };

}
