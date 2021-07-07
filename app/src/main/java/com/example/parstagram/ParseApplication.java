package com.example.parstagram;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("kBiR9O22JABC9QPcFhsnSJcFCCtjctmfQlZ600K4")
                .clientKey("4JfYj1zs6bvcJMTC5XQE9bIbGXwIQursUfuNw6LD")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
