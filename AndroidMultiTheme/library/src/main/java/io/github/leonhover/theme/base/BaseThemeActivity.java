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

        private AppCompatActivity activity;

        private ThemeManager themeManager;

        private ActivityTheme(AppCompatActivity activity) {
            this.activity = activity;
            this.themeManager = ThemeManager.getInstance();
        }

        private void assembleThemeBeforeInflate() {
            int theme = -1;
            themeManager.addObserver(this);
            activity.setTheme(theme);
            themeManager.assembleThemeBeforeInflate(activity);

        }

        private void close() {
            this.themeManager.removeObserver(this);
            this.activity = null;
            this.themeManager = null;
        }

        @Override
        public void onThemeChanged(int theme) {
            if (this.themeManager != null) {
                this.themeManager.applyTheme(this.activity, theme);
            }
        }

    }
}
