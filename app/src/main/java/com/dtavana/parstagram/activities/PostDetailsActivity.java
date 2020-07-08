package com.dtavana.parstagram.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dtavana.parstagram.databinding.ActivityPostDetailsBinding;
import com.dtavana.parstagram.models.Post;
import com.dtavana.parstagram.utils.GlideApp;

import org.parceler.Parcels;

public class PostDetailsActivity extends AppCompatActivity {

    ActivityPostDetailsBinding binding;

    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        post = Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));

        assert post != null;
        binding.tvDescription.setText(post.getDescription());
        binding.tvUsername.setText(post.getUser().getUsername());
        binding.tvTimestamp.setText(post.getRelativeTimeAgo());
        if(post.getImage() != null) {
            GlideApp
                    .with(this)
                    .load(post.getImage().getUrl())
                    .into(binding.ivImage);
        }
    }
}