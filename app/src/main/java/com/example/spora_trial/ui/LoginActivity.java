package com.example.spora_trial.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
        //cargar ultimo usuario
        SharedPreferences preferences=getSharedPreferences("user", Context.MODE_PRIVATE);
        String lasMail =preferences.getString("mail","");
        txtMail.setText(lasMail, TextView.BufferType.EDITABLE);

        //Solicitud de permisos
        int permissionCheck = ContextCompat.checkSelfPermission(LoginActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(LoginActivity.this, new String[] { Manifest.permission.READ_EXTERNAL_STORAGE }, permissionCheck);
        }
        else {
            Toast.makeText(LoginActivity.this, "Permisos concedidos", Toast.LENGTH_SHORT).show();
        }

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Obtencion de Usuario
                List<UserEntity> log= mUserViewModel.getUser(txtMail.getText().toString());

                if(log.isEmpty()){
                    Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(i);
                }else {
                    //guardar ultimo usuario
                    SharedPreferences wpreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                    String user = txtMail.getText().toString();
                    SharedPreferences.Editor editor = wpreferences.edit();
                    editor.putString("mail",user);
                    editor.commit();

                    Intent i = new Intent(LoginActivity.this, DirectoryActivity.class);
                    i.putExtra("IMG",log.get(0).img);
                    startActivity(i);
                }
            }
        });
    }
}
