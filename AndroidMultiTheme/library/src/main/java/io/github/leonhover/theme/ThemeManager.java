package io.github.leonhover.theme;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.StyleRes;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import io.github.leonhover.theme.widget.AbsListViewWidget;
import io.github.leonhover.theme.widget.AbstractThemeWidget;
import io.github.leonhover.theme.widget.CompoundButtonWidget;
import io.github.leonhover.theme.widget.IThemeWidget;
import io.github.leonhover.theme.widget.ImageViewWidget;
import io.github.leonhover.theme.widget.LinearLayoutWidget;
import io.github.leonhover.theme.widget.ListViewWidget;
import io.github.leonhover.theme.widget.ProgressBarWidget;
import io.github.leonhover.theme.widget.RecycleViewWidget;
import io.github.leonhover.theme.widget.SeekBarWidget;
import io.github.leonhover.theme.widget.SingleViewWidget;
import io.github.leonhover.theme.widget.TextViewWidget;
import io.github.leonhover.theme.widget.ViewWidget;


/**
 * Created by leonhover on 16-9-26.
 */

public class ThemeManager {

    public static final String TAG = ThemeManager.class.getSimpleName();

    private final static int THEME_WIDGET_TYPE_INDEX = 0;

    private Map<Integer, IThemeWidget> themeWidgetMap;

    private ThemeViewCreator themeViewCreator;

    private boolean isNightTheme = false;

    private static ThemeManager sThemeManager;

    private Context context;

    private Set<IThemeObserver> themeObserverSet;

    private ThemeManager() {
        this.themeWidgetMap = new HashMap<>();
        this.themeWidgetMap.put(777, new ViewWidget());
        this.themeWidgetMap.put(776, new TextViewWidget());
        this.themeWidgetMap.put(775, new ImageViewWidget());
        this.themeWidgetMap.put(774, new CompoundButtonWidget());
        this.themeWidgetMap.put(773, new ProgressBarWidget());
        this.themeWidgetMap.put(772, new ListViewWidget());
        this.themeWidgetMap.put(771, new SeekBarWidget());
        this.themeWidgetMap.put(770, new LinearLayoutWidget());
        this.themeWidgetMap.put(769, new AbsListViewWidget());
        this.themeWidgetMap.put(768, new RecycleViewWidget());
        this.themeWidgetMap.put(767, new SingleViewWidget());

        this.themeObserverSet = new HashSet<>();
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
        sThemeManager.isNightTheme = false;
    }

    /**
     * 添加自定义的AbstractThemeWidget
     *
     * @param themeWidgetType 类型索引值
     * @param themeWidget     自定义的AbstractThemeWidget
     */
    public void add(int themeWidgetType, AbstractThemeWidget themeWidget) {
        this.themeWidgetMap.put(themeWidgetType, themeWidget);
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

                TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.ThemeWidget);
                int themeWidgetType = typeArray.getInt(THEME_WIDGET_TYPE_INDEX, -1);
                assembleViewThemeElement(view, attrs, themeWidgetType);
                typeArray.recycle();

                return view;
            }
        });
    }

    public void assembleViewHolderThemeElement(View view) {
        if (view == null) {
            return;
        }

        view.setTag(R.id.tag_theme_widget_type, 767);
        view.setTag(R.id.tag_theme_is_night, isNightTheme);
    }

    /**
     * 组装每个View的主题元素
     */
    private void assembleViewThemeElement(View view, AttributeSet attributeSet, int widgetType) {

        Log.d(TAG, "assembleViewThemeElement  theme widget type " + widgetType + " view:" + view);
        if (view == null || widgetType < 0) {
            return;
        }

        IThemeWidget themeWidget = this.themeWidgetMap.get(widgetType);
        if (themeWidget != null) {
            view.setTag(R.id.tag_theme_widget_type, widgetType);
            themeWidget.assemble(view, attributeSet);
            if (themeWidget instanceof SingleViewWidget) {
                view.setTag(R.id.tag_theme_is_night, isNightTheme);
            }

            Log.d(TAG, "assembleViewThemeElement  theme widget type " + widgetType + " view:" + view);
            debugAttributes(attributeSet);

        } else {
            view.setTag(R.id.tag_theme_widget_type, -1);
            Log.d(TAG, "unsupported theme widget type " + widgetType + ",is your custom theme widget?");
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
    public boolean changeNightTheme() {
        Log.d(TAG, "changeNightTheme isNightTheme=" + isNightTheme);
        isNightTheme = !isNightTheme;

        //setAppNightTheme(this.context, isNightTheme);

        for (IThemeObserver themeObserver : themeObserverSet) {
            themeObserver.onThemeChanged(isNightTheme);
        }

        return isNightTheme;
    }

    public boolean isNightTheme() {
        return this.isNightTheme;
    }

    /**
     * 改变主题，在默认主题与夜间主题之间进行切换
     *
     * @param activity   Activity
     * @param themeResId 主题资源id
     */
    public void applyTheme(Activity activity, @StyleRes int themeResId) {
        activity.setTheme(themeResId);
        applyTheme(activity, activity.getTheme(), activity.getResources());
    }

    private void applyTheme(Activity activity, Resources.Theme theme, Resources resources) {
        Log.d(TAG, "applyThemeForActivity");
        if (activity == null || theme == null || resources == null) {
            throw new IllegalArgumentException("activity theme resources,someone is null!");
        }

        View decorView = activity.getWindow().getDecorView();
        applyTheme(decorView, theme, resources);

    }

    /**
     * 改变View的主题，应用场景要是AdapterView以及动态创建的View。
     *
     * @param context 上下文
     * @param view    ItemView
     */
    public void applyThemeForView(Context context, View view) {
        boolean viewIsNightTheme = false;
        try {
            viewIsNightTheme = ThemeUtils.getViewTag(view, R.id.tag_theme_is_night);
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "applyThemeForViewHolder viewIsNightTheme:" + viewIsNightTheme + " isNightTheme:" + isNightTheme);
        if (viewIsNightTheme != isNightTheme) {
            Resources.Theme theme = context.getTheme();
            Resources resources = context.getResources();
            applyTheme(view, theme, resources);
        }
        Log.d(TAG, "applyThemeForViewHolder end" + isNightTheme);
    }

    /**
     * 对单个View应用主题
     *
     * @param view      View
     * @param theme     主题
     * @param resources 资源
     */
    private void applyTheme(View view, Resources.Theme theme, Resources resources) {

        Object object = view.getTag(R.id.tag_theme_widget_type);
        int themeWidgetType = -1;
        if (object instanceof Integer) {
            themeWidgetType = (int) object;
        }

        Log.d(TAG, "applyTheme  theme widget type " + themeWidgetType + " view:" + view);
        if (themeWidgetType > 0) {
            IThemeWidget themeWidget = this.themeWidgetMap.get(themeWidgetType);
            if (themeWidget != null) {
                themeWidget.applyTheme(theme, resources, view);
                if (themeWidget instanceof SingleViewWidget) {
                    view.setTag(R.id.tag_theme_is_night, isNightTheme);
                }
                Log.d(TAG, "applyTheme  theme widget type " + themeWidgetType + " view:" + view + " themeWidget:" + themeWidget);
            } else {
                Log.d(TAG, "applyTheme unsupport theme widget type:" + themeWidgetType + " view:" + view);
            }
        }

        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                applyTheme(child, theme, resources);
            }
        }
    }

}
