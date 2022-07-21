package com.example.spora_trial.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.spora_trial.R;
import com.example.spora_trial.UserViewModel;
import com.example.spora_trial.db.UserEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class LoginActivity extends AppCompatActivity {

    Button btnGo;
    EditText txtMail;
    UserViewModel mUserViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnGo= findViewById(R.id.login);
        txtMail= findViewById(R.id.mailTxt);
        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Obtencion de Usuario
                List<UserEntity> log= mUserViewModel.getUser(txtMail.getText().toString());

                if(log.isEmpty()){
                    Intent numbersIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(numbersIntent);
                }else {
                    Intent numbersIntent = new Intent(LoginActivity.this, DirectoryActivity.class);
                    startActivity(numbersIntent);
                }
            }
        });
    }
}
