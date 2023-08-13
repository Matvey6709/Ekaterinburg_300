package ru.n3studio.ekaterinburg300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    Button button3_log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button bac = findViewById(R.id.button_bac_log);
        button3_log = findViewById(R.id.button3_logs);
        button3_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(LoginActivity.this, ActivityMap.class);
                startActivity(intent2);
                overridePendingTransition(0, 0);
            }
        });
        bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, FirstScreen.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }
}