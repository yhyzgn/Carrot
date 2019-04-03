package com.yhy.carrot;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.yhy.carrot.etc.entity.CaptureStrategy;
import com.yhy.carrot.etc.utils.CarrotUtils;
import com.yhy.carrot.media.MediaType;
import com.yhy.carrot.media.SelectionCreator;
import com.yhy.carrot.ui.CarrotActivity;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Set;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2019-04-03 14:55
 * version: 1.0.0
 * desc   :
 */
public final class Carrot {

    private final WeakReference<Activity> mContext;
    private final WeakReference<Fragment> mFragment;

    private Carrot(Activity activity) {
        this(activity, null);
    }

    private Carrot(Fragment fragment) {
        this(fragment.getActivity(), fragment);
    }

    private Carrot(Activity activity, Fragment fragment) {
        mContext = new WeakReference<>(activity);
        mFragment = new WeakReference<>(fragment);
    }

    /**
     * Start Matisse from an Activity.
     * <p>
     * This Activity's {@link Activity#onActivityResult(int, int, Intent)} will be called when user
     * finishes selecting.
     *
     * @param activity Activity instance.
     * @return Matisse instance.
     */
    public static Carrot from(Activity activity) {
        return new Carrot(activity);
    }

    /**
     * Start Matisse from a Fragment.
     * <p>
     * This Fragment's {@link Fragment#onActivityResult(int, int, Intent)} will be called when user
     * finishes selecting.
     *
     * @param fragment Fragment instance.
     * @return Matisse instance.
     */
    public static Carrot from(Fragment fragment) {
        return new Carrot(fragment);
    }

    /**
     * Obtain user selected media' {@link Uri} list in the starting Activity or Fragment.
     *
     * @param data Intent passed by {@link Activity#onActivityResult(int, int, Intent)} or
     *             {@link Fragment#onActivityResult(int, int, Intent)}.
     * @return User selected media' {@link Uri} list.
     */
    public static List<Uri> obtainResult(Intent data) {
        return data.getParcelableArrayListExtra(CarrotActivity.EXTRA_RESULT_SELECTION);
    }

    /**
     * Obtain user selected media path list in the starting Activity or Fragment.
     *
     * @param data Intent passed by {@link Activity#onActivityResult(int, int, Intent)} or
     *             {@link Fragment#onActivityResult(int, int, Intent)}.
     * @return User selected media path list.
     */
    public static List<String> obtainPathResult(Intent data) {
        return data.getStringArrayListExtra(CarrotActivity.EXTRA_RESULT_SELECTION_PATH);
    }

    /**
     * Obtain state whether user decide to use selected media in original
     *
     * @param data Intent passed by {@link Activity#onActivityResult(int, int, Intent)} or
     *             {@link Fragment#onActivityResult(int, int, Intent)}.
     * @return Whether use original photo
     */
    public static boolean obtainOriginalState(Intent data) {
        return data.getBooleanExtra(CarrotActivity.EXTRA_RESULT_ORIGINAL_ENABLE, false);
    }

    /**
     * MIME types the selection constrains on.
     * <p>
     * Types not included in the set will still be shown in the grid but can't be chosen.
     *
     * @param mimeTypes MIME types set user can choose from.
     * @return {@link SelectionCreator} to build select specifications.
     * @see MediaType
     * @see SelectionCreator
     */
    public SelectionCreator choose(Set<MediaType> mimeTypes) {
        return this.choose(mimeTypes, true);
    }

    /**
     * MIME types the selection constrains on.
     * <p>
     * Types not included in the set will still be shown in the grid but can't be chosen.
     *
     * @param mimeTypes          MIME types set user can choose from.
     * @param mediaTypeExclusive Whether can choose images and videos at the same time during one single choosing
     *                           process. true corresponds to not being able to choose images and videos at the same
     *                           time, and false corresponds to being able to do this.
     * @return {@link SelectionCreator} to build select specifications.
     * @see MediaType
     * @see SelectionCreator
     */
    public SelectionCreator choose(Set<MediaType> mimeTypes, boolean mediaTypeExclusive) {
        return new SelectionCreator(this, mimeTypes, mediaTypeExclusive).captureStrategy(new CaptureStrategy(true, CarrotUtils.getApplicationId(mContext.get()) + ".provider.carrot", CarrotUtils.getAppName(mContext.get())));
    }

    @Nullable
    public Activity getActivity() {
        return mContext.get();
    }

    @Nullable
    public Fragment getFragment() {
        return mFragment != null ? mFragment.get() : null;
    }
}
