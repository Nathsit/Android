package com.example.loginpractica;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText email, contraseña;

    String str_email,str_password;
    String url = "https://practicau.000webhostapp.com/sesion.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.etemail);
        contraseña = findViewById(R.id.etcontraseña);


    }

    @SuppressLint("NotConstructor")
    public void Login(View view) {

        if(email.getText().toString().equals("")){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }
        else if(contraseña.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else{


            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Por favor espera...");

            progressDialog.show();

            str_email = email.getText().toString().trim();
            str_password = contraseña.getText().toString().trim();


            StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
                progressDialog.dismiss();

                if(response.equalsIgnoreCase("ingreso correctamente")){

                    email.setText("");
                    contraseña.setText("");
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                }

            }, error -> {
                progressDialog.dismiss();
                Toast.makeText(Login.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }

            ){
                @Override
                protected Map<String, String> getParams() {
                    Map<String,String> params = new HashMap<>();
                    params.put("email",str_email);
                    params.put("password",str_password);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
            requestQueue.add(request);




        }
    }

}