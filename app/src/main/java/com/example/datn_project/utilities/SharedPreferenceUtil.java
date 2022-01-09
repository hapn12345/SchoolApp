package com.example.datn_project.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

public class SharedPreferenceUtil {
    public static final String DEFAULT_SETTING_PREFERENCE = "schoolApp";
    public static final String KEY_ACCESS_TOKEN = "key_access_token";

    public static String getKeyString(Context context, String paramString1, String paramString2) {
        return context.getSharedPreferences(DEFAULT_SETTING_PREFERENCE,
                Context.MODE_MULTI_PROCESS)
                .getString(paramString1, paramString2);
    }

    public static void putKeyString(Context context, String paramString1, String paramString2) {
        context.getSharedPreferences(DEFAULT_SETTING_PREFERENCE,
                Context.MODE_MULTI_PROCESS)
                .edit().putString(paramString1, paramString2).commit();
    }

    public static String readAccessToken(Context context) {
        return getKeyString(context, KEY_ACCESS_TOKEN, "");
    }

    public static void editAccessToken(Context context, String pass) {
        putKeyString(context, KEY_ACCESS_TOKEN, pass);
    }
}
