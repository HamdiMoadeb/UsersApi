package com.outsider.usersapiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.Builders;

import java.util.ArrayList;

public class UsersListActivity extends AppCompatActivity {

    ListView userListV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        userListV = findViewById(R.id.userlistv);

        Ion.with(UsersListActivity.this)
                .load("https://backend-people-crud-app.herokuapp.com/users")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {

                        if(e == null){

                            ArrayList<User> userArrayList = new ArrayList<>();
                            for(int i = 0; i<result.size(); i++){

                                JsonObject userObject = result.get(i).getAsJsonObject();

                                User user = new User();
                                user.setEmail(userObject.get("email").getAsString());
                                user.setFirstname(userObject.get("firstname").getAsString());
                                user.setLastname(userObject.get("lastname").getAsString());
                                user.setPhone(userObject.get("phone").getAsString());
                                user.setId(userObject.get("_id").getAsString());

                                userArrayList.add(user);
                            }

                            UsersAdapter usersAdapter = new UsersAdapter(
                                    UsersListActivity.this,
                                    R.layout.user_item,
                                    userArrayList
                            );

                            userListV.setAdapter(usersAdapter);

                        }
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.add_user_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.addUserId){
            Intent intent = new Intent(UsersListActivity.this, AddUserActivity.class);
            startActivity(intent);
        }
        return true;
    }
}
