package io.yunba.android.demo;

import java.util.HashMap;

/**
 * Created by longmiao on 16-12-25.
 */
public interface PushCallback {
    HashMap<String, Object> getRegisterParams();
    void onMessaheReceived(UniformMessage result);
    void onRegisterResult(long errCode, String regId);
}
