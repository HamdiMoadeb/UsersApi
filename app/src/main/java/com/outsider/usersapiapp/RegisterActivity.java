package com.outsider.usersapiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class RegisterActivity extends AppCompatActivity {

    EditText first, last, email, phone, password, repeatpass;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        first = findViewById(R.id.firstRegister);
        last = findViewById(R.id.lastRegister);
        email = findViewById(R.id.emailRegister);
        phone = findViewById(R.id.phoneRegister);
        password = findViewById(R.id.passwordRegister);
        repeatpass = findViewById(R.id.repeatPass);
        btnRegister = findViewById(R.id.registerBtn);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //password.setError("Check your password");

                String firstname = first.getText().toString();
                String lastname = last.getText().toString();
                String emailF = email.getText().toString();
                String phoneF = phone.getText().toString();
                String passwd = password.getText().toString();

                JsonObject json = new JsonObject();
                json.addProperty("firstname", firstname);
                json.addProperty("lastname", lastname);
                json.addProperty("email", emailF);
                json.addProperty("phone", phoneF);
                json.addProperty("password", passwd);

                Ion.with(RegisterActivity.this)
                        .load("https://backend-people-crud-app.herokuapp.com/users/register")
                        .setJsonObjectBody(json)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                if(e == null){

                                    Toast.makeText(RegisterActivity.this, ""+result, Toast.LENGTH_LONG).show();

                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });
            }
        });
    }
}
