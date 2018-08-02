package com.szh.library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.widget.Button;

import com.szh.library.views.barrage.BarrageItemEntity;
import com.szh.library.views.barrage.BarrageLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button =findViewById(R.id.button);
        final BarrageLayout barrageLayout = findViewById(R.id.barrageLayout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BarrageItemEntity entity = new BarrageItemEntity();
                entity.setText("测试啊啊啊啊啊啊啊啊啊啊啊啊");
                entity.setTextSize(20);
                entity.setTextColor(getResources().getColor(R.color.colorAccent));
                entity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                barrageLayout.createBarrageItemView(entity);
            }
        });
    }
}
