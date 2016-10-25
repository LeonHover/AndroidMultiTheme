package io.github.leonhover.theme;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.StyleRes;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import io.github.leonhover.theme.widget.AbsListViewWidget;
import io.github.leonhover.theme.widget.AbstractThemeWidget;
import io.github.leonhover.theme.widget.CompoundButtonWidget;
import io.github.leonhover.theme.widget.IThemeWidget;
import io.github.leonhover.theme.widget.ImageViewWidget;
import io.github.leonhover.theme.widget.LinearLayoutWidget;
import io.github.leonhover.theme.widget.ListViewWidget;
import io.github.leonhover.theme.widget.ProgressBarWidget;
import io.github.leonhover.theme.widget.SeekBarWidget;
import io.github.leonhover.theme.widget.TextViewWidget;
import io.github.leonhover.theme.widget.ToolBarWidget;
import io.github.leonhover.theme.widget.ViewWidget;


/**
 * Created by leonhover on 16-9-26.
 */

public class ThemeManager {

    public static final String TAG = ThemeManager.class.getSimpleName();

    private Map<Class<?>, AbstractThemeWidget> themeWidgetMap;

    private ThemeViewCreator themeViewCreator;

    private int currentThemeIndex = -1;

    private static ThemeManager sThemeManager;

    private Context context;

    private static boolean isDebug = true;


    private Set<IThemeObserver> themeObserverSet;

    private ThemeManager() {
        this.themeWidgetMap = new HashMap<>();
        this.themeWidgetMap.put(View.class, new ViewWidget());
        this.themeWidgetMap.put(TextView.class, new TextViewWidget());
        this.themeWidgetMap.put(ImageView.class, new ImageViewWidget());
        this.themeWidgetMap.put(CompoundButton.class, new CompoundButtonWidget());
        this.themeWidgetMap.put(ProgressBar.class, new ProgressBarWidget());
        this.themeWidgetMap.put(ListView.class, new ListViewWidget());
        this.themeWidgetMap.put(SeekBar.class, new SeekBarWidget());
        this.themeWidgetMap.put(LinearLayout.class, new LinearLayoutWidget());
        this.themeWidgetMap.put(AbsListView.class, new AbsListViewWidget());
        this.themeWidgetMap.put(Toolbar.class, new ToolBarWidget());

        this.themeObserverSet = new TreeSet<>();
        this.themeViewCreator = new ThemeViewCreator();
    }

    public static ThemeManager getInstance() {
        return sThemeManager;
    }

    /**
     * 初始化
     *
     * @param context 应用上下文
     */
    public static void init(Application context) {
        sThemeManager = new ThemeManager();
        sThemeManager.context = context;
        sThemeManager.currentThemeIndex = -1;
    }

    /**
     * 添加自定义的AbstractThemeWidget
     *
     * @param widgetKey   类型索引
     * @param themeWidget 自定义的AbstractThemeWidget
     */
    public void add(Class<?> widgetKey, AbstractThemeWidget themeWidget) {
        this.themeWidgetMap.put(widgetKey, themeWidget);
    }

    public void addObserver(IThemeObserver observer) {
        this.themeObserverSet.add(observer);
    }

    public void removeObserver(IThemeObserver observer) {
        this.themeObserverSet.remove(observer);
    }

