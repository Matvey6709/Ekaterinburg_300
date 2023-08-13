package ru.n3studio.ekaterinburg300;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

public class EventActivity extends AppCompatActivity {
    ImageButton map;

    ImageButton news;
    ImageButton akk;

    MapView mapView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapKitFactory.setApiKey("077d748a-e458-4642-a53e-d888274fbc49");
        MapKitFactory.initialize(this);
        setContentView(R.layout.activity_event);
        map = findViewById(R.id.map_2);
        mapView = (MapView)findViewById(R.id.mapview);
        mapView.getMap().move(
                new CameraPosition(new com.yandex.mapkit.geometry.Point(56.8519, 60.6122), 11.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null);
        ImageProvider pr = ImageProvider.fromResource(this, R.drawable.ic_pin);
        ImageProvider pr2 = ImageProvider.fromResource(this, R.drawable.is_red);
        PlacemarkMapObject placemark = mapView.getMap().getMapObjects().addPlacemark(new com.yandex.mapkit.geometry.Point(56.8529, 60.6122), pr);
        mapView.getMap().getMapObjects().addPlacemark(placemark.getGeometry(), pr);
        com.yandex.mapkit.geometry.Point mappoint= new com.yandex.mapkit.geometry.Point(56.8529, 60.6122);
        com.yandex.mapkit.geometry.Point mappoint2= new com.yandex.mapkit.geometry.Point(56.8509, 60.6112);
        com.yandex.mapkit.geometry.Point mappoint3= new com.yandex.mapkit.geometry.Point(56.8929, 60.6122);
        mapView.getMap().getMapObjects().addPlacemark(mappoint, pr);
        mapView.getMap().getMapObjects().addPlacemark(mappoint2, pr);
        mapView.getMap().getMapObjects().addPlacemark(mappoint3, pr2);



        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(EventActivity.this, ActivityMap.class);
                startActivity(intent1);
                overridePendingTransition(0, 0);
            }
        });

        akk = findViewById(R.id.akk_2);
        akk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(EventActivity.this, AkkActivity.class);
                startActivity(intent1);
                overridePendingTransition(0, 0);
            }
        });
        news = findViewById(R.id.news_2);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(EventActivity.this, NewsActivity.class);
                startActivity(intent1);
                overridePendingTransition(0, 0);
            }
        });
    }

    @Override
    protected void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }
}