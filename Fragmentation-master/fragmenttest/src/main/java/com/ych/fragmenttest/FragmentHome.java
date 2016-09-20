package com.ych.fragmenttest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by ych on 2016/8/31.
 */
public class FragmentHome extends SupportFragment {

    public static FragmentHome getInstance() {
        Bundle bundle = new Bundle();
        FragmentHome home = new FragmentHome();
        home.setArguments(bundle);
        return home;
    }

    Button button;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
        initView();
        return view;
    }


    private void initView() {
button= (Button) view.findViewById(R.id.btn1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(FragmentOne.getInstance());
            }
        });

    }
}