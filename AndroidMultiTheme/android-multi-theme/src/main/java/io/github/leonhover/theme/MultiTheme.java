package io.github.leonhover.theme;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import io.github.leonhover.theme.widget.AbstractThemeWidget;

/**
 * Created by wangzongliang on 2016/10/28.
 */

public class MultiTheme {

    private static ThemeManager sThemeManager = null;
    private static boolean debugMode = false;

    /**
     * 初始化多主题模块，必须在其他方法调用前调用
     */
    public static void init(Application application) {
        sThemeManager = new ThemeManager(application);
    }

    public static void setDebugMode(boolean debugMode) {
        MultiTheme.debugMode = debugMode;
    }

    protected static boolean isDebugMode() {
        return MultiTheme.debugMode;
    }

    /**
     * 添加自定义的AbstractThemeWidget，通常紧随init方法后调用。
     *
     * @param widgetKey   类型索引，指定themeWidget对应的View的Class，如TextViewWidget对应TextView.class.
     * @param themeWidget 自定义的AbstractThemeWidget
     */
    public static void addThemeWidget(Class<?> widgetKey, AbstractThemeWidget themeWidget) {
        checkInstance();
        sThemeManager.addThemeWidget(widgetKey, themeWidget);
    }

    /**
     * 获取view对应的AbstractThemeWidget。
     *
     * @param view View.
     * @return AbstractThemeWidget
     */
    public static AbstractThemeWidget getThemeWidget(View view) {
        checkInstance();
        return sThemeManager.getThemeWidget(view.getClass());
    }

    /**
     * 获取Class对应的AbstractThemeWidget。
     *
     * @param clazz class of View.
     * @return AbstractThemeWidget
     */
    public static AbstractThemeWidget getThemeWidget(Class<?> clazz) {
        checkInstance();
        return sThemeManager.getThemeWidget(clazz);
    }

    /**
     * 添加主题改变的观察器
     *
     * @param themeObserver
     */
    public static void addObserver(IThemeObserver themeObserver) {
        checkInstance();
        sThemeManager.addObserver(themeObserver);
    }

    /**
     * 移除主题改变的观察器
     *
     * @param themeObserver
     */
    public static void removeObserver(IThemeObserver themeObserver) {
        checkInstance();
        sThemeManager.removeObserver(themeObserver);
    }

    /**
     * 组装主题相关元素，注意在Activity的setContentView之前调用。
     *
     * @param activity AppCompatActivity
     */
    public static void assembleThemeBeforeInflate(AppCompatActivity activity) {
        checkInstance();
        sThemeManager.assembleThemeBeforeInflate(activity);
    }

    /**
     * 动态地为View添加主题控件的WidgetKey。
     *
     * @param view
     * @return AbstractThemeWidget
     */
    public static AbstractThemeWidget addViewThemeWidgetKeyTag(View view) {
        checkInstance();
        return sThemeManager.addViewThemeWidgetKeyTag(view);
    }

    /**
     * 改变主题
     *
     * @param whichTheme 应用主题，这里对应的是ActivityTheme中的theme[]的索引值
     * @return true 表示切换主题成功,反之为false.
     */
    public static boolean setAppTheme(int whichTheme) {
        checkInstance();
        return sThemeManager.setAppTheme(whichTheme);
    }

    /**
     * 设置应用的默认主题，通常是首次使用的，但修改后就会这个值就不再生效了。
     *
     * @param defaultTheme 默认主题
     */
    public static void setDefaultAppTheme(int defaultTheme) {
        checkInstance();
        sThemeManager.setDefaultTheme(defaultTheme);
    }

    /**
     * 获取应用的当前主题
     *
     * @return int 当前应用的theme[]的索引值
     */
    public static int getAppTheme() {
        checkInstance();
        return sThemeManager.getAppTheme();
    }

    /**
     * 改变Activity主题
     *
     * @param activity
     * @param theme
     */
    protected static void applyTheme(Activity activity, @StyleRes int theme) {
        checkInstance();
        sThemeManager.applyTheme(activity, theme);
    }

    /**
     * 改变主题后，动态的应用View的主题改变，通常情况只在DecorView中找不到这些view的情况下使用。
     *
     * @param view
     */
    public static void applyTheme(View view) {
        checkInstance();
        sThemeManager.applyTheme(view);
    }

    private static void checkInstance() throws IllegalStateException {
        if (sThemeManager == null) {
            throw new IllegalStateException("Do you initialize MultiTheme in creation of your app's application.");
        }
    }

    public static int d(String tag, String msg) {
        if (isDebugMode()) {
            return Log.d(tag, msg);
        } else {
            return -1;
        }
    }

    public static int e(String tag, String msg) {
        if (isDebugMode()) {
            return Log.e(tag, msg);
        } else {
            return -1;
        }
    }

    public static int i(String tag, String msg) {
        if (isDebugMode()) {
            return Log.i(tag, msg);
        } else {
            return -1;
        }
    }
}
