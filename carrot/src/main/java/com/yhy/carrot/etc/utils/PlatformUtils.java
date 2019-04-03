package com.yhy.carrot.etc.utils;

import android.os.Build;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2019-04-03 15:18
 * version: 1.0.0
 * desc   :
 */
public class PlatformUtils {

    private PlatformUtils() {
        throw new UnsupportedOperationException("Can not instantiate utils class.");
    }

    public static boolean hasICS() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }
}
