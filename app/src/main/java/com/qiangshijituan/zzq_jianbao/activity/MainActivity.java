package com.qiangshijituan.zzq_jianbao.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.qiangshijituan.zzq_jianbao.R;
import com.qiangshijituan.zzq_jianbao.fragment.HomeFragment;
import com.qiangshijituan.zzq_jianbao.fragment.LoginFragment;
import com.qiangshijituan.zzq_jianbao.fragment.PublishFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        // 1.初始化tabhost
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        // 2.设置TabHost的参数 , 第一个参数:上下文 ， 第二个:参数碎片管理者 ， 第三个参数：要操作fragment应放的位置
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        View view = getIndicatorView(R.string.home, R.drawable.home);
        View view2 = getIndicatorView(R.string.issue, R.drawable.publish);
        View view3 = getIndicatorView(R.string.personal, R.drawable.personage);


        // 3.设置下方Indicate(指示器)
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("a");//创建指示器“a”是这个指示器的一个tag（标记）任意写
        tabSpec.setIndicator(view); // 设置tabHost的样式
        Bundle bundle = new Bundle();
        bundle.putInt("name", 1);
        tabHost.addTab(tabSpec, HomeFragment.class, bundle);
        Bundle bundle2 = new Bundle();
        bundle.putInt("name", 2);
        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("b");
        tabSpec2.setIndicator(view2);
        tabHost.addTab(tabSpec2, PublishFragment.class, bundle2);
        Bundle bundle3 = new Bundle();
        bundle.putInt("name", 3);
        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("c");
        tabSpec3.setIndicator(view3);
        tabHost.addTab(tabSpec3, LoginFragment.class, bundle3);
    }

    @NonNull
    private View getIndicatorView(int textid, int imgid) {
        View view = LayoutInflater.from(this).inflate(R.layout.jianbao_table_bottom, null);
        ImageView imgs = (ImageView) view.findViewById(R.id.iv_bottom_imgs);
        TextView tv = (TextView) view.findViewById(R.id.tv_bottom_tv);
        tv.setText(textid);
        imgs.setBackgroundResource(imgid);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();


    }


    /**
     * 点击空白区域隐藏键盘.
     *
     * @param event the event
     * @return true, if successful
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (this.getCurrentFocus() != null) {
                if (this.getCurrentFocus().getWindowToken() != null) {
                    imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
        return true;
    }


}
