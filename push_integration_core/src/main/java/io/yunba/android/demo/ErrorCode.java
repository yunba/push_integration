package io.yunba.android.demo;

/**
 * Created by longmiao on 16-12-25.
 */
public class ErrorCode {
    public static final long SUCCESS = 0;
    public static final long ERROR_SERVICE_UNAVAILABLE = 70000001;
    public static final long ERROR_INTERNAL_ERROR = 70000004;
    public static final long ERROR_AUTHERICATION_ERROR = 70000002;
    public static final long ERROR_INVALID_PAYLOAD = 70000003;
    public static final long ERROR_OTHER = 70000004;

    public static long fromMiuiErr(long miuiErr) {
        return miuiErr;
    }
}
