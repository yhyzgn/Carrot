package com.yhy.carrot.etc.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2019-04-03 16:54
 * version: 1.0.0
 * desc   :
 */
public class CarrotUtils {

    /**
     * 获取当前APP的名称
     *
     * @return 当前APP的名称
     */
    public static String getAppName(Context ctx) {
        PackageManager pm = ctx.getPackageManager();
        ApplicationInfo ai = null;
        try {
            ai = pm.getApplicationInfo(ctx.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return (String) pm.getApplicationLabel(ai);
    }

    /**
     * 获取ApplicationId
     *
     * @return ApplicationId
     */
    public static String getApplicationId(Context ctx) {
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);

            String appId = null;
            if (applicationInfo != null) {
                appId = applicationInfo.packageName;
                if (TextUtils.isEmpty(appId)) {
                    appId = applicationInfo.processName;
                }
            }
            if (TextUtils.isEmpty(appId)) {
                appId = ctx.getPackageName();
            }
            return appId;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
