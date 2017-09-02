package com.seek.languageswitchdemo;

import android.content.Context;

/**
 * Created by admin on 2017/8/31.
 */

public class LanguageUtils {
    public final static String LANGUAGE_ENGLISH = "en";
    public final static String LANGUAGE_CHANESE = "zh";

    public static String getSystemLanguage(Context context){
        String lanuage = context.getResources().getConfiguration().locale.getLanguage();
//        LogUtil.i("LangugeUtils", "current language:" + lanuage);
        return lanuage;
    }

    public static boolean isEnglishLanguage(Context context){
        if (getSystemLanguage(context).equals(LanguageUtils.LANGUAGE_ENGLISH)){
            return true;
        }
        return false;
    }

    /**
     * 包括（简体中文、繁体中文、繁体中文（香港））
     * @param context
     * @return
     */
    public static boolean isChineseLanguage(Context context){
        if (getSystemLanguage(context).equals(LanguageUtils.LANGUAGE_CHANESE)){
            return true;
        }
        return false;
    }

}
