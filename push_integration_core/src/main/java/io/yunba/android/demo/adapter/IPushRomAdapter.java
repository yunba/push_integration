package io.yunba.android.demo.adapter;


import io.yunba.android.demo.PushCallback;

/**
 * Created by longmiao on 16-12-23.
 */
public interface IPushRomAdapter {
    void onResume();
    void onPause();
    void register();
    void setPushCallback(PushCallback callback);
    void setAlias(String alias);
}
