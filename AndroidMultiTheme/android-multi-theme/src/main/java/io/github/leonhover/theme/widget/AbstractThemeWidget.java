package io.github.leonhover.theme.widget;

import android.support.annotation.AttrRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.HashSet;
import java.util.Set;

import io.github.leonhover.theme.model.ThemeElement;

import static io.github.leonhover.theme.ThemeUtils.ANDROID_NAMESPACE;
import static io.github.leonhover.theme.ThemeUtils.APP_NAMESPACE;
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
    private static String[] NAMESPACES = new String[]{ANDROID_NAMESPACE, APP_NAMESPACE};

    /**
     * 主题元素集合
     */
    private Set<ThemeElement> elementSet;

    public AbstractThemeWidget() {
        initializeElements();
    }

    /**
     * 初始化主题的元素
     */
    protected abstract void initializeElements();

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

    @Override
    public void assemble(View view, AttributeSet attributeSet) {

        Log.d(TAG, "applyTheme");
        if (elementSet == null) {
            return;
        }

//        int count = attributeSet.getAttributeCount();
//        for (int i = 0; i < count; i++) {
//            String attrName = attributeSet.getAttributeName(i);
//            Log.e(TAG, "assemble attributeSet.getAttributeName(" + i + ")   name:" + attrName);
//        }

        for (ThemeElement element : elementSet) {
            for (String nameSpace : NAMESPACES) {
                String attrValue = attributeSet.getAttributeValue(nameSpace, element.getAttrName());
                int attrId = -1;
                if (isAttrReference(attrValue)) {
                    attrId = getAttrResId(attrValue);
                    view.setTag(element.getTagKey(), attrId);
                    Log.d(TAG, "assemble element:" + element + " attrId:" + attrId);
                    break;
                }
            }
        }

    }

    @Override
    public void applyTheme(View view) {
        Log.d(TAG, "applyTheme");
        if (view == null) {
            throw new IllegalArgumentException(" view is illegal!!");
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
            if (attrResId > -1) {
                Log.d(TAG, "applyTheme element:" + element + " attrId:" + attrResId);
                appleElementTheme(view, element, attrResId);
            }
        }
    }

    /**
     * 应用单个主题元素
     *
     * @param view      View
     * @param element   主题元素
     * @param attrResId AttrResId
     */
    public void appleElementTheme(View view, ThemeElement element, @AttrRes int attrResId) {

    }


}
