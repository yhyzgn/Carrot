package com.yhy.carrot.listener;

import android.net.Uri;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2019-04-03 15:09
 * version: 1.0.0
 * desc   :
 */
public interface OnSelectedListener {

    void onSelected(@NonNull List<Uri> uriList, @NonNull List<String> pathList);
}
