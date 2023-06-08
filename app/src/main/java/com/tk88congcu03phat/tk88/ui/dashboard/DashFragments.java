package com.tk88congcu03phat.tk88.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.tk88congcu03phat.tk88.data.model.ApplicationDefMod;
import com.tk88congcu03phat.tk88.data.model.MainEventsMod;
import com.tk88congcu03phat.tk88.data.model.ImageMain;
import com.tk88congcu03phat.tk88.databinding.FragmentDashboardBinding;
import com.tk88congcu03phat.tk88.ui.home.ListedHomeImageAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;



public class DashFragments extends Fragment {

    private FragmentDashboardBinding binding;
    private ListedHomeImageAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashViewUIModel dashViewUIModel =
                new ViewModelProvider(this).get(DashViewUIModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
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
        List<ImageMain> list = ApplicationDefMod.getImageLike();
        if (list==null) list = new ArrayList<>();
        adapter.addData(list);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Object event) {
        if (event instanceof MainEventsMod) {
            adapter.clearData();
            List<ImageMain> list = ApplicationDefMod.getImageLike();
            if (list==null) list = new ArrayList<>();
            adapter.addData(list);
        }
    }
}