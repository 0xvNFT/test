package com.tk88congcu03phat.tk88.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.tk88congcu03phat.tk88.databinding.FragmentNotificationsBinding;
import com.tk88congcu03phat.tk88.ui.home.ListedHomeImageAdapter;
import com.tk88congcu03phat.tk88.utils.AppUtilities;



public class NotifsFragments extends Fragment {
    private FragmentNotificationsBinding binding;
    private ListedHomeImageAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModels notificationsViewModels =
                new ViewModelProvider(this).get(NotificationsViewModels.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

           return root;
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
        adapter.addData(AppUtilities.imageColorList());
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}