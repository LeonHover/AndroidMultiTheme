package io.github.leonhover.theme;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import io.github.leonhover.theme.widget.AbstractThemeWidget;
import io.github.leonhover.theme.widget.IThemeWidget;

/**
 * Created by wangzongliang on 2016/10/28.
 */

public class MultiTheme {

    private static ThemeManager sThemeManager = null;

    /**
     * 初始化多主题模块，必须在其他方法调用前调用
     *
     * @param application
     */
    public static void init(Application application) {
        sThemeManager = new ThemeManager(application);
    }

    /**
     * 添加自定义的AbstractThemeWidget，通常紧随init方法后调用。
     *
     * @param widgetKey   类型索引，指定themeWidget对应的View的Class，如TextViewWidget对应TextView.class.
     * @param themeWidget 自定义的AbstractThemeWidget
     */
    public static void addThemeWidget(Class<?> widgetKey, AbstractThemeWidget themeWidget) {
        sThemeManager.addThemeWidget(widgetKey, themeWidget);
    }

    /**
     * 添加主题改变的观察器
     *
     * @param themeObserver
     */
    public static void addObserver(IThemeObserver themeObserver) {
        sThemeManager.addObserver(themeObserver);
    }

    /**
     * 移除主题改变的观察器
     *
     * @param themeObserver
     */
    public static void removeObserver(IThemeObserver themeObserver) {
        sThemeManager.removeObserver(themeObserver);
    }

    /**
     * 组装主题相关元素，注意在Activity的setContentView之前调用。
     *
     * @param activity AppCompatActivity
     */
    public static void assembleThemeBeforeInflate(AppCompatActivity activity) {
        sThemeManager.assembleThemeBeforeInflate(activity);
    }

    /**
     * 动态地为View添加主题控件的WidgetKey。
     *
     * @param view
     */
    public static void addViewThemeWidgetKeyTag(View view) {
        sThemeManager.addViewThemeWidgetKeyTag(view);
    }

    /**
     * 改变主题
     *
     * @param whichTheme 主题的Index值
     * @return
     */
    public static boolean changeTheme(int whichTheme) {
        return sThemeManager.changeTheme(whichTheme);
    }

    public static int getCurrentThemeIndex() {
        return sThemeManager.getCurrentThemeIndex();
    }

    /**
     * 改变Activity主题
     *
     * @param activity
     * @param theme
     */
    protected static void applyTheme(Activity activity, @StyleRes int theme) {
        sThemeManager.applyTheme(activity, theme);
    }

    /**
     * 改变主题后，动态的应用View的主题改变，通常情况只在DecorView中找不到这些view的情况下使用。
     *
     * @param view
     */
    public static void applyTheme(View view) {
        sThemeManager.applyTheme(view);
    }


    private static void checkInstance() {
        if (sThemeManager == null) {

        }
    }

}
