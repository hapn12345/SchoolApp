package com.example.datn_project.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

public class SharedPreferenceUtil {
    public static final String DEFAULT_SETTING_PREFERENCE = "schoolApp";
    public static final String KEY_PASS = "password";

    public static String getKeyString(Context context, String paramString1, String paramString2) {
        return context.getSharedPreferences(DEFAULT_SETTING_PREFERENCE,
                Build.VERSION.SDK_INT > Build.VERSION_CODES.FROYO ? Context.MODE_MULTI_PROCESS : Context.MODE_PRIVATE)
                .getString(paramString1, paramString2);
    }

    public static void putKeyString(Context context, String paramString1, String paramString2) {
        context.getSharedPreferences(DEFAULT_SETTING_PREFERENCE,
                Build.VERSION.SDK_INT > Build.VERSION_CODES.FROYO ? Context.MODE_MULTI_PROCESS : Context.MODE_PRIVATE)
                .edit().putString(paramString1, paramString2).commit();
    }

    public static String readPassWord(Context context) {
        return getKeyString(context, KEY_PASS, "");
    }

    public static void editPassWord(Context context, String pass) {
        putKeyString(context, KEY_PASS, pass);
    }
}
