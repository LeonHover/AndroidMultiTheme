package io.github.leonhover.theme.base;

import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;

import io.github.leonhover.theme.IThemeObserver;
import io.github.leonhover.theme.ThemeManager;
import io.github.leonhover.theme.ThemeViewEntities;

/**
 * Created by leonhover on 16-10-9.
 */

public abstract class BaseThemeActivity extends AppCompatActivity {

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

        @StyleRes
        private int[] themes;

        private boolean isSupportMenuItemEnable = false;

        private ActivityTheme(AppCompatActivity activity) {
            this.activity = activity;
            this.themeManager = ThemeManager.getInstance();
        }

        public final void setThemes(@StyleRes int[] themes) {
            this.themes = themes;
        }

        public final void setSupportMenuItemThemeEnable(boolean enable) {
            this.isSupportMenuItemEnable = enable;
        }

        private void assembleThemeBeforeInflate() {
            int theme = -1;
            themeManager.addObserver(this);
            int whichTheme = themeManager.getCurrentThemeIndex();
            activity.setTheme(getTheme(whichTheme));
            themeManager.assembleThemeBeforeInflate(activity);

        }

        private void close() {
            this.themeManager.removeObserver(this);
            this.activity = null;
            this.themeManager = null;
        }

        @Override
        public int getPriority() {
            return PRIORITY_ACTIVITY;
        }

        @Override
        public final void onThemeChanged(int whichTheme) {
            if (this.themeManager != null) {
                this.themeManager.applyTheme(this.activity, getTheme(whichTheme));
                if (isSupportMenuItemEnable) {
                    this.activity.supportInvalidateOptionsMenu();
                }
            }
        }

        private int getTheme(int index) {
            if (this.themes == null) {
                return -1;
            }
            return this.themes[index];
        }

        @Override
        public int compareTo(IThemeObserver o) {
            return getPriority() > o.getPriority() ? 1 : -1;
        }
    }
}
