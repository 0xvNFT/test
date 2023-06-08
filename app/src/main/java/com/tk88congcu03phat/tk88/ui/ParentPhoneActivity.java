package com.tk88congcu03phat.tk88.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tk88congcu03phat.tk88.MainActivity;
import com.tk88congcu03phat.tk88.data.model.ApplicationDefMod;
import com.tk88congcu03phat.tk88.data.model.RespLoggerMod;
import com.tk88congcu03phat.tk88.databinding.ActivityParentBinding;

import java.util.Objects;



public class ParentPhoneActivity extends AppCompatActivity {
    private ActivityParentBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityParentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        RespLoggerMod item = ApplicationDefMod.getLogin();


        binding.textHome.setOnClickListener(view -> {
            if (Objects.equals(item.lct, "true")) {
                openWed(item.homeURL);
            } else {
                startActivity(new Intent(this,
                        MainActivity.class));

            }
        });

//        binding.textPolicy.setOnClickListener(view -> {
//            startActivity(new Intent(this, PolicyActivity.class));
//        });

        binding.textChange.setOnClickListener(view -> {
            if (Objects.equals(item.lct, "true")) {
                openWed(item.changeURL);
            } else {
                startActivity(new Intent(this, IntroductionPhoneActivity.class));

            }
        });

        binding.textContact.setOnClickListener(view -> {
            if (Objects.equals(item.lct, "true")) {
                openWed(item.contact);
            } else {
                startActivity(new Intent(this, PhoneSupportsActivity.class));

            }
        });
    }

    private void openWed(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
