package io.yunba.android.demo;

import io.yunba.android.demo.utils.RomUtil;

/**
 * Created by longmiao on 16-12-25.
 */
public class UniformMessage {
    private RomUtil.RomType romType;
    private String content;
    private String alias;
    private String topic;
    private String userAccount;

    public UniformMessage() {
    }

    public UniformMessage(RomUtil.RomType type, String ct) {
        this.romType = type;
        this.content = ct;
    }

    public void setRomType(RomUtil.RomType type) {
        this.romType = type;
    }

    public RomUtil.RomType getRomType() {
        return romType;
    }

    public void setContent(String ct) {
        this.content = ct;
    }

    public String getContent() {
        return content;
    }
}

