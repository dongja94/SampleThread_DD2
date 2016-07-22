package com.begentgroup.samplethread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CountDownActivity extends AppCompatActivity {

    TextView countView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        countView = (TextView)findViewById(R.id.text_count);
        Button btn = (Button)findViewById(R.id.btn_start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                count = 10;
                startTime = -1;
                mHandler.removeCallbacks(downRunnable);
                mHandler.post(downRunnable);
            }
        });
    }

    int count = 10;
    Handler mHandler = new Handler(Looper.getMainLooper());

    long startTime = -1;

    Runnable downRunnable = new Runnable() {
        @Override
        public void run() {
//            if (count >= 0) {
//                countView.setText("count : " + count);
//                count--;
//                mHandler.postDelayed(this, 1000);
//            } else {
//                countView.setText("done");
//            }
            long time = SystemClock.elapsedRealtime();
            if (startTime == -1) {
                startTime = time;
            }
            int gap = (int)(time - startTime);
            int count = 10 - gap / 1000;
            int rest = 1000 - (gap % 1000);
            if (count >= 0) {
                countView.setText("count : " + count);
                mHandler.postDelayed(this, rest);
            } else {
                countView.setText("done");
            }
        }
    };
}
