package com.tk88congcu03phat.tk88;

import android.app.Application;

import com.orhanobut.hawk.Hawk;
import com.tk88congcu03phat.tk88.utils.Library;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
        Library.initialize(this);

    }
}
