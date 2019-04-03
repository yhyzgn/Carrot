package com.yhy.carrot.media;

import android.content.ContentResolver;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import com.yhy.carrot.etc.utils.PhotoUtils;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.collection.ArraySet;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2019-04-03 14:23
 * version: 1.0.0
 * desc   :
 */
public enum MediaType {

    /************************************************ Images ************************************************/
    JPEG("image/jpeg", arraySetOf("jpg", "jpeg")),
    PNG("image/png", arraySetOf("png")),
    GIF("image/gif", arraySetOf("gif")),
    BMP("image/x-ms-bmp", arraySetOf("bmp")),
    WEBP("image/webp", arraySetOf("webp")),

    /************************************************ Videos ************************************************/
    MPEG("video/mpeg", arraySetOf("mpeg", "mpg")),
    MP4("video/mp4", arraySetOf("mp4", "m4v")),
    QUICKTIME("video/quicktime", arraySetOf("mov")),
    THREEGPP("video/3gpp", arraySetOf("3gp", "3gpp")),
    THREEGPP2("video/3gpp2", arraySetOf("3g2", "3gpp2")),
    MKV("video/x-matroska", arraySetOf("mkv")),
    WEBM("video/webm", arraySetOf("webm")),
    TS("video/mp2ts", arraySetOf("ts")),
    AVI("video/avi", arraySetOf("avi"));

    private final String mTypeName;
    private final Set<String> mExtensions;

    MediaType(String mimeTypeName, Set<String> extensions) {
        mTypeName = mimeTypeName;
        mExtensions = extensions;
    }

    public static Set<MediaType> ofAll() {
        return EnumSet.allOf(MediaType.class);
    }

    public static Set<MediaType> of(MediaType type, MediaType... rest) {
        return EnumSet.of(type, rest);
    }

    public static Set<MediaType> ofImage() {
        return EnumSet.of(JPEG, PNG, GIF, BMP, WEBP);
    }

    public static Set<MediaType> ofVideo() {
        return EnumSet.of(MPEG, MP4, QUICKTIME, THREEGPP, THREEGPP2, MKV, WEBM, TS, AVI);
    }

    public static boolean isImage(String mimeType) {
        if (mimeType == null) return false;
        return mimeType.startsWith("image");
    }

    public static boolean isVideo(String mimeType) {
        if (mimeType == null) return false;
        return mimeType.startsWith("video");
    }

    public static boolean isGif(String mimeType) {
        if (mimeType == null) return false;
        return mimeType.equals(MediaType.GIF.toString());
    }

    private static Set<String> arraySetOf(String... suffixes) {
        return new ArraySet<>(Arrays.asList(suffixes));
    }

    @NonNull
    @Override
    public String toString() {
        return mTypeName;
    }

    public boolean checkType(ContentResolver resolver, Uri uri) {
        if (uri == null) {
            return false;
        }
        MimeTypeMap map = MimeTypeMap.getSingleton();
        String type = map.getExtensionFromMimeType(resolver.getType(uri));
        String path = null;
        // lazy load the path and prevent resolve for multiple times
        boolean pathParsed = false;
        for (String extension : mExtensions) {
            if (extension.equals(type)) {
                return true;
            }
            if (!pathParsed) {
                // we only resolve the path for one time
                path = PhotoUtils.getPath(resolver, uri);
                if (!TextUtils.isEmpty(path)) {
                    path = path.toLowerCase(Locale.US);
                }
                pathParsed = true;
            }
            if (path != null && path.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
}
