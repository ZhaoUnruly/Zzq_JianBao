package com.qiangshijituan.zzq_jianbao.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qiangshijituan.zzq_jianbao.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 赵自强 on 2016/12/8.
 */

public class CountDownActivity extends Activity{

    private Intent intent;
    private int num = 5 ;
    private Timer timer = new Timer();
    private TextView textView;
    private Button button;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jianbao_countdown);
        textView = (TextView) findViewById(R.id.tv_countdown);
        button = (Button) findViewById(R.id.bt_tiaozhuan);
        timer.schedule(task, 1000, 1000);
        initData();
    }
    private void initData() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CountDownActivity.this, MainActivity.class);
                startActivity(intent);
                timer.cancel();

            }
        });
    }
    //设置线程
    TimerTask task = new TimerTask() {

        @Override
        public void run() {
            // 更新UI控件
            runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    num--;
                    //设置文本内容
                    textView.setText("跳过广告  "+num);
                    //判断num小于0，停止计时
                    if (num <= 0) {

                        //设置跳转到主界面
                        Intent intent = new Intent(CountDownActivity.this, MainActivity.class);
                        startActivity(intent);
                        //设置文本不可见
                        textView.setVisibility(View.GONE);
                    }
                }
            });

        }
    };
    // 当Activity 停止是销毁掉这个Activity
    @Override
    protected void onStop() {
        super.onStop();
        finish();
        timer.cancel();
    }
}
