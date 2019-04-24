package info.ankurpandya.testvcsproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class DataHelper {

    private static SharedPreferences sp;

    public static void init(Context context) {
        sp = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void saveString(String key, String data) {
        sp.edit().putString(key, data).apply();
    }

    public static String getString(String key) {
        return sp.getString(key, "Not Available");
    }


}
