package io.github.leonhover.theme.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.github.leonhover.theme.ActivityTheme;
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

    /**
     * 设置StatusBar的背景色
     *
     * @param color Color
     */
    public void setStatusBarColor(int color) {
        this.activityTheme.setStatusBarColor(color);
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
