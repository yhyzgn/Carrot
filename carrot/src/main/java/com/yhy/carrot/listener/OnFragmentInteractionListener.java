package com.yhy.carrot.listener;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2019-04-03 15:10
 * version: 1.0.0
 * desc   : PreViewItemFragment 和  BasePreViewActivity 通信的接口 ，为了方便拿到 ImageViewTouch 的点击事件
 */
public interface OnFragmentInteractionListener {

    /**
     * ImageViewTouch 被点击了
     */
    void onClick();
}
