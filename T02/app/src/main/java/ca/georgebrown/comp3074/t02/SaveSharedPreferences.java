package ca.georgebrown.comp3074.t02;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SaveSharedPreferences {
    static final String PREF_FIRST_NAME= "firstName";
    static final String PREF_EMAIL = "email";
    static final String PREF_ROLE = "role";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setFirstName(Context c, String firstName)
    {
        Editor editor = getSharedPreferences(c).edit();
        editor.putString(PREF_FIRST_NAME, firstName);
        editor.commit();
    }

    public static void setEmail(Context c, String email){
        Editor editor = getSharedPreferences(c).edit();
        editor.putString(PREF_EMAIL, email);
        editor.commit();
    }

    public static void setRole(Context c, String role){
        Editor editor = getSharedPreferences(c).edit();
        editor.putString(PREF_ROLE, role);
        editor.commit();
    }

    public static String getFirstName(Context c)
    {
        return getSharedPreferences(c).getString(PREF_FIRST_NAME, "");
    }


    public static String getEmail(Context c)
    {
        return getSharedPreferences(c).getString(PREF_EMAIL, "");
    }

    public static String getRole(Context c){
        return getSharedPreferences(c).getString(PREF_ROLE,"");
    }

    public static void clearSharedPreferences(Context c)
    {
        Editor editor = getSharedPreferences(c).edit();
        editor.clear(); //clear all stored data
        editor.commit();
    }
}
