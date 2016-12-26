package io.yunba.android.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PushManager.start(this, new PushCallback() {
            @Override
            public HashMap<String, Object> getRegisterParams() {
                return null;
            }

            @Override
            public void onMessaheReceived(UniformMessage result) {

            }

            @Override
            public void onRegisterResult(long errCode, String regId) {

            }
        });
    }
}
