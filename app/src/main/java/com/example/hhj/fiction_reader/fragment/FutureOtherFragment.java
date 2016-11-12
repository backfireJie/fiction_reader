package com.example.hhj.fiction_reader.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hhj.fiction_reader.R;

/**
 * Created by backfire on 2016/10/22.
 */
public class FutureOtherFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.future_tab_item_other,container,false);
        return  view;
    }
}
