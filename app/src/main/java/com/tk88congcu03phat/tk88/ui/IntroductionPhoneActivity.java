package com.tk88congcu03phat.tk88.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tk88congcu03phat.tk88.databinding.ActivityIntroduceBinding;

public class IntroductionPhoneActivity extends AppCompatActivity {
    private ActivityIntroduceBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroduceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.layoutHeader.imageBack.setOnClickListener(view -> finish());
        binding.layoutHeader.titleHeader.setText("GIỚI THIỆU");
    }
}
