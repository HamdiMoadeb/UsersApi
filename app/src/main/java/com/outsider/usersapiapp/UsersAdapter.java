package com.outsider.usersapiapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UsersAdapter extends ArrayAdapter<User> {

    Context ctx;
    int res;
    public UsersAdapter(Context context, int resource,  ArrayList<User> objects) {
        super(context, resource, objects);
        this.ctx = context;
        this.res = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(ctx).inflate(res,parent, false);

        TextView name = convertView.findViewById(R.id.nameId);
        TextView email = convertView.findViewById(R.id.emailId);

        User user = getItem(position);
        name.setText(user.getFirstname()+ ' ' +user.getLastname());
        email.setText(user.getEmail());

        return convertView;
    }
}
