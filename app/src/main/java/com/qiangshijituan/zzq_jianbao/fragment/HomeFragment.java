package com.qiangshijituan.zzq_jianbao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.qiangshijituan.zzq_jianbao.R;

import static android.R.attr.name;
import static android.content.ContentValues.TAG;

/**
 * Created by 赵自强 on 2016/12/8.
 */

public class HomeFragment extends BaseFragment{
  

    @Override
    public View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.jianbao_homefragmen, null);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取bundle
        Bundle bundle = getArguments();
        bundle.getInt("name");
        Log.e(TAG, "onCreate: "+name );
    }
}


