package com.goEuro.test.util;

/**
 * MessageType type enum
 * <p/>
 * Created by Abhishek Ghosh on 23/08/14.
 */
public enum MessageType {
    JSON("application/json");

    private String mimeType;

    MessageType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return mimeType;
    }
}
