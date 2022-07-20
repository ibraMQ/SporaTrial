package com.example.spora_trial;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;

public class RegisterActivity  extends AppCompatActivity{

    EditText edtMail, edtPhone, edtName, edtAdres, edtAge;
    Button btnSave;
    ImageView imgUser;
    Bitmap selectedImageBitmap;
    int SELECT_PICTURE = 200;
    // Patrón para validar el email
    Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtMail = findViewById(R.id.editTextTextEmailAddress);
        edtPhone = findViewById(R.id.editTextPhone);
        edtName = findViewById(R.id.editTextTextName);
        edtAdres = findViewById(R.id.editTextAdress);
        edtAge = findViewById(R.id.editTextAge);
        btnSave= findViewById(R.id.btnSave);
        imgUser=findViewById(R.id.imgSelect);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validacion de campos
                boolean val = true;
                if(selectedImageBitmap == null && val){
                    Toast.makeText(getApplicationContext(),"Debe seleccionar una imagen", Toast.LENGTH_SHORT).show();
                    val = false;
                }
                Matcher mather = pattern.matcher(edtMail.getText().toString());
                if(!mather.find() && val){
                    Toast.makeText(getApplicationContext(),"Ingrese un correo valido", Toast.LENGTH_SHORT).show();
                    val = false;
                }
                if(edtName.getText().toString().equals("") && val){
                    Toast.makeText(getApplicationContext(),"Complete el campo de Nombre", Toast.LENGTH_SHORT).show();
                    val = false;
                }
                if(edtPhone.getText().toString().length()<8 && val){
                    Toast.makeText(getApplicationContext(),"Ingrese un telefono valido (minimo 8 numeros)", Toast.LENGTH_SHORT).show();
                    val = false;
                }
                if(edtAdres.getText().toString().equals("") && val){
                    Toast.makeText(getApplicationContext(),"Ingrese una dirreccion valida", Toast.LENGTH_SHORT).show();
                    val = false;
                }
                if(edtAge.getText().toString().equals("") && val){
                    Toast.makeText(getApplicationContext(),"Ingrese su edad", Toast.LENGTH_SHORT).show();
                    val = false;
                }
                if (val){
                    Intent numbersIntent = new Intent(RegisterActivity.this, DirectoryActivity.class);
                    startActivity(numbersIntent);
                }
            }
        });
        //Seleccion de imagen de galeria
        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                launchActivity.launch(i);
            }
        });
    }
    //Actividad para obtencion de imagen de galeria
    ActivityResultLauncher<Intent> launchActivity
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null && data.getData() != null) {
                        Uri selectedImageUri = data.getData();
                        selectedImageBitmap = null;
                        try {
                            selectedImageBitmap
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        imgUser.setImageBitmap(selectedImageBitmap);
                    }
                }
            });
}
