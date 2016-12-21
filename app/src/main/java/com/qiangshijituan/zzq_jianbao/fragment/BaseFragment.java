package com.qiangshijituan.zzq_jianbao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiangshijituan.zzq_jianbao.R;

/**
 * Created by 赵自强 on 2016/12/9.
 */

public abstract class BaseFragment extends Fragment {
    // 此变量为页面的状态
    int fragment_status = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    public abstract View initView();

    public abstract void initData();

    public View setContent(int i) {
        View v = null ;
        switch (i) {
            case 0:

                v= LayoutInflater.from(getActivity()).inflate(R.layout.status_blank, null);
                break;

            case 1:

                v = LayoutInflater.from(getActivity()).inflate(R.layout.status_failure, null);
                break;
        }
       return v;
    }


}
