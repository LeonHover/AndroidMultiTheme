package io.github.leonhover.theme.samples;

import android.app.Application;

import io.github.leonhover.theme.MultiTheme;
import io.github.leonhover.theme.ThemeManager;

/**
 * Created by wangzongliang on 2016/10/19.
 */

public class ThemeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initTheme();
    }

    private void initTheme() {
        MultiTheme.init(this);
        MultiTheme.changeTheme(0);
    }
}
