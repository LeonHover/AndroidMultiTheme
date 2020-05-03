package io.github.leonhover.theme.samples;

import android.app.Application;

import io.github.leonhover.theme.DarkMode;
import io.github.leonhover.theme.MultiTheme;

/**
 * Created by wangzongliang on 2016/10/19.
 */

public class ThemeApplication extends Application {

    static Application sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        initTheme();
    }

    private void initTheme() {
        MultiTheme.init(this);
        MultiTheme.enableDebug();
        MultiTheme.setDefaultAppTheme(0);
    }
}
