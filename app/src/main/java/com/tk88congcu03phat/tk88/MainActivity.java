package com.tk88congcu03phat.tk88;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tk88congcu03phat.tk88.databinding.ActivityMainBinding;
import com.tk88congcu03phat.tk88.ui.dashboard.DashFragments;
import com.tk88congcu03phat.tk88.ui.home.HomeFrags;
import com.tk88congcu03phat.tk88.ui.notifications.NotifsFragments;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private HomeFrags homeFrags;
    private DashFragments dashFragments;
    private NotifsFragments notifsFragments;
    private FragmentManager fragmentManager;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentManager = getSupportFragmentManager();
        homeFrags = new HomeFrags();
        dashFragments = new DashFragments();
        notifsFragments = new NotifsFragments();

        binding.navView.setOnNavigationItemSelectedListener(navItemSelectedListener);

        // Set the default fragment
        currentFragment = homeFrags;
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main, currentFragment).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = homeFrags;
                    break;
                case R.id.navigation_dashboard:
                    selectedFragment = dashFragments;
                    break;
                case R.id.navigation_notifications:
                    selectedFragment = notifsFragments;
                    break;
            }

            if (selectedFragment != null) {
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main, selectedFragment).commit();
                currentFragment = selectedFragment;
            }

            return true;
        }
    };

    @Override
    public void onBackPressed() {
        if (currentFragment instanceof HomeFrags) {
            // If the current fragment is HomeFrags, perform the default back button behavior
            super.onBackPressed();
        } else {
            // Navigate back to HomeFrags fragment
            binding.navView.setSelectedItemId(R.id.navigation_home);
        }
    }
}

//package com.tk88congcu03phat.tk88;
//
//        import android.os.Bundle;
//
//        import com.google.android.material.bottomnavigation.BottomNavigationView;
//
//        import androidx.appcompat.app.AppCompatActivity;
//        import androidx.fragment.app.Fragment;
//        import androidx.fragment.app.FragmentManager;
//
//        import com.tk88congcu03phat.tk88.databinding.ActivityMainBinding;
//        import com.tk88congcu03phat.tk88.databinding.ActivityImageBinding;
//        import com.tk88congcu03phat.tk88.ui.dashboard.DashFragments;
//        import com.tk88congcu03phat.tk88.ui.home.HomeFrags;
//        import com.tk88congcu03phat.tk88.ui.notifications.NotifsFragments;
//
//public class MainActivity extends AppCompatActivity {
//    private HomeFrags homeFrags;
//
//    private DashFragments dashFragments;
//
//    private NotifsFragments notifsFragments;
//    private ActivityMainBinding binding;
//
//    private Fragment fragmentMain;
//    private FragmentManager fragmentManager;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        homeFrags = new HomeFrags();
//        dashFragments = new DashFragments();
//        notifsFragments = new NotifsFragments();
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        fragmentManager = getSupportFragmentManager();
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        navView.setSelectedItemId(R.id.navigation_home);
//        loadFragment(homeFrags);
//        navView.setOnItemSelectedListener(item -> {
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    loadFragment(homeFrags);
//                    break;
//                case R.id.navigation_dashboard:
//                    loadFragment(dashFragments);
//                    break;
//                case R.id.navigation_notifications:
//                    loadFragment(notifsFragments);
//                    break;
//            }
//            return false;
//        });
//        binding.imageHome.setOnClickListener(view ->
//                loadFragment(homeFrags));
//
//    }
//
//    private void loadFragment(Fragment fragment) {
//        if (fragment.isAdded()) {
//            if (fragment.isVisible()) {
//                fragmentMain = fragment;
//                return;
//            }
//            fragmentManager.beginTransaction().hide(fragmentMain).show(fragment).commit();
//        } else {
//            if (fragmentMain != null) {
//                fragmentManager.beginTransaction().add(R.id.nav_host_fragment_activity_main, fragment).hide(fragmentMain).commit();
//            } else {
//                fragmentManager.beginTransaction().add(R.id.nav_host_fragment_activity_main, fragment).commit();
//            }
//        }
//        fragmentMain = fragment;
//    }
//
//}


