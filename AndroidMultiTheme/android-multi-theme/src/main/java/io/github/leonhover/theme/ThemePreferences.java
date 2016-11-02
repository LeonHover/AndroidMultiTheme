package io.github.leonhover.theme;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wangzongliang on 2016/11/2.
 */

public class ThemePreferences {

    private static final String AMT_PREFERENCES_NAME = "amt_preferences";

    private static final String AMT_APP_THEME = "amt_app_theme";

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(AMT_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    protected static void setAppTheme(Context context, int theme) {
        SharedPreferences themePreferences = getPreferences(context);
        SharedPreferences.Editor editor = themePreferences.edit();
        editor.putInt(AMT_APP_THEME, theme);
        editor.apply();
    }

    protected static int getAppTheme(Context context) {
        return getPreferences(context).getInt(AMT_APP_THEME, -1);
    }

}
