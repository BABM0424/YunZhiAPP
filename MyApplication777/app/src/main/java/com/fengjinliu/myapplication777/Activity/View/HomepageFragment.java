package com.fengjinliu.myapplication777.Activity.View;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.fengjinliu.myapplication777.R;

import java.util.HashMap;

public class HomepageFragment extends Fragment {

    private TextView textView1, textView2, textView3;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup container, Bundle saveInstanceState) {
        return layoutInflater.inflate(R.layout.main_homepage, container, false);
    }
}
