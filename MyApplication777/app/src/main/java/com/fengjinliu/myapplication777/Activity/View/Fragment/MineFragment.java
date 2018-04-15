package com.fengjinliu.myapplication777.Activity.View.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fengjinliu.myapplication777.R;

public class MineFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup container, Bundle saveInstanceState) {
        return layoutInflater.inflate(R.layout.mine, container, false);
    }
}
