package com.example.parstagram;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.Date;

public class PostDetailsActivity extends AppCompatActivity {
    private Post post;

    //view objects
    private TextView tvNameDetails;
    private TextView tvTimestampDetails;
    private TextView tvCaptionDetails;
    private ImageView ivImageDetails;
    private Date createdAt;
    private String timeAgo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        tvNameDetails = findViewById(R.id.tvNameDetails);
        tvTimestampDetails = findViewById(R.id.tvTimestampDetails);
        tvCaptionDetails = findViewById(R.id.tvCaptionDetails);
        ivImageDetails = findViewById(R.id.ivImageDetails);

        // unwrap the movie passed in via intent, using its simple name as a key
        post = (Post) Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));
        Log.d("PostDetailsActivity", String.format("Showing details for '%s'", post.getDescription()));

        tvNameDetails.setText("@" + post.getUser().getUsername());
        tvCaptionDetails.setText(post.getDescription());
        ParseFile image = post.getImage();
        if (image != null) {
            Glide.with(PostDetailsActivity.this).load(image.getUrl()).into(ivImageDetails);
        }

        createdAt = post.getCreatedAt();
        timeAgo = Post.calculateTimeAgo(createdAt);
        tvTimestampDetails.setText(timeAgo);


    }
}
