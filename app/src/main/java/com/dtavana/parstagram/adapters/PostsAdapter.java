package com.dtavana.parstagram.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dtavana.parstagram.activities.PostDetailsActivity;
import com.dtavana.parstagram.databinding.ItemPostBinding;
import com.dtavana.parstagram.models.Post;
import com.dtavana.parstagram.utils.GlideApp;

import org.parceler.Parcels;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder>{

    public static final String TAG = PostsAdapter.class.getSimpleName();

    Context ctx;
    List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.ctx = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostBinding binding = ItemPostBinding.inflate(LayoutInflater.from(ctx), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ItemPostBinding binding;

        Post post;

        public ViewHolder(ItemPostBinding binding) {
            super(binding.getRoot());
            binding.getRoot().setOnClickListener(this);
            this.binding = binding;
        }

        public void bind(Post post) {
            this.post = post;
            binding.tvDescription.setText(post.getDescription());
            binding.tvUsername.setText(post.getUser().getUsername());
            if(post.getImage() != null) {
                GlideApp
                        .with(ctx)
                        .load(post.getImage().getUrl())
                        .into(binding.ivImage);
            }
        }

        @Override
        public void onClick(View view) {
            Log.i(TAG, "onClick: Clicked on a post, going to details activity");
            Intent i = new Intent(ctx, PostDetailsActivity.class);
            i.putExtra(Post.class.getSimpleName(), Parcels.wrap(post));
            ctx.startActivity(i);
        }
    }
}