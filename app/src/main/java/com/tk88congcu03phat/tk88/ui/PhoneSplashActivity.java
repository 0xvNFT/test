package com.tk88congcu03phat.tk88.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.tk88congcu03phat.tk88.data.model.ApplicationDefMod;
import com.tk88congcu03phat.tk88.data.model.RespLoggerMod;
import com.tk88congcu03phat.tk88.databinding.ActivitySplashBinding;
import com.tk88congcu03phat.tk88.ui.phone.PhoneSupActivity;


public class PhoneSplashActivity extends AppCompatActivity {
//Progress bar
    private ActivitySplashBinding binding;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new Handler().postDelayed(() -> {
            RespLoggerMod item = ApplicationDefMod.getLogin();
//            if (item == null) {
//                startActivity(new Intent(this, SupportPhoneActivity.class));
//            } else {
//                startActivity(new Intent(this, ParentActivity.class));
//
//            }
            startActivity(new Intent(PhoneSplashActivity.this, PhoneSupActivity.class));
        }, 1000);



    }
}
