package com.tk88congcu03phat.tk88.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tk88congcu03phat.tk88.databinding.ActivityImageBinding;

public class ImageActivity extends AppCompatActivity {

        private ActivityImageBinding binding;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityImageBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            binding.layoutHeader.imageBack.setOnClickListener(view -> onBackPressed());
            //binding.layoutHeader.imageBack.setOnClickListener(view -> finish());
            binding.layoutHeader.titleHeader.setText("MẶT SAU");
        }
}




