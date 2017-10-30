package io.github.leonhover.theme;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import static io.github.leonhover.theme.ThemeUtils.IS_KITKAT;
import static io.github.leonhover.theme.ThemeUtils.IS_LOLLIPOP;
import static io.github.leonhover.theme.ThemeUtils.getStatusBarHeight;

/**
 * Created by wangzongliang on 2016/10/28.
 */

public class ActivityTheme implements IThemeObserver {

    public static final String TAG = ActivityTheme.class.getSimpleName();

    private AppCompatActivity activity;

    @StyleRes
    private int[] themes;

    private boolean isSupportMenuItemEnable = false;

    @AttrRes
    private int statusBarColorAttrResId = -1;

    private View statusBarPlaceHolder = null;

    public ActivityTheme(AppCompatActivity activity) {
        this.activity = activity;
    }

    public final void setThemes(@StyleRes int[] themes) {
        this.themes = themes;
    }

    /**
     * 开启主题切换支持菜单功能
     *
     * @param enable
     */
    public final void setSupportMenuItemThemeEnable(boolean enable) {
        this.isSupportMenuItemEnable = enable;
    }

    /**
     * 设置通知栏的颜色，4.4以下的设备无效
     *
     * @param statusBarColorAttrResId 指向颜色值的AttrRes
     */
    public final void setStatusBarColorAttrRes(@AttrRes int statusBarColorAttrResId) {
        this.statusBarColorAttrResId = statusBarColorAttrResId;
    }

    public final void setStatusBarColor(@ColorInt int statusBarColor) {
        if (!IS_KITKAT) {
            return;
        }

        if (IS_LOLLIPOP) {
            setStatusBarColorOnLollipop(statusBarColor);
        } else if (IS_KITKAT) {
            setStatusBarColorKitKat(statusBarColor);
        }
    }

    public void assembleThemeBeforeInflate() {
        MultiTheme.addObserver(this);
        int whichTheme = MultiTheme.getAppTheme();
        int theme = getTheme(whichTheme);
        if (theme > 0) {
            activity.setTheme(theme);
        }
        MultiTheme.assembleThemeBeforeInflate(activity);
        initializeStatusBarTheme();
    }

    private void initializeStatusBarTheme() {
        MultiTheme.d(TAG, "initializeStatusBarTheme sdk version:" + Build.VERSION.SDK_INT);
        if (!IS_KITKAT || statusBarColorAttrResId < 0) {
            return;
        }

        int statusColor = ThemeUtils.getColor(this.activity, statusBarColorAttrResId);
        if (IS_LOLLIPOP) {
            initializeStatusBarColorOnLollipop(statusColor);
        } else if (IS_KITKAT) {
            initializeStatusBarColorKitKat(statusColor);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void initializeStatusBarColorKitKat(int statusBarColor) {
        MultiTheme.d(TAG, "setStatusBarColorKitkat");
        int statusBarHeight = getStatusBarHeight(this.activity);
        Window window = activity.getWindow();
        ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);

        //First translucent status bar.
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ViewGroup actionBarRoot = (ViewGroup) mContentView.getParent();

        statusBarPlaceHolder = new View(activity);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
        //向 ContentView 中添加假 View
        actionBarRoot.addView(statusBarPlaceHolder, 0, lp);
        setStatusBarColorKitKat(statusBarColor);
    }

    private void setStatusBarColorKitKat(int statusBarColor) {
        if (statusBarPlaceHolder != null) {
            statusBarPlaceHolder.setBackgroundColor(statusBarColor);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initializeStatusBarColorOnLollipop(int statusBarColor) {
        MultiTheme.d(TAG, "setStatusBarColorOnLollipop");
        Window window = activity.getWindow();
        //设置透明状态栏,这样才能让 ContentView 向上
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        window.setStatusBarColor(statusBarColor);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setStatusBarColorOnLollipop(int statusBarColor) {

        Window window = activity.getWindow();
        window.setStatusBarColor(statusBarColor);
    }

    public void destroy() {
        MultiTheme.removeObserver(this);
        this.activity = null;
    }

    @Override
    public int getPriority() {
        return PRIORITY_ACTIVITY;
    }

    @Override
    public final void onThemeChanged(int whichTheme) {
        MultiTheme.applyTheme(this.activity, getTheme(whichTheme));
        changeStatusBarColor();
        if (isSupportMenuItemEnable) {
            this.activity.supportInvalidateOptionsMenu();
        }
    }

    private void changeStatusBarColor() {
        if (statusBarColorAttrResId > 0) {
            int statusBarColor = ThemeUtils.getColor(this.activity, statusBarColorAttrResId);
            setStatusBarColor(statusBarColor);
        }
    }

    private int getTheme(int index) {
        if (this.themes == null) {
            return -1;
        }

        if (index > this.themes.length || index < 0) {
            return this.themes[0];
        }

        return this.themes[index];
    }

    @Override
    public int compareTo(IThemeObserver o) {
        return getPriority() > o.getPriority() ? 1 : -1;
    }
}
