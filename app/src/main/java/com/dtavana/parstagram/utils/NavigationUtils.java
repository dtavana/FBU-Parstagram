package com.dtavana.parstagram.utils;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.dtavana.parstagram.R;
import com.dtavana.parstagram.activities.MainActivity;
import com.dtavana.parstagram.activities.PostActivity;
import com.dtavana.parstagram.activities.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationUtils {
    public static void setupNavigationBar(final Context ctx, final Class currentActivity, BottomNavigationView nav) {
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Class destinationActivity;
                switch (item.getItemId()) {
                    case R.id.miHome:
                        destinationActivity = MainActivity.class;
                        break;
                    case R.id.miPost:
                        destinationActivity = PostActivity.class;
                        break;
                    case R.id.miProfile:
                        destinationActivity = ProfileActivity.class;
                        break;
                    default:
                        destinationActivity = null;
                }
                if(destinationActivity != null && destinationActivity.equals(currentActivity)) {
                    return true;
                }
                Intent i = new Intent(ctx, destinationActivity);
                ctx.startActivity(i);
                return true;
            }
        });
    }
}
