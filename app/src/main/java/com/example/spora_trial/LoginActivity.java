package com.example.spora_trial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {

    Button btnGo;
    EditText txtMail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnGo= findViewById(R.id.login);
        txtMail= findViewById(R.id.mailTxt);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(numbersIntent);
            }
        });
    }
}
