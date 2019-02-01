package com.nestnfly.event.helper;

import android.content.Context;

//import com.nestnfly.event.ui.BaseActivity;

public class PrefUtils {
    public static String USER_ID = "UserId";
    public static String USER_PROFILE_KEY = "USER_PROFILE_KEY";
    public static String LOGGED_IN = "isLogin";
    public static String FILTER_APPLIED = "filter_apply";
    private static String FCM_TOKEN = "fcm";
    private static String ACCESS_TYPE = "access";
    private static String CONNECTOR_TYPE = "CONNECTOR";
    private static String SPEED_LEVEL_TYPE = "SPEED_LEVEL";
    private static String FACILITIES_TYPE = "FACILITIES";
    private static String LANGUAGE = "lang";
    private static String LANGUAGE_ID = "LANGUAGE_ID";
    private static String FIRST_TIME = "FIRST_TIME";

    public static void setFirstTime(Context ctx, boolean value) {
        Prefs.with(ctx).save(FIRST_TIME, value);
    }

    public static boolean isFirstType(Context ctx) {
        return Prefs.with(ctx).getBoolean(FIRST_TIME, true);
    }

    public static void setFCMToken(Context ctx, String value) {
        Prefs.with(ctx).save(FCM_TOKEN, value);
    }

    public static String getFCMToken(Context ctx) {
        return Prefs.with(ctx).getString(FCM_TOKEN, "");
    }

    public static void setLoggedIn(Context ctx, boolean value) {
        Prefs.with(ctx).save(LOGGED_IN, value);
    }

    public static boolean isUserLoggedIn(Context ctx) {
        return Prefs.with(ctx).getBoolean(LOGGED_IN, false);
    }


    public static void setUserID(Context ctx, long value) {
        Prefs.with(ctx).save(USER_ID, value);
    }

//    public static long getUserID(BaseActivity ctx) {
//        return Prefs.with(ctx).getLong(USER_ID, 0);
//    }

/*
    public static void setUserFullProfileDetails(BaseActivity context, User userProfile) {

        String toJson = new Gson().toJson(userProfile);
        setUserID(context, (userProfile == null) ? 0 : userProfile.getUserId());
        Prefs.with(context).save(USER_PROFILE_KEY, toJson);
    }

    public static User getUserFullProfileDetails(BaseActivity context) {
        Gson gson = new Gson();

        User userProfileDetails = null;

        String getUser = Prefs.with(context).getString(USER_PROFILE_KEY, "");

        try {
            userProfileDetails = gson.fromJson(getUser, User.class);

        } catch (Exception e) {

        }
        return userProfileDetails;
    }
*/

}
