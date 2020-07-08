package com.dtavana.parstagram.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.dtavana.parstagram.R;
import com.dtavana.parstagram.databinding.ActivityProfileBinding;
import com.dtavana.parstagram.models.User;
import com.dtavana.parstagram.utils.GlideApp;
import com.dtavana.parstagram.utils.NavigationUtils;
import com.parse.LogOutCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    public static final String TAG = ProfileActivity.class.getSimpleName();

    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.navigation.getRoot().setSelectedItemId(R.id.miProfile);
        NavigationUtils.setupNavigationBar(this, ProfileActivity.class, binding.navigation.getRoot());

        binding.tvUsername.setText(ParseUser.getCurrentUser().getUsername());
        ParseFile avatar = User.getAvatar();
        if(avatar != null) {
            GlideApp
                    .with(this)
                    .load(avatar.getUrl())
                    .into(binding.ivAvatar);
        }

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOutInBackground(new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {
                        Log.d(TAG, "done: Succesfully logged out");
                        Intent i = new Intent(ProfileActivity.this, LoginActivity.class);
                        startActivity(i);
                    }
                });
            }
        });
    }
}