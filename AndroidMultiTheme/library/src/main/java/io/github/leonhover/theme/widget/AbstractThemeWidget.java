package io.github.leonhover.theme.widget;

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
     * 适合的主人Class
     */
    private Class properMaster;

    /**
     * 主题元素集合
     */
    private Set<ThemeElement> elementSet;

    public  AbstractThemeWidget(Class master){
        this.properMaster = master;
        initializeElements();
    }

    /**
     * 初始化主题的元素
     */
    protected abstract void initializeElements();

    public Class getMaster() {
        return this.properMaster;
    }

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
    public void applyTheme(View view) {
        Log.d(TAG, "applyTheme");
        if ( view == null) {
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
            Log.d(TAG, "applyTheme element:" + element + " attrId:" + attrResId);
            if (attrResId > -1) {
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
    public void appleElementTheme(View view, ThemeElement element, int attrResId){

    }



}
