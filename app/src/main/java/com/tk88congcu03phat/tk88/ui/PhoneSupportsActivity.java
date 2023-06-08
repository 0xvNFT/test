package com.tk88congcu03phat.tk88.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tk88congcu03phat.tk88.databinding.ActivitySupportBinding;

public class PhoneSupportsActivity extends AppCompatActivity {
    private ActivitySupportBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySupportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.layoutHeader.imageBack.setOnClickListener(view -> finish());
        binding.layoutHeader.titleHeader.setText("LIÊN HỆ");

        binding.layoutFb.setOnClickListener(view ->
                openWed("https://m.facebook.com/"));

        binding.layoutZalo.setOnClickListener(view ->
                openWed("https://zalo.me/pc"));

        binding.layoutTelegram.setOnClickListener(view ->
                openWed("https://web.telegram.org/"));

        binding.layoutMessenger.setOnClickListener(view ->
                openWed("https://www.messenger.com/"));
    }

    private void openWed(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
