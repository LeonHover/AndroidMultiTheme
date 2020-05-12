package io.github.leonhover.theme;

import android.annotation.TargetApi;
import android.os.Build;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;

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

    private AppCompatActivity activity;

    @StyleRes
    private int[] themes;

    private int darkThemeIndex = -1;

    private boolean isSupportMenuItemEnable = false;

    @AttrRes
    private int statusBarColorAttrResId = 0;

    private View statusBarPlaceHolder = null;

    public ActivityTheme(AppCompatActivity activity) {
        this.activity = activity;
    }

    /**
     * 设置主题资源数组
     *
     * @param themes 主题资源数组
     */
    public final void setThemes(@StyleRes int[] themes) {
        this.themes = themes;
    }

    /**
     * 设置支持暗黑模式的主题资源数组
     *
     * @param darkThemeIndex 暗黑主题在主题资源数组中的位置
     * @param themes         主题资源数组
     */
    public final void setThemes(int darkThemeIndex, @StyleRes int[] themes) {
        this.themes = themes;
        this.darkThemeIndex = darkThemeIndex;
        if (this.darkThemeIndex < 0 || this.darkThemeIndex >= this.themes.length) {
            throw new IllegalArgumentException("please check you param,there ara some error.");
        }
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
        MultiTheme.d(MultiTheme.TAG, "initializeStatusBarTheme sdk version:" + Build.VERSION.SDK_INT);
        if (!IS_KITKAT || statusBarColorAttrResId == 0) {
            return;
        }

        int statusColor = ThemeUtils.getColor(this.activity, statusBarColorAttrResId);
        if (IS_LOLLIPOP) {
            initializeStatusBarColorOnLollipop(statusColor);
        } else {
            initializeStatusBarColorKitKat(statusColor);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void initializeStatusBarColorKitKat(int statusBarColor) {
        MultiTheme.d(MultiTheme.TAG, "setStatusBarColorKitkat");
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
        MultiTheme.d(MultiTheme.TAG, "setStatusBarColorOnLollipop");
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
        if (statusBarColorAttrResId != 0) {
            int statusBarColor = ThemeUtils.getColor(this.activity, statusBarColorAttrResId);
            setStatusBarColor(statusBarColor);
        }
    }

    private int getTheme(int index) {
        if (this.themes == null) {
            MultiTheme.e(MultiTheme.TAG, "There is no theme array.");
            return -1;
        }

        if (index == MultiTheme.DARK_THEME) {
            return getDarkTheme();
        } else {
            return getNormalTheme(index);
        }
    }

    private int getNormalTheme(int index) {
        MultiTheme.d(MultiTheme.TAG, "getNormalTheme index:" + index);
        if (index > this.themes.length || index < 0) {
            MultiTheme.e(MultiTheme.TAG, "OutOfBound. we use the first theme.");
            return this.themes[0];
        }
        return this.themes[index];
    }

    private int getDarkTheme() {
        MultiTheme.d(MultiTheme.TAG, "getDarkTheme");
        if (darkThemeIndex < 0 || darkThemeIndex >= this.themes.length) {
            MultiTheme.e(MultiTheme.TAG, "You forgot setup a darkThemeIndex, we use the first theme indeed.");
            return this.themes[0];
        }
        return this.themes[this.darkThemeIndex];
    }

    @Override
    public int compareTo(IThemeObserver o) {
        return getPriority() > o.getPriority() ? 1 : -1;
    }
}
