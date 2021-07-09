package com.example.parstagram;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;
import com.parse.ParseUser;

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

        //set IG logo in actionbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.nav_logo_whiteout);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("");
    }

    //inflate actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // comes into play when an item in the actionbar is clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // if the home icon is tapped, navigate to the feed activity
        if (item.getItemId() == R.id.homeFeed) {
            Intent i = new Intent(PostDetailsActivity.this, FeedActivity.class);
            startActivity(i);
        }

        // if the compose icon is tapped, navigate to the compose activity (aka main activity)
        if (item.getItemId() == R.id.compose) {
            Intent i = new Intent(PostDetailsActivity.this, MainActivity.class);
            startActivity(i);
        }

        // if the logout icon is tapped, log out + navigate to the login screen
        if (item.getItemId() == R.id.logout) {
            ParseUser.logOut();
            ParseUser currentUser = ParseUser.getCurrentUser();
            Intent i = new Intent(PostDetailsActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
