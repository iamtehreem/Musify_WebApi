package com.tehreemfatima.smd_assignment3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class screen2 extends AppCompatActivity {

    EditText etname,pass,etEmail;
    Button signup;

    String URL="http://192.168.100.10/signup/signin.php";
    String name,password,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

        etname=findViewById(R.id.etname);
        pass=findViewById(R.id.pass);
        etEmail=findViewById(R.id.etEmail);

        signup=findViewById(R.id.signup);
        name=password=email="";




    }

    public void signup(View view) {

        name=etname.getText().toString().trim();
        email=etEmail.getText().toString().trim();
        password=pass.getText().toString().trim();

        if(!name.equals("") && !email.equals("") && !password.equals("")){


            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                       signup.setClickable(false);
                    }
                    else if (response.equals("failed")) {
                        Toast.makeText(screen2.this, "Invalid email/Password", Toast.LENGTH_LONG).show();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.toString().trim(),Toast.LENGTH_LONG).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data=new HashMap<>();
                    data.put("name",name);
                    data.put("email",email);
                    data.put("password",password);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

        }

    }

    public void signin(View view) {

        Intent intent=new Intent(this,screen4.class);
        startActivity(intent);
        finish();
    }
}