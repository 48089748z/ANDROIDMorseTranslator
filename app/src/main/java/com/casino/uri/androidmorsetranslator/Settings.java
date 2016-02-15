package com.casino.uri.androidmorsetranslator;

import android.app.Application;

/**
 * Created by uRi on 16/02/2016.
 */
public class Settings extends Application
{
    String value = "TEXT_TO_MORSE";

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
