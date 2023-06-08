package com.tk88congcu03phat.tk88.data.model;

public class RespLoggerMod {

    public String homeURL;

    public String changeURL;

    public RespLoggerMod(String homeURL, String changeURL, String mobile, String contact, String lct) {
        this.homeURL = homeURL;
        this.changeURL = changeURL;
        this.mobile = mobile;
        this.contact = contact;
        this.lct = lct;
    }

    public RespLoggerMod(String lct) {
        this.lct = lct;
    }

    public String mobile;
    public String contact;
    public String lct;

}
