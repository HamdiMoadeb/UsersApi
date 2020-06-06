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

public class AddUserActivity extends AppCompatActivity {


    EditText first, last, phone;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        first = findViewById(R.id.firstAdd);
        last = findViewById(R.id.lastAdd);
        phone = findViewById(R.id.phoneAdd);
        addBtn = findViewById(R.id.btnAdd);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String firstname = first.getText().toString();
                String lastname = last.getText().toString();
                String phoneF = phone.getText().toString();

                JsonObject json = new JsonObject();
                json.addProperty("firstname", firstname);
                json.addProperty("lastname", lastname);
                json.addProperty("phone", phoneF);

                Ion.with(AddUserActivity.this)
                        .load("https://backend-people-crud-app.herokuapp.com/users/add")
                        .setJsonObjectBody(json)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                if(e == null){

                                    Intent intent = new Intent(AddUserActivity.this, UsersListActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });

            }
        });
    }
}
