package io.github.leonhover.theme.widget;

import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


import java.util.HashSet;
import java.util.Set;

import io.github.leonhover.theme.model.ThemeElement;

import static io.github.leonhover.theme.ThemeUtils.ANDROID_NAMESPACE;
import static io.github.leonhover.theme.ThemeUtils.getAttrResId;
import static io.github.leonhover.theme.ThemeUtils.isAttrReference;

/**
 * Created by leonhover on 16-9-27.
 */

public abstract class AbstractThemeWidget implements IThemeWidget {

    public static final String TAG = AbstractThemeWidget.class.getSimpleName();

    /**
     * Android官方的NameSpace
     */
    private String nameSpace = ANDROID_NAMESPACE;

    /**
     * 主题元素集合
     */
    private Set<ThemeElement> elementSet;

    /**
     * 添加主题元素
     *
     * @param themeElement 主题元素
     */
    protected final void add(ThemeElement themeElement) {
        if (elementSet == null) {
            elementSet = new HashSet<>();
        }

        elementSet.add(themeElement);
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    @Override
    public void assemble(View view, AttributeSet attributeSet) {

        Log.d(TAG, "applyTheme");
        if (elementSet == null) {
            return;
        }

        for (ThemeElement element : elementSet) {
            Log.d(TAG, "assemble element:" + element);
            String attrValue = attributeSet.getAttributeValue(nameSpace, element.getAttrName());
            int attrId = -1;
            if (isAttrReference(attrValue)) {
                attrId = getAttrResId(attrValue);
                view.setTag(element.getTagKey(), attrId);
                Log.d(TAG, "assemble element:" + element + " attrId:" + attrId);
            }
        }

    }

    @Override
    public void applyTheme(Resources.Theme theme, Resources resources, View view) {
        Log.d(TAG, "applyTheme");
        if (theme == null || resources == null || view == null) {
            throw new IllegalArgumentException("check theme , resources, view, someone is illegal!!");
        }

        if (elementSet == null) {
            return;
        }

        for (ThemeElement element : elementSet) {
            Object tagValue = view.getTag(element.getTagKey());
            int attrResId = -1;
            if (tagValue instanceof Integer) {
                attrResId = (int) tagValue;
            }
            Log.d(TAG, "applyTheme element:" + element + " attrId:" + attrResId);
            if (attrResId > -1) {
                appleElementTheme(theme, resources, view, element, attrResId);
            }
        }
    }

    @Override
    public boolean isAdapterView() {
        return false;
    }

    /**
     * 应用单个主题元素
     *
     * @param theme     主题
     * @param resources 资源
     * @param view      View
     * @param element   主题元素
     * @param attrResId AttrResId
     */
    public abstract void appleElementTheme(Resources.Theme theme, Resources resources, View view, ThemeElement element, int attrResId);



}
