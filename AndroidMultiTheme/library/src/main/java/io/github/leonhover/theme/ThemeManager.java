package io.github.leonhover.theme;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.StyleRes;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
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
import java.util.HashSet;
import java.util.Iterator;
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
import io.github.leonhover.theme.widget.SeekBarWidget;
import io.github.leonhover.theme.widget.TextViewWidget;
import io.github.leonhover.theme.widget.ViewWidget;


/**
 * Created by leonhover on 16-9-26.
 */

public class ThemeManager {

    public static final String TAG = ThemeManager.class.getSimpleName();

    private Map<String, AbstractThemeWidget> themeWidgetMap;

    private ThemeViewCreator themeViewCreator;

    private int currentThemeIndex = -1;

    private static ThemeManager sThemeManager;

    private Context context;

    private static boolean isDebug = true;


    private Set<IThemeObserver> themeObserverSet;

    private ThemeManager() {
        this.themeWidgetMap = new HashMap<>();
        this.themeWidgetMap.put(View.class.getSimpleName(), new ViewWidget(View.class));
        this.themeWidgetMap.put(TextView.class.getSimpleName(), new TextViewWidget(TextView.class));
        this.themeWidgetMap.put(ImageView.class.getSimpleName(), new ImageViewWidget(ImageView.class));
        this.themeWidgetMap.put(CompoundButton.class.getSimpleName(), new CompoundButtonWidget(CompoundButton.class));
        this.themeWidgetMap.put(ProgressBar.class.getSimpleName(), new ProgressBarWidget(ProgressBar.class));
        this.themeWidgetMap.put(ListView.class.getSimpleName(), new ListViewWidget(ListView.class));
        this.themeWidgetMap.put(SeekBar.class.getSimpleName(), new SeekBarWidget(SeekBar.class));
        this.themeWidgetMap.put(LinearLayout.class.getSimpleName(), new LinearLayoutWidget(LinearLayout.class));
        this.themeWidgetMap.put(AbsListView.class.getSimpleName(), new AbsListViewWidget(AbsListView.class));

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
        sThemeManager.currentThemeIndex = -1;
    }

    /**
     * 添加自定义的AbstractThemeWidget
     *
     * @param themeWidgetType 类型索引值
     * @param themeWidget     自定义的AbstractThemeWidget
     */
    public void add(String themeWidgetType, AbstractThemeWidget themeWidget) {
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

                String themeWidgetType = findProperThemeWidgetType(view);
                assembleViewThemeElement(view, attrs, themeWidgetType);

                return view;
            }
        });
    }

    private String findProperThemeWidgetType(View view) {

        if (view == null) {
            return null;
        }

        Log.d(TAG, "findProperThemeWidgetType " + view.getClass().getSimpleName());

        Class<?> tmpMaster = null;
        for (AbstractThemeWidget themeWidget : this.themeWidgetMap.values()) {
            Class<?> clazz = themeWidget.getMaster();

            if (view.getClass().equals(themeWidget.getMaster())) {
                return view.getClass().getSimpleName();
            }

            if (clazz.isAssignableFrom(view.getClass())) {
                if (tmpMaster == null) {
                    tmpMaster = clazz;
                } else {
                    if (tmpMaster.isAssignableFrom(themeWidget.getMaster())) {
                        tmpMaster = themeWidget.getMaster();
                    }
                }
            }
        }

        if (tmpMaster == null) {
            return null;
        } else {
            Log.d(TAG, "findProperThemeWidgetType result:" + tmpMaster.getSimpleName());
            return tmpMaster.getSimpleName();
        }

    }

    /**
     * 组装每个View的主题元素
     */
    private void assembleViewThemeElement(View view, AttributeSet attributeSet, String widgetType) {

        Log.d(TAG, "assembleViewThemeElement  theme widget type:" + widgetType + ", view:" + view);
        if (view == null || TextUtils.isEmpty(widgetType)) {
            return;
        }

        IThemeWidget themeWidget = this.themeWidgetMap.get(widgetType);
        if (themeWidget != null) {
            view.setTag(R.id.amt_tag_widget_type, widgetType);
            themeWidget.assemble(view, attributeSet);
            Log.d(TAG, "assembleViewThemeElement  theme widget type: " + widgetType + " themeWidget:" + themeWidget.getClass().getSimpleName());
            debugAttributes(attributeSet);

        } else {
            view.setTag(R.id.amt_tag_widget_type, null);
            Log.i(TAG, "unsupported theme widget type " + widgetType + ",is your custom theme widget?");
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
     * 改变View的主题，应用场景要是AdapterView以及动态创建的View。
     *
     * @param view ItemView
     */
    public void applyThemeForView(View view) {
        int themeOfView = -1;
        try {
            themeOfView = ThemeUtils.getViewTag(view, R.id.amt_tag_view_current_theme);
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "applyThemeForViewHolder viewIsNightTheme:" + themeOfView + " currentThemeIndex:" + currentThemeIndex);
        if (themeOfView != currentThemeIndex) {
            applyTheme(view);
        }
    }

    /**
     * 对单个View应用主题
     *
     * @param view View
     */
    private void applyTheme(View view) {

        Object object = view.getTag(R.id.amt_tag_widget_type);
        String themeWidgetType = "";
        if (object instanceof String) {
            themeWidgetType = (String) object;
        }

        Log.d(TAG, "applyTheme  theme widget type:" + themeWidgetType + " ,view:" + view);
        if (!TextUtils.isEmpty(themeWidgetType)) {
            IThemeWidget themeWidget = this.themeWidgetMap.get(themeWidgetType);
            if (themeWidget != null) {
                themeWidget.applyTheme(view);
                Log.d(TAG, "applyTheme  theme widget type: " + themeWidgetType + " ,view:" + view + " themeWidget:" + themeWidget);
            } else {
                Log.i(TAG, "applyTheme unsupport theme widget type:" + themeWidgetType + ", view:" + view);
            }
        }

        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                applyTheme(child);
            }
        }
    }

}
