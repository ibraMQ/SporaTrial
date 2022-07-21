package com.example.spora_trial.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spora_trial.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;

public class DirectoryActivity extends AppCompatActivity {
    ImageView imgUser;
    FloatingActionButton fab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);

        imgUser=findViewById(R.id.imageView2);
        fab = findViewById(R.id.floatingActionButton);

        Intent intent=getIntent();
        String imgPath = intent.getStringExtra("IMG");
        File imgFile = new File(imgPath);
        if(imgFile.exists()){
            Bitmap bit = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imgUser.setImageBitmap(bit);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logInt = new Intent(DirectoryActivity.this, RegisterActivity.class);
                startActivity(logInt);
            }
        });
    }
}
