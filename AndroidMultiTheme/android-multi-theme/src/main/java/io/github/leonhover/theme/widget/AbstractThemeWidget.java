package io.github.leonhover.theme.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.AnyRes;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.View;

import java.util.HashSet;
import java.util.Set;

import io.github.leonhover.theme.MultiTheme;
import io.github.leonhover.theme.R;
import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.annotation.MultiThemeAttrs;

import static io.github.leonhover.theme.ThemeUtils.getAttrResId;
import static io.github.leonhover.theme.ThemeUtils.isAttrReference;

/**
 * Created by leonhover on 16-9-27.
 */

public abstract class AbstractThemeWidget implements IThemeWidget {

    public static final String TAG = AbstractThemeWidget.class.getSimpleName();

    /**
     * 主题元素集合
     */
    private Set<Integer> themeElementKeySet = new HashSet<>();

    public AbstractThemeWidget() {
        initializeAppThemeElements(this.getClass());
        initializeLibraryElements();
    }

    /**
     * 初始化应用或FrameWork的attr属性作为Key的主题元素
     */
    private void initializeAppThemeElements(Class<?> themeWidgetClass) {
        if (themeWidgetClass != null && AbstractThemeWidget.class.isAssignableFrom(themeWidgetClass)) {
            Class<?> superClass = themeWidgetClass.getSuperclass();
            initializeAppThemeElements(superClass);

            MultiThemeAttrs multiThemeAttrs = themeWidgetClass.getAnnotation(MultiThemeAttrs.class);
            if (multiThemeAttrs != null) {
                for (int attrRes : multiThemeAttrs.value()) {
                    themeElementKeySet.add(attrRes);
                }
            }
        }
    }

    /**
     * 仅供MultiThemeLibrary来初始一些不能直接当做常量引用的attr属性主题元素Key。
     */
    protected void initializeLibraryElements() {

    }

    protected final void addThemeElementKey(@AttrRes int themeElementKey) {
        themeElementKeySet.add(themeElementKey);
    }

    /**
     * 添加主题支持的Attribute Resource Id;
     *
     * @param themeElementKey Attribute Ressurce Id
     */
    public final void add(@AttrRes int themeElementKey) {
        themeElementKeySet.add(themeElementKey);
    }

    @Override
    public void assemble(View view, AttributeSet attributeSet) {
        MultiTheme.d(TAG, "assemble");
        if (themeElementKeySet == null || themeElementKeySet.size() < 1) {
            return;
        }

        SparseIntArray themeElementPairs = getThemeElementPairs(view);

        int count = attributeSet.getAttributeCount();

        for (int themeElementKey : themeElementKeySet) {
            for (int i = 0; i < count; i++) {
                if (themeElementKey == attributeSet.getAttributeNameResource(i)) {
                    String attrValue = attributeSet.getAttributeValue(i);
                    int attrId = -1;
                    if (isAttrReference(attrValue)) {
                        attrId = getAttrResId(attrValue);
                        themeElementPairs.put(themeElementKey, attrId);
                        break;
                    }
                }
            }
        }

    }

    @Override
    public void applyTheme(View view) {
        MultiTheme.d(TAG, "applyTheme");
        if (view == null) {
            throw new IllegalArgumentException(" view is illegal!!");
        }

        if (themeElementKeySet == null || themeElementKeySet.size() < 1) {
            return;
        }

        SparseIntArray themeElements = getThemeElementPairs(view);

        for (int i = 0; i < themeElements.size(); i++) {
            int attrResSupported = themeElements.keyAt(i);
            int attrResId = themeElements.get(attrResSupported);

            if (attrResId > 0) {
                applySingleElementTheme(view, attrResSupported, attrResId);
            }
        }
    }

    @Override
    public void applyStyle(View view, @StyleRes int styleRes) {

        if (view == null) {
            throw new IllegalArgumentException(" view is illegal!!");
        }

        if (themeElementKeySet == null || themeElementKeySet.size() < 1) {
            return;
        }

        for (Integer themeElementKey : themeElementKeySet) {

            TypedArray typedArray = view.getContext().obtainStyledAttributes(styleRes, new int[]{themeElementKey});
            if (typedArray != null && typedArray.getIndexCount() > 0) {
                TypedValue typedValue = new TypedValue();
                typedArray.getValue(0, typedValue);
                if (typedValue.resourceId > 0) {
                    applyElementTheme(view, themeElementKey, typedValue.resourceId);
                }
            }

            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

    /**
     * 应用单个主题元素
     *
     * @param view              View
     * @param themeElementKey   主题元素Key
     * @param themeElementValue Attr资源ID
     */
    public void applySingleElementTheme(View view, @AttrRes int themeElementKey, @AttrRes int themeElementValue) {
        SparseIntArray themeElementPairs = getThemeElementPairs(view);
        themeElementPairs.put(themeElementKey, themeElementValue);
        TypedValue typedValue = new TypedValue();
        view.getContext().getTheme().resolveAttribute(themeElementValue, typedValue, true);
        if (typedValue.resourceId > 0) {
            applyElementTheme(view, themeElementKey, typedValue.resourceId);
        }
    }

    /**
     * 移除View的单个主题元素，移除的主题元素将不再跟随主题变化
     *
     * @param view            View
     * @param themeElementKey 主题元素Key
     */
    public void removeSingleElementTheme(View view, @AttrRes int themeElementKey) {
        SparseIntArray themeElementPairs = getThemeElementPairs(view);
        int index = themeElementPairs.indexOfKey(themeElementKey);
        if (index >= 0) {
            themeElementPairs.removeAt(index);
        }
    }

    /**
     * 应用单个主题元素
     *
     * @param view            View
     * @param themeElementKey 主题元素
     * @param resId           资源ID
     */
    protected void applyElementTheme(View view, @AttrRes int themeElementKey, @AnyRes int resId) {

    }

    /**
     * 获取单个View的所有主题元素键值对
     *
     * @param view View
     * @return SparseIntArray
     */
    private static SparseIntArray getThemeElementPairs(View view) {

        SparseIntArray themeElementPairs = (SparseIntArray) view.getTag(R.id.amt_tag_theme_element_pairs);

        if (themeElementPairs == null) {
            themeElementPairs = new SparseIntArray();
            view.setTag(R.id.amt_tag_theme_element_pairs, themeElementPairs);
        }

        return themeElementPairs;
    }

    public static Drawable getDrawable(View view, @DrawableRes int drawableResId) {
        return ThemeUtils.getDrawableWithResId(view.getContext(), drawableResId);
    }

    public static int getColor(View view, @ColorRes int colorResId) {
        return ThemeUtils.getColorWithResId(view.getContext(), colorResId);
    }

    public static ColorStateList getColorStateList(View view, @ColorRes int colorStateListResId) {
        return ThemeUtils.getColorStateListWithResId(view.getContext(), colorStateListResId);
    }
}
