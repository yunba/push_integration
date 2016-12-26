package io.yunba.android.demo.adapter;

import android.content.Context;

import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.HashMap;

import io.yunba.android.demo.PushCallback;
import io.yunba.android.demo.PushManager;
import io.yunba.android.demo.receiver.MIUIMessageReceiver;

/**
 * Created by longmiao on 16-12-23.
 */
public class MIUIPushAdapter implements IPushRomAdapter{
    private PushCallback mPushCallback;
    private String mMiuiAppId = "";
    private String mMiuiAppKey = "";
    private Context mContext;
    public MIUIPushAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onResume() {
        MiPushClient.resumePush(mContext, null);
    }

    @Override
    public void onPause() {
        MiPushClient.pausePush(mContext, null);
    }

    @Override
    public void register() {
        MiPushClient.registerPush(mContext, mMiuiAppId, mMiuiAppKey);
    }

    @Override
    public void setPushCallback(PushCallback callback) {
        this.mPushCallback = callback;
        HashMap<String, Object> params = callback.getRegisterParams();
        if (null != params) {
            mMiuiAppId = (String) params.get(PushManager.KEY_MIUI_APP_ID);
            mMiuiAppKey = (String) params.get(PushManager.KEY_MIUI_APP_KEY);
        }
        MIUIMessageReceiver.setPushCallback(mPushCallback);
    }

    @Override
    public void setAlias(String alias) {
        MiPushClient.setAlias(mContext, alias, null);
    }
}
