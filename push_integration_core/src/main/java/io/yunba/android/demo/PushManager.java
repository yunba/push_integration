package io.yunba.android.demo;


import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;

import java.util.List;

import io.yunba.android.demo.adapter.EMUIPushAdapter;
import io.yunba.android.demo.adapter.IPushRomAdapter;
import io.yunba.android.demo.adapter.MIUIPushAdapter;
import io.yunba.android.demo.utils.RomUtil;

public class PushManager {
    public static final String KEY_MIUI_APP_ID = "miui_app_id";
    public static final String KEY_MIUI_APP_KEY = "miui_app_key";

    private static IPushRomAdapter mPushRomAdapter;
    private static Context mContext;

    public static RomUtil.RomType start(Context context, PushCallback callback) {
        mContext = context;
        RomUtil.RomType type = RomUtil.getRoomType();
        switch (type) {
            case MIUI:
                mPushRomAdapter = new MIUIPushAdapter(context);
                break;
            case EMUI:
                mPushRomAdapter = new EMUIPushAdapter(context);
                break;
            default:
        }
        if (null != mPushRomAdapter) {
            if (shouldInit()) {
                mPushRomAdapter.register();
            }
            mPushRomAdapter.setPushCallback(callback);
        }
        return type;
    }

    public static void resume() {
        mPushRomAdapter.onResume();
    }

    public void pause() {
        mPushRomAdapter.onPause();
    }

    private static boolean shouldInit() {
        ActivityManager am = ((ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = mContext.getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    public static void setAlias(String alias) {
        if (null != mPushRomAdapter) {
            mPushRomAdapter.setAlias(alias);
        }
    }
}
