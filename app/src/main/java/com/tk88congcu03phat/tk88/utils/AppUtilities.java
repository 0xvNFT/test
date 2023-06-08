package com.tk88congcu03phat.tk88.utils;

import com.tk88congcu03phat.tk88.R;
import com.tk88congcu03phat.tk88.data.model.ApplicationDefMod;
import com.tk88congcu03phat.tk88.data.model.ImageMain;

import java.util.ArrayList;
import java.util.List;

public class AppUtilities {

    public static List<ImageMain> imageMainList() {
        List<ImageMain> list = new ArrayList<>();
        list.add(new ImageMain(1, "Image 1", "image_1", R.drawable.image_1));
        list.add(new ImageMain(2, "Image 2", "image_2", R.drawable.image_2));
        list.add(new ImageMain(3, "Image 3", "image_3", R.drawable.image_3));
        list.add(new ImageMain(4, "Image 4", "image_4", R.drawable.image_4));
        list.add(new ImageMain(5, "Image 5", "image_5", R.drawable.image_5));
        list.add(new ImageMain(6, "Image 6", "image_6", R.drawable.image_6));
        return list;
    }

    public static List<ImageMain> imageColorList() {
        List<ImageMain> list = new ArrayList<>();
        list.add(new ImageMain(1, "Image 1", "image_1", R.drawable.image_main_1));
        list.add(new ImageMain(2, "Image 2", "image_2", R.drawable.image_main_2));
        list.add(new ImageMain(3, "Image 3", "image_3", R.drawable.image_main_3));
        list.add(new ImageMain(4, "Image 4", "image_4", R.drawable.image_main_4));
        list.add(new ImageMain(5, "Image 5", "image_5", R.drawable.image_main_5));
        list.add(new ImageMain(6, "Image 6", "image_6", R.drawable.image_main_6));
        return list;
    }

    public static void saveImageLike(ImageMain imageMain) {
        List<ImageMain> list = ApplicationDefMod.getImageLike();
        if (list == null) {
            list = new ArrayList<>();
        }
        for (ImageMain imageMain1 : list) {
            if (imageMain1.id == imageMain.id) {
                return;
            }
        }
        list.add(imageMain);
        ApplicationDefMod.saveImage(list);

    }
}
