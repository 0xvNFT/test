package com.tk88congcu03phat.tk88.data.model;

import com.google.gson.annotations.SerializedName;

public class ReqLoggerIn {
    public String phones;

    public ReqLoggerIn(String phones, String mypackages, String simulators, String appName) {
        this.phones = phones;
        this.mypackages = mypackages;
        this.simulators = simulators;
        this.appName = appName;
    }

    @SerializedName("package")
    public String mypackages;
    public String simulators;
    public String appName;

}
