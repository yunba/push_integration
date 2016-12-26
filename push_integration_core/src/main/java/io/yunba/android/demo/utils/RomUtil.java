package io.yunba.android.demo.utils;

import android.content.Context;
import android.text.TextUtils;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by longmiao on 16-12-25.
 */
public class RomUtil {
    private static RomType mType;
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
    private static final String KEY_MIUI_HANDY_MODE_SF = "ro.miui.has_handy_mode_sf";
    private static final String KEY_MIUI_REAL_BLUR = "ro.miui.has_real_blur";
    private static final String KEY_EMUI_VERSION_CODE = "ro.build.version.emui";

    public enum RomType {MIUI, EMUI, NONE}

    public static RomType getRoomType() {
        if (null != mType) {
            return mType;
        }
        if (isEMUI()) {
            return RomType.EMUI;
        }
        if (isMIUI()) {
            return RomType.MIUI;
        }
        return RomType.NONE;
    }

    public static boolean isMIUI() {
        try {
            return !TextUtils.isEmpty(getKey(KEY_MIUI_VERSION_CODE))
                    || !TextUtils.isEmpty(getKey(getKey(KEY_MIUI_VERSION_NAME)))
                    || !TextUtils.isEmpty(getKey(getKey(KEY_MIUI_INTERNAL_STORAGE)));
        } catch (Throwable e) {
            return false;
        }
    }

    public static boolean isMIUI2() {
        try {
            BuildProperties prop = BuildProperties.getInstance();
            return prop.containsKey(KEY_MIUI_VERSION_CODE)
                    || prop.containsKey(KEY_MIUI_VERSION_NAME)
                    || prop.containsKey(KEY_MIUI_REAL_BLUR)
                    || prop.containsKey(KEY_MIUI_HANDY_MODE_SF);
        } catch (IOException exception) {
            return false;
        }
    }

    public static boolean isEMUI() {
        if(null == android.os.Build.MANUFACTURER ) return false;
        if(android.os.Build.MANUFACTURER.toLowerCase().equals("huawei")) return true;
        return false;
    }

    public static boolean isEMUI2() {
        try {
            final BuildProperties prop = BuildProperties.getInstance();
            return prop.containsKey(KEY_EMUI_VERSION_CODE);
        } catch (final IOException e) {
            return false;
        }
    }

    public static String getKey(String key) {
        String value = null;
        try {
            Class<?> classType = Class.forName("android.os.SystemProperties");
            Method getMethod = classType.getDeclaredMethod("get",
                    new Class<?>[]{String.class});
            value = (String) getMethod.invoke(classType, new Object[]{key});
        } catch (Exception e) {
        }
        return value;
    }

    public static String get(Context context, String key) throws IllegalArgumentException {
        String ret = "";
        try {
            ClassLoader cl = context.getClassLoader();
            @SuppressWarnings("rawtypes")
            Class SystemProperties = cl.loadClass("android.os.SystemProperties");

            //Parameters Types
            @SuppressWarnings("rawtypes")
            Class[] paramTypes = new Class[1];
            paramTypes[0] = String.class;

            Method get = SystemProperties.getMethod("get", paramTypes);

            //Parameters
            Object[] params = new Object[1];
            params[0] = new String(key);

            ret = (String) get.invoke(SystemProperties, params);

        } catch (IllegalArgumentException iAE) {
            throw iAE;
        } catch (Exception e) {
            ret = "";
        }
        return ret;
    }
}
