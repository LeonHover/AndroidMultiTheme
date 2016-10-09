package io.github.leonhover.theme.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.github.leonhover.theme.IThemeObserver;
import io.github.leonhover.theme.ThemeManager;
import io.github.leonhover.theme.ThemeViewEntities;

/**
 * Created by leonhover on 16-10-9.
 */

public class BaseThemeActivity extends AppCompatActivity {

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
    protected void configTheme(ActivityTheme activityTheme) {
//        activityTheme.setTheme(R.style.TopvAppDayTheme, R.style.TopvAppNightTheme);
    }

    public ThemeViewEntities getThemeViewEntities() {
        return this.themeViewEntities;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (activityTheme != null) {
            activityTheme.close();
            activityTheme = null;
        }

        themeViewEntities.clear();
    }

    public static class ActivityTheme implements IThemeObserver {
        public static final String TAG = ActivityTheme.class.getSimpleName();
        private int defaultTheme = -1;
        private int nightTheme = -1;

        private AppCompatActivity activity;

        private ThemeManager themeManager;

        private ActivityTheme(AppCompatActivity activity) {
            this.activity = activity;
            this.themeManager = ThemeManager.getInstance();
        }

        /**
         * 设置主题
         *
         * @param defaultTheme 默认主题
         * @param nightTheme   夜间主题
         */
        public void setTheme(int defaultTheme, int nightTheme) {
            this.defaultTheme = defaultTheme;
            this.nightTheme = nightTheme;
        }

        private void assembleThemeBeforeInflate() {
            if (isThemeConfigured()) {
                themeManager.addObserver(this);
                activity.setTheme(getCurrentTheme(themeManager.isNightTheme()));
                themeManager.assembleThemeBeforeInflate(activity);
            }

        }

        private void close() {
            this.themeManager.removeObserver(this);
            this.activity = null;
            this.themeManager = null;
        }

        @Override
        public void onThemeChanged(boolean isNightTheme) {
            if (isThemeConfigured() && this.themeManager != null) {
                this.themeManager.applyTheme(this.activity, getCurrentTheme(isNightTheme));
            }
        }

        private boolean isThemeConfigured() {
            return this.defaultTheme > -1 && this.nightTheme > -1;
        }

        private int getCurrentTheme(boolean isNightTheme) {
            return isNightTheme && nightTheme > 0 ? nightTheme : defaultTheme;
        }
    }
}
