package io.github.leonhover.theme.base;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.github.leonhover.theme.ActivityTheme;
import io.github.leonhover.theme.DarkMode;
import io.github.leonhover.theme.MultiTheme;
import io.github.leonhover.theme.ThemeViewEntities;

/**
 * Created by leonhover on 16-10-9.
 */

public abstract class BaseThemeActivity extends AppCompatActivity {

    private final static String TAG = BaseThemeActivity.class.getSimpleName();
    private ActivityTheme activityTheme = new ActivityTheme(this);
    private ThemeViewEntities themeViewEntities = new ThemeViewEntities();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initTheme();
        super.onCreate(savedInstanceState);
    }

    private void initTheme() {
        configTheme(activityTheme);
        activityTheme.assembleThemeBeforeInflate();
    }

    /**
     * 配置Activity的主题
     *
     * @param activityTheme Activity主题
     */
    protected abstract void configTheme(ActivityTheme activityTheme);

    public final ThemeViewEntities getThemeViewEntities() {
        return this.themeViewEntities;
    }

    /**
     * 设置StatusBar的背景色
     *
     * @param color Color
     */
    public void setStatusBarColor(int color) {
        this.activityTheme.setStatusBarColor(color);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        DarkMode mode = MultiTheme.getDarkMode();
        MultiTheme.d("MultiTheme", "onConfigurationChanged mode:" + mode);
        if (mode == DarkMode.followSystem) {
            int uiMode = newConfig.uiMode & Configuration.UI_MODE_NIGHT_MASK;
            if (uiMode == Configuration.UI_MODE_NIGHT_YES) {
                MultiTheme.setAppTheme(MultiTheme.DARK_THEME);
            } else {
                MultiTheme.setAppTheme(MultiTheme.sDefaultThemeIndex);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (activityTheme != null) {
            activityTheme.destroy();
            activityTheme = null;
        }

        themeViewEntities.clear();
    }

}
