package com.example.neeru.architecturecomponent.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.neeru.architecturecomponent.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity implements SplashNavigator {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openMainActivity();
    }

    /**
     * decide, which screen will be next
     */
    @Override
    public void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
