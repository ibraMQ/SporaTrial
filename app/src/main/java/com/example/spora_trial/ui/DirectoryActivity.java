package com.example.spora_trial.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spora_trial.R;
import com.example.spora_trial.UserViewModel;
import com.example.spora_trial.adapter.UserAdapter;
import com.example.spora_trial.db.UserEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DirectoryActivity extends AppCompatActivity {
    ImageView imgUser;
    FloatingActionButton fab;
    RecyclerView recyclerUsers;
    UserAdapter userAdapter;
    UserViewModel mUserViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);

        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        imgUser=findViewById(R.id.imageView2);
        fab = findViewById(R.id.floatingActionButton);
        recyclerUsers=findViewById(R.id.contactList);
        recyclerUsers.setLayoutManager(new LinearLayoutManager(this));

        //get users from db
        List<UserEntity> usersDB = mUserViewModel.getAll();
        //set recycler adapter
        userAdapter = new UserAdapter(usersDB,this,mUserViewModel);
        recyclerUsers.setAdapter(userAdapter);

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
