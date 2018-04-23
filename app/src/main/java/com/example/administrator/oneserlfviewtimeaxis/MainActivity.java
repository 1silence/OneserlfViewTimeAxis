package com.example.administrator.oneserlfviewtimeaxis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.oneserlfviewtimeaxis.util.TimeAxis;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<TempBean> list = new ArrayList<>();
    private TimeAxis tempTimeAxis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tempTimeAxis = findViewById(R.id.temp_time_axis);
        findViewById(R.id.but).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but:
                for (int i = 1; i < 20; i++) {
                    list.add(new TempBean("12" + i, i + "9"));
                }
                tempTimeAxis.updateList(list);
                break;
        }
    }
}
