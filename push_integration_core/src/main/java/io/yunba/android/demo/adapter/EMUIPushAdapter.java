package io.yunba.android.demo.adapter;

import android.content.Context;

import com.huawei.android.pushagent.api.PushManager;

import java.util.HashMap;

import io.yunba.android.demo.PushCallback;
import io.yunba.android.demo.receiver.EMUIMessageReceiver;

/**
 * Created by longmiao on 16-12-23.
 */
public class EMUIPushAdapter implements IPushRomAdapter {
    private static final String KEY_TAG = "name";
    private Context mContext;
    private PushCallback mPushCallback;

    public EMUIPushAdapter(Context ctx) {
        this.mContext = ctx;
    }

    @Override
    public void onResume() {
        PushManager.enableReceiveNormalMsg(mContext, true);
        PushManager.enableReceiveNotifyMsg(mContext, true);
    }

    @Override
    public void onPause() {
        PushManager.enableReceiveNormalMsg(mContext, false);
        PushManager.enableReceiveNotifyMsg(mContext, false);
    }

    @Override
    public void register() {
        PushManager.requestToken(mContext);
    }

    @Override
    public void setPushCallback(PushCallback callback) {
        this.mPushCallback = callback;
        EMUIMessageReceiver.setPushCallback(mPushCallback);
    }

    @Override
    public void setAlias(String alias) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(KEY_TAG, alias);
        PushManager.setTags(mContext, map);
    }
}
