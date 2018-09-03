package com.szh.library;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testScheme();
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


}
