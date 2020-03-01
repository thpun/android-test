package com.example.thpun.networktesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text = findViewById(R.id.text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text.getText().toString().equals("Started")) {
                    text.setText("Stopped");
                    stopService(new Intent(MainActivity.this, service.class));
                } else {
                    text.setText("Started");
                    startService(new Intent(MainActivity.this, service.class));
                }
            }
        });
    }
}
