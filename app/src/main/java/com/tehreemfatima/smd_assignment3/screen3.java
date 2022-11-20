package com.tehreemfatima.smd_assignment3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

public class screen3 extends AppCompatActivity {

    EditText Email,Password;
    String stEmail,stPassword;
    String URL="http://192.168.100.10/signup/login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);

        Email=findViewById(R.id.Email);
        Password=findViewById(R.id.Password);
        stEmail="";
        stPassword="";


    }

    public void signin(View view) {
        stEmail=Email.getText().toString().trim();
        stPassword=Password.getText().toString().trim();
        if(!stEmail.equals("") && !stPassword.equals("")){

            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Intent intent = new Intent(screen3.this, screen4.class);
                        startActivity(intent);
                        finish();
                    } else if (response.equals("failed")) {
                        Toast.makeText(screen3.this, "Invalid email/Password", Toast.LENGTH_LONG).show();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(screen3.this,error.toString().trim(),Toast.LENGTH_LONG).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data=new HashMap<>();
                    data.put("email",stEmail);
                    data.put("password",stPassword);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
        else{
            Toast.makeText(this,"Fields cant be empty!",Toast.LENGTH_LONG).show();
        }
    }



    public void signup(View view) {

        Intent intent=new Intent(this,screen4.class);
        startActivity(intent);
        finish();

    }
}