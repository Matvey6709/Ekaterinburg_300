package ru.n3studio.ekaterinburg300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SharedPreferences prefs;
    EditText login;
    EditText password;
    EditText password_second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = this.getSharedPreferences("theme", Context.MODE_PRIVATE);
        Intent intent = new Intent(this, FirstScreen.class);
        startActivity(intent);
//        if(!prefs.getBoolean("FirstScreen", false)){
//            Intent intent = new Intent(this, FirstScreen.class);
//            startActivity(intent);
//            prefs.edit().putBoolean("FirstScreen", true).apply();
//        }




    }
}