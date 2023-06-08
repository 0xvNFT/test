package com.tk88congcu03phat.tk88.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.tk88congcu03phat.tk88.data.model.ImageMain;
import com.tk88congcu03phat.tk88.databinding.FragmentHomeBinding;
import com.tk88congcu03phat.tk88.utils.AppUtilities;
import com.tk88congcu03phat.tk88.utils.Library;


public class HomeFrags extends Fragment {

    private FragmentHomeBinding binding;
    private ListedHomeImageAdapter adapter;

    public static ImageMain imageMainSelect;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeHomeViewModel homeHomeViewModel =
                new ViewModelProvider(this).get(HomeHomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

    }

    private void initView() {
        adapter = new ListedHomeImageAdapter();
        binding.rclMain.setAdapter(adapter);
        binding.rclMain.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
        adapter.clearData();
        adapter.addData(AppUtilities.imageMainList());
        adapter.setOnClick(item -> {
            imageMainSelect = item;
            ImageHomeActivity.colorCurrent = Color.BLUE;
            startActivity(new Intent(getActivity(), ImageHomeActivity.class));
        });
        Library.getInstance().setCurrentBook(0);

        Library.getInstance().setCurrentPage(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}