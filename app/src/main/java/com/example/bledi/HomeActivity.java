package com.example.bledi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.provider.MediaStore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.ImageView;




public class HomeActivity extends AppCompatActivity {
    Button btnCamera ;

     ImageView imageView ;
    private static int SPLASH_TIME_OUT =0000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imageView = (ImageView) findViewById(R.id.imageView);


        btnCamera = (Button) findViewById(R.id.btnCamera);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });






    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode , resultCode , data);
        Bitmap bitmap =(Bitmap)data.getExtras().get("data");

        imageView.setImageBitmap(bitmap);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(HomeActivity.this, LastActivity.class);







                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);


    }





    }













