package com.szh.library;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.szh.library.views.CircleIndicator;
import com.szh.library.views.NinePagesPagination.MyGridViewAdapter;
import com.szh.library.views.NinePagesPagination.NinePagesPagination;
import com.szh.library.views.NinePagesPagination.RequestDataListener;
import com.szh.library.views.TestEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NinePagesPagination viewPager = findViewById(R.id.ninePages);
        List<List<TestEntity>> lists = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            List<TestEntity> list1 = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                TestEntity entity = new TestEntity();
                entity.setText("j=" + j + "/i=" + i);
                list1.add(entity);
            }
            lists.add(list1);
        }
        viewPager.setEveryRequest(false);
        viewPager.setPagingLoad(true);
        viewPager.setListener(new RequestDataListener<TestEntity>() {
            @Override
            public List<TestEntity> request() {
                List<TestEntity> changeList =new ArrayList<>();
                for (int i = 0; i < 9; i++) {
                    TestEntity testEntity = new TestEntity();
                    testEntity.setText("change" + i);
                    changeList.add(testEntity);
                }
                return changeList;
            }

            @Override
            public int gridViewColumn() {
                return 3;
            }

            @Override
            public View getView(LayoutInflater inflater, int position, View convertView, ViewGroup parent, List<TestEntity> list) {
                ViewHolder viewHolder  = null;
                if(convertView == null){
                    convertView = inflater.inflate(R.layout.item_gridview,parent,false);
                    viewHolder = new ViewHolder();
                    viewHolder.textView = convertView.findViewById(R.id.textview);
                    convertView.setTag(viewHolder);
                }else{
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                viewHolder.textView.setText(list.get(position).getText());
                return convertView;
            }
        });
        final CircleIndicator circleIndicator = findViewById(R.id.circleIndicator);
        circleIndicator.setCircleTotal(lists.size());
        viewPager.setPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                circleIndicator.setPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setList(lists);


    }

    private void testScheme() {
        Button button = findViewById(R.id.button);
        Uri uri = getIntent().getData();
        if (uri != null) {
            System.out.println("uri=" + uri.toString());
            System.out.println("scheme=" + uri.getScheme());
            System.out.println("query=" + uri.getQuery());
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("zjmarket://");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    class ViewHolder{
        private TextView textView;
    }

}
