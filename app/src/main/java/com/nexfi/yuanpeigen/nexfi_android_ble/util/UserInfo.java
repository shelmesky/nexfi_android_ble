package com.nexfi.yuanpeigen.nexfi_android_ble.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.nexfi.yuanpeigen.nexfi_android_ble.R;

/**
 * Created by Mark on 2016/4/14.
 */
public class UserInfo {

    public static int[] userHeadIcon = {R.mipmap.img_head_1, R.mipmap.img_head_2, R.mipmap.img_head_3, R.mipmap.img_head_5, R.mipmap.img_head_6, R.mipmap.img_head_7, R.mipmap.img_head_8, R.mipmap.img_head_9, R.mipmap.img_head_10, R.mipmap.img_head_11, R.mipmap.img_head_12, R.mipmap.img_head_13, R.mipmap.img_add};

    public static void saveUsername(Context context, String username) {
        SharedPreferences sp = context.getSharedPreferences("username", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("userName", username);
        editor.commit();
    }

    public static void saveUsersex(Context context, String usersex) {
        SharedPreferences sp = context.getSharedPreferences("usersex", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("userSex", usersex);
        editor.commit();
    }

    public static void setConfigurationInformation(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("first_pref", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isFirstIn", false);
        editor.commit();
    }

    public static void setNexFiInformation(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("first_nexfi", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isNexFi", true);
        editor.commit();
    }

    public static void saveEnabledInformation(Context context, boolean flag) {
        SharedPreferences preferences = context.getSharedPreferences("EnabledInformation", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("Enabled", flag);
        editor.commit();
    }


    public static void saveUserHeadIcon(Context context, int id) {
        SharedPreferences preferences = context.getSharedPreferences("UserHeadIcon", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("userhead", id);
        editor.commit();
    }

    public static void saveUserAge(Context context, int age) {
        SharedPreferences preferences = context.getSharedPreferences("UserAge", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("userage", age);
        editor.commit();
    }


    public static void initUserAvatar(int userAvatar, Context context) {
        SharedPreferences preferences = context.getSharedPreferences("UserHeadIcon", Context.MODE_PRIVATE);
        userAvatar = preferences.getInt("userhead", R.mipmap.img_default);
    }

    public static void initUserNick(String userNick, Context context) {
        SharedPreferences preferences = context.getSharedPreferences("username", Context.MODE_PRIVATE);
        userNick = preferences.getString("userName", "未填写");
    }

    public static void initUserGender(String userGender, Context context) {
        SharedPreferences preferences = context.getSharedPreferences("usersex", Context.MODE_PRIVATE);
        userGender = preferences.getString("userSex", null);
    }

    public static void initUserAge(int userAge, Context context) {
        SharedPreferences preferences = context.getSharedPreferences("UserAge", Context.MODE_PRIVATE);
        userAge = preferences.getInt("userage", 0);
    }

    public static void initConfigurationInformation(boolean isFirstIn, Context context) {
        SharedPreferences preferences = context.getSharedPreferences("first_pref", Context.MODE_PRIVATE);
        isFirstIn = preferences.getBoolean("isFirstIn", true);
    }
}
