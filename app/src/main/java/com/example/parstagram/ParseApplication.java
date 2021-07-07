package com.example.parstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //register parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("kBiR9O22JABC9QPcFhsnSJcFCCtjctmfQlZ600K4")
                .clientKey("4JfYj1zs6bvcJMTC5XQE9bIbGXwIQursUfuNw6LD")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
