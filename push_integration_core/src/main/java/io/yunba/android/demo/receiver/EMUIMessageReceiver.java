package io.yunba.android.demo.receiver;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.huawei.android.pushagent.PushReceiver;

import java.io.UnsupportedEncodingException;

import io.yunba.android.demo.ErrorCode;
import io.yunba.android.demo.PushCallback;
import io.yunba.android.demo.UniformMessage;

/**
 * Created by longmiao on 16-12-23.
 */
public class EMUIMessageReceiver extends PushReceiver {
    private static PushCallback mPushCallback;

    public static void setPushCallback(PushCallback callback) {
        mPushCallback = callback;
    }

    @Override
    public void onToken(Context context, String token, Bundle extras) {
        if (!TextUtils.isEmpty(token)) {
            mPushCallback.onRegisterResult(ErrorCode.SUCCESS, token);
        }
    }

    @Override
    public boolean onPushMsg(Context context, byte[] msg, Bundle bundle) {
        try {
            String content = new String(msg, "UTF-8");
            UniformMessage um = new UniformMessage();
            um.setContent(content);
            mPushCallback.onMessaheReceived(um);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void onEvent(Context context, Event event, Bundle extras) {
    }
}
