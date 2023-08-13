package ru.n3studio.ekaterinburg300;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AkkActivity extends AppCompatActivity {

    ImageButton map;
    ImageButton news;
    ImageButton events;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akk);
        map = findViewById(R.id.map_4);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(AkkActivity.this, ActivityMap.class);
                startActivity(intent1);
                overridePendingTransition(0, 0);

            }
        });
        events = findViewById(R.id.event_4);
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(AkkActivity.this, EventActivity.class);
                startActivity(intent1);
                overridePendingTransition(0, 0);

            }
        });
        news = findViewById(R.id.news_4);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(AkkActivity.this, NewsActivity.class);
                startActivity(intent1);
                overridePendingTransition(0, 0);
            }
        });

    }
}