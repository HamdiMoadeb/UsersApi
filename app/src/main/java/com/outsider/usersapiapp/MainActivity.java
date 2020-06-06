package com.outsider.usersapiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class MainActivity extends AppCompatActivity {

    Button btnlogin;
    EditText email, password;
    TextView createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnlogin = findViewById(R.id.btnLogin);
        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwoedLogin);
        createAccount = findViewById(R.id.createAccount);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailF = email.getText().toString();
                String passwd = password.getText().toString();

                JsonObject json = new JsonObject();
                json.addProperty("email", emailF);
                json.addProperty("password", passwd);

                Ion.with(MainActivity.this)
                        .load("https://backend-people-crud-app.herokuapp.com/users/login")
                        .setJsonObjectBody(json)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                if(e == null){

                                   Intent intent = new Intent(MainActivity.this, UsersListActivity.class);
                                   startActivity(intent);

                                }
                            }
                        });
            }
        });

    }
}