    /**
     * 组装主题相关元素，注意在Activity的setContentView之前调用。
     *
     * @param activity AppCompatActivity
     */
    public void assembleThemeBeforeInflate(final AppCompatActivity activity) {

        if (activity == null) {
            throw new NullPointerException();
        }
        LayoutInflaterCompat.setFactory(activity.getLayoutInflater(), new LayoutInflaterFactory() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                AppCompatDelegate appCompatDelegate = activity.getDelegate();
                View view = appCompatDelegate.createView(parent, name, context, attrs);


                if (view == null) {
                    view = themeViewCreator.createView(parent, name, context, attrs);
                }

                if (view == null) {
                    return null;
                }

                Class<?> widgetKey = findProperThemeWidgetType(view);
                assembleViewThemeElement(view, attrs, widgetKey);

                return view;
            }
        });
    }

    private Class<?> findProperThemeWidgetType(View view) {

        if (view == null) {
            return null;
        }

        Log.d(TAG, "findProperThemeWidgetType " + view.getClass().getSimpleName());

        Class<?> tmpKey = null;
        for (Class<?> widgetKey : this.themeWidgetMap.keySet()) {

            if (view.getClass().equals(widgetKey)) {
                return view.getClass();
            }

            if (widgetKey.isAssignableFrom(view.getClass())) {
                if (tmpKey == null) {
                    tmpKey = widgetKey;
                } else {
                    if (tmpKey.isAssignableFrom(widgetKey)) {
                        tmpKey = widgetKey;
                    }
                }
            }
        }

        if (tmpKey == null) {
            return null;
        } else {
            Log.d(TAG, "findProperThemeWidgetType result:" + tmpKey);
            return tmpKey;
        }

    }

    /**
     * 组装每个View的主题元素
     */
    private void assembleViewThemeElement(View view, AttributeSet attributeSet, Class<?> widgetKey) {

        Log.d(TAG, "assembleViewThemeElement  theme widget type:" + widgetKey + ", view:" + view);
        if (view == null) {
            return;
        }

        IThemeWidget themeWidget = this.themeWidgetMap.get(widgetKey);
        if (themeWidget != null) {
            view.setTag(R.id.amt_tag_widget_type, widgetKey);
            view.setTag(R.id.amt_tag_view_current_theme, getCurrentThemeIndex());
            themeWidget.assemble(view, attributeSet);
            Log.d(TAG, "assembleViewThemeElement  theme widget type: " + widgetKey + " themeWidget:" + themeWidget.getClass().getSimpleName());
            debugAttributes(attributeSet);

        } else {
            view.setTag(R.id.amt_tag_widget_type, null);
            Log.i(TAG, "unsupported theme widget type " + widgetKey + ",is your custom theme widget?");
        }
    }

    private void debugAttributes(AttributeSet attributeSet) {
        Log.d(TAG, "debugAttributes =============== start ===============");

        for (int i = 0; attributeSet != null && i < attributeSet.getAttributeCount(); i++) {
            Log.d(TAG, "name:" + attributeSet.getAttributeName(i) + " value:" + attributeSet.getAttributeValue(i));
        }
        Log.d(TAG, "debugAttributes =============== end ===============");

    }

    /**
     * 改变主题，在默认主题与夜间主题之间进行切换
     *
     * @return true 改变为夜间主题，false 改变为默认主题
     */
    public boolean changeTheme(int whichTheme) {
        Log.d(TAG, "changeTheme whichTheme=" + whichTheme);

        if (whichTheme != currentThemeIndex) {
            currentThemeIndex = whichTheme;
            for (IThemeObserver themeObserver : themeObserverSet) {
                themeObserver.onThemeChanged(whichTheme);
            }
            return true;
        } else {
            return false;
        }

    }

    public int getCurrentThemeIndex() {
        return this.currentThemeIndex;
    }

    /**
     * 改变主题，在默认主题与夜间主题之间进行切换
     *
     * @param activity   Activity
     * @param themeResId 主题资源id
     */
    public void applyTheme(Activity activity, @StyleRes int themeResId) {
        activity.setTheme(themeResId);
        applyTheme(activity);
    }

    private void applyTheme(Activity activity) {
        Log.d(TAG, "applyThemeForActivity");
        if (activity == null) {
            throw new IllegalArgumentException("activity  is null!");
        }

        View decorView = activity.getWindow().getDecorView();
        applyTheme(decorView);

    }

    /**
     * 对单个View应用主题
     *
     * @param view View
     */
    public void applyTheme(View view) {

        int themeOfView = -1;
        try {
            themeOfView = ThemeUtils.getViewTag(view, R.id.amt_tag_view_current_theme);
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
        }

        if (themeOfView == currentThemeIndex) {
            return;
        }


        Class<?> widgetKey = (Class<?>) view.getTag(R.id.amt_tag_widget_type);

        Log.d(TAG, "applyTheme  theme widget type:" + widgetKey + " ,view:" + view);
        IThemeWidget themeWidget = this.themeWidgetMap.get(widgetKey);
        if (themeWidget != null) {
            themeWidget.applyTheme(view);
            Log.d(TAG, "applyTheme  theme widget type: " + widgetKey + " ,view:" + view + " themeWidget:" + themeWidget);
        } else {
            Log.i(TAG, "applyTheme unsupport theme widget type:" + widgetKey + ", view:" + view);
        }

        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                applyTheme(child);
            }
        }

        view.setTag(R.id.amt_tag_view_current_theme, getCurrentThemeIndex());
    }

}
