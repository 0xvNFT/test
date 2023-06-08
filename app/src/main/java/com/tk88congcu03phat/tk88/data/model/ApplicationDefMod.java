package com.tk88congcu03phat.tk88.data.model;

import com.orhanobut.hawk.Hawk;

import java.util.List;

public class ApplicationDefMod {

    public static String phones = "LOGIN";

    public static String likes = "LIKE";

    public static void saveLogin(RespLoggerMod item) {
        Hawk.put(phones, item);
    }

    public static RespLoggerMod getLogin() {
        return Hawk.get(phones);
    }

    public static List<ImageMain> getImageLike() {
        return Hawk.get(likes);
    }

    public static void saveImage(List<ImageMain> list) {
        Hawk.put(likes, list);
    }

}
