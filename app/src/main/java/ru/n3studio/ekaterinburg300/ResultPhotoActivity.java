package ru.n3studio.ekaterinburg300;

import static com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Predicates.equalTo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.ClientProtocolException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.entity.UrlEncodedFormEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpPost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultPhotoActivity extends AppCompatActivity {

    ImageView result;
    ImageView bac;
    SharedPreferences prefs;

    Button send;

    ImageView min;
    ImageView sr;
    ImageView max;

    LinearLayout water;
    boolean waters = false;
    ImageView water2;
    LinearLayout shestr;
    boolean shestrs = false;
    ImageView shestr2;
    LinearLayout batle;
    boolean batles = false;
    ImageView batle2;

    LinearLayout block;
    boolean blocks = false;
    ImageView block2;
    LinearLayout home;
    boolean homes = false;
    ImageView home2;
    LinearLayout wheel;
    boolean wheels = false;
    ImageView wheel2;

    int length = -1;

    EditText text;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_photo);
        prefs = this.getSharedPreferences("theme", Context.MODE_PRIVATE);
        result = findViewById(R.id.imageView_result);
        bac = findViewById(R.id.back);
        bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ResultPhotoActivity.this, ActivityMap.class);
                startActivity(intent1);
            }
        });
        Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("BitmapImage");
        result.setImageBitmap(bitmap);
        text = findViewById(R.id.text);

        send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text.getText().length() < 1){
                    Toast.makeText(getApplicationContext(),"Вы не ввели подробную информацию о происшествии", Toast.LENGTH_SHORT).show();
                }
                else {
                    text.setText("");
                    Toast.makeText(getApplicationContext(), "Отправлено на модерацию", Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(ResultPhotoActivity.this, ActivityMap.class);
                    startActivity(intent1);
                }
        }});
        min = findViewById(R.id.mini);
        sr = findViewById(R.id.sr);
        max = findViewById(R.id.big);
        min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                min.setBackgroundResource(R.drawable.corner);
                sr.setBackgroundResource(R.color.white);
                max.setBackgroundResource(R.color.white);
            }
        });
        sr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sr.setBackgroundResource(R.drawable.corner);
                min.setBackgroundResource(R.color.white);
                max.setBackgroundResource(R.color.white);
            }
        });
        max.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                max.setBackgroundResource(R.drawable.corner);
                sr.setBackgroundResource(R.color.white);
                min.setBackgroundResource(R.color.white);
            }
        });
        water = findViewById(R.id.water);
        water2 = findViewById(R.id.water2);
        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!waters){
                    waters = true;
                    water2.setBackgroundResource(R.drawable.corner);
                }
                else {
                    waters = false;
                    water2.setBackgroundResource(R.color.white);
                }
            }
        });
        shestr = findViewById(R.id.shestr);
        shestr2 = findViewById(R.id.shestr2);
        shestr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!shestrs){
                    shestrs = true;
                    shestr2.setBackgroundResource(R.drawable.corner);
                }
                else {
                    shestrs = false;
                    shestr2.setBackgroundResource(R.color.white);
                }
            }
        });
        batle = findViewById(R.id.batle);
        batle2 = findViewById(R.id.batle2);
        batle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!batles){
                    batles = true;
                    batle2.setBackgroundResource(R.drawable.corner);
                }
                else {
                    batles = false;
                    batle2.setBackgroundResource(R.color.white);
                }
            }
        });
        block = findViewById(R.id.block);
        block2 = findViewById(R.id.block2);
        block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!blocks){
                    blocks = true;
                    block2.setBackgroundResource(R.drawable.corner);
                }
                else {
                    blocks = false;
                    block2.setBackgroundResource(R.color.white);
                }
            }
        });
        home = findViewById(R.id.home);
        home2 = findViewById(R.id.home2);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!homes){
                    homes = true;
                    home2.setBackgroundResource(R.drawable.corner);
                }
                else {
                    homes = false;
                    home2.setBackgroundResource(R.color.white);
                }
            }
        });
        wheel = findViewById(R.id.wheel);
        wheel2 = findViewById(R.id.wheel2);
        wheel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!wheels){
                    wheels = true;
                    wheel2.setBackgroundResource(R.drawable.corner);
                }
                else {
                    wheels = false;
                    wheel2.setBackgroundResource(R.color.white);
                }
            }
        });


}



}



