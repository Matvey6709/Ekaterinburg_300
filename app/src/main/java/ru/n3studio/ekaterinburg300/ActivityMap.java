package ru.n3studio.ekaterinburg300;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ActivityMap extends AppCompatActivity {
    MapView mapView;
    SharedPreferences prefs;
    ImageButton events;

    ImageButton news;
    ImageButton akk;

    Button take_photo;

    private static final int REQUEST_TAKE_PHOTO = 1;
    private ImageView imageView;

// в методе onCreate()



    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        MapKitFactory.setApiKey("077d748a-e458-4642-a53e-d888274fbc49");
//        MapKitFactory.initialize(this);
        // Укажите имя Activity вместо map.
        setContentView(R.layout.activity_map);
        prefs = this.getSharedPreferences("theme", Context.MODE_PRIVATE);

//
//
//        mapView = (MapView)findViewById(R.id.mapview);
//        mapView.getMap().move(
//                new CameraPosition(new Point(56.8519, 60.6122), 11.0f, 0.0f, 0.0f),
//                new Animation(Animation.Type.SMOOTH, 0),
//                null);
//        ImageProvider pr = ImageProvider.fromResource(this, R.drawable.baseline_map_24);
//        PlacemarkMapObject placemark = mapView.getMap().getMapObjects().addPlacemark(new Point(56.8529, 60.6122), pr);
//        mapView.getMap().getMapObjects().addPlacemark(new Point(56.8529, 60.6122), pr);
//        Point mappoint= new Point(56.8529, 60.6122);
//        mapView.getMap().getMapObjects().addPlacemark(mappoint);
//        placemark.addTapListener(new MapObjectTapListener() {
//            @Override
//            public boolean onMapObjectTap(@NonNull MapObject mapObject, @NonNull Point point) {
//                Point mappoint= new Point(point.getLatitude(), point.getLatitude());
//                mapView.getMap().getMapObjects().addPlacemark(mappoint);
//                System.out.println(point.getLatitude() + " " + point.getLatitude());
//                return false;
//            }
//        });
        events = findViewById(R.id.event_1);
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ActivityMap.this, EventActivity.class);
                startActivity(intent1);
                overridePendingTransition(0, 0);
            }
        });
        news = findViewById(R.id.news_1);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ActivityMap.this, NewsActivity.class);
                startActivity(intent1);
                overridePendingTransition(0, 0);
            }
        });
        akk = findViewById(R.id.akk_1);
        akk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ActivityMap.this, AkkActivity.class);
                startActivity(intent1);
                overridePendingTransition(0, 0);
            }
        });

        imageView = findViewById(R.id.imageView_result);
        take_photo = findViewById(R.id.take_photo);
        take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try{
                    startActivityForResult(takePhotoIntent, REQUEST_TAKE_PHOTO);
                }catch (ActivityNotFoundException e){
                    e.printStackTrace();
                }
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            // Фотка сделана, извлекаем миниатюру картинки
            Bundle extras = data.getExtras();
            Bitmap thumbnailBitmap = (Bitmap) extras.get("data");
            try {
                saveToInternalStorage(thumbnailBitmap);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//            imageView.setImageBitmap(thumbnailBitmap);

            Intent intent1 = new Intent(ActivityMap.this, ResultPhotoActivity.class);
            intent1.putExtra("BitmapImage", thumbnailBitmap);
            startActivity(intent1);
        }
    }
    private String saveToInternalStorage(Bitmap bitmapImage) throws IOException {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // путь /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Создаем imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Используем метод сжатия BitMap объекта для записи в OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fos.close();
        }
        return directory.getAbsolutePath();
    }




    @Override
    protected void onStop() {
//        mapView.onStop();
//        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        MapKitFactory.getInstance().onStart();
//        mapView.onStart();
    }
}