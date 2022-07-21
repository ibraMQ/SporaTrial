package com.example.spora_trial.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
    private int STORAGE_PERMISSION_CODE = 1;

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


        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Solicitud de permisos
                if(ContextCompat.checkSelfPermission(LoginActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    //
                    if(ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                        new AlertDialog.Builder(LoginActivity.this)
                                .setTitle("Permiso Denegado")
                                .setMessage("El permisso es requerido para el uso de la app")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        ActivityCompat.requestPermissions(LoginActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                                STORAGE_PERMISSION_CODE);
                                    }
                                })
                                .create().show();
                    }else{
                        ActivityCompat.requestPermissions(LoginActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                STORAGE_PERMISSION_CODE);
                    }
                }else {
                    //Obtencion de Usuario
                    List<UserEntity> log = mUserViewModel.getUser(txtMail.getText().toString());

                    if (log.isEmpty()) {
                        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                        startActivity(i);
                    } else {
                        //guardar ultimo usuario
                        SharedPreferences wpreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                        String user = txtMail.getText().toString();
                        SharedPreferences.Editor editor = wpreferences.edit();
                        editor.putString("mail", user);
                        editor.commit();

                        Intent i = new Intent(LoginActivity.this, DirectoryActivity.class);
                        i.putExtra("IMG", log.get(0).img);
                        startActivity(i);
                    }
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permisso concedido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permisso DENEGADO", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
