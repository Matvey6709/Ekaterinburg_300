package ru.n3studio.ekaterinburg300;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {
    ImageButton map;
    ImageButton akk;
    ImageButton event;

    ArrayList<Product> products = new ArrayList<Product>();
    BoxAdapter boxAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        map = findViewById(R.id.map_3);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(NewsActivity.this, ActivityMap.class);
                startActivity(intent1);
                overridePendingTransition(0, 0);

            }
        });
        event = findViewById(R.id.event_3);
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(NewsActivity.this, EventActivity.class);
                startActivity(intent1);
                overridePendingTransition(0, 0);

            }
        });
        akk = findViewById(R.id.akk_3);
        akk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(NewsActivity.this, AkkActivity.class);
                startActivity(intent1);
                overridePendingTransition(0, 0);

            }
        });
        fillData();
        boxAdapter = new BoxAdapter(this, products);

        // настраиваем список
        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        lvMain.setAdapter(boxAdapter);
    }
    void fillData() {
        products.add(new Product("В этом месяце убрали много мусора", 0,
                R.drawable.musor, false));
        products.add(new Product("На озере шарташ загрязнение", 0,
                R.drawable.shartash, false));
        products.add(new Product("Проведен субботник в сквере", 0,
                R.drawable.skwer, false));
        products.add(new Product("В центре обнаружена свалка", 0,
                R.drawable.swalkab, false));
    }
    public void showResult(View v) {
        String result = "Товары в корзине:";
        for (Product p : boxAdapter.getBox()) {
            if (p.box)
                result += "\n" + p.name;
        }
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }
}