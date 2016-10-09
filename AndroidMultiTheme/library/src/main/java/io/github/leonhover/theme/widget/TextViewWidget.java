package io.github.leonhover.theme.widget;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import io.github.leonhover.theme.R;
import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.model.ThemeElement;

/**
 * Created by leonhover on 16-9-27.
 */

public class TextViewWidget extends ViewWidget {

    public TextViewWidget() {
        super();
        ThemeElement element;
        element = new ThemeElement(R.id.tag_theme_widget_color_01, "textColor");
        add(element);
        element = new ThemeElement(R.id.tag_theme_widget_color_02, "textColorHint");
        add(element);
        element = new ThemeElement(R.id.tag_theme_widget_color_03, "textColorLink");
        add(element);
        element = new ThemeElement(R.id.tag_theme_widget_drawable_02, "drawableBottom");
        add(element);
        element = new ThemeElement(R.id.tag_theme_widget_drawable_03, "drawableLeft");
        add(element);
        element = new ThemeElement(R.id.tag_theme_widget_drawable_04, "drawableRight");
        add(element);
        element = new ThemeElement(R.id.tag_theme_widget_drawable_05, "drawableTop");
        add(element);
    }

    @Override
    public void appleElementTheme(Resources.Theme theme, Resources resources, View view, ThemeElement element, int attrResId) {
        super.appleElementTheme(theme, resources, view, element, attrResId);
        switch (element.getTagKey()) {
            case R.id.tag_theme_widget_color_01:
                setTextColor(theme, resources, view, attrResId);
                break;
            case R.id.tag_theme_widget_color_02:
                setTextColorHint(theme, resources, view, attrResId);
                break;
            case R.id.tag_theme_widget_color_03:
                setTextColorLink(theme, resources, view, attrResId);
                break;
            case R.id.tag_theme_widget_drawable_02:
                setDrawableBottom(theme, resources, view, attrResId);
                break;
            case R.id.tag_theme_widget_drawable_03:
                setDrawableLeft(theme, resources, view, attrResId);
                break;
            case R.id.tag_theme_widget_drawable_04:
                setDrawableRight(theme, resources, view, attrResId);
                break;
            case R.id.tag_theme_widget_drawable_05:
                setDrawableTop(theme, resources, view, attrResId);
                break;
        }
    }

    private void setTextColor(Resources.Theme theme, Resources resources, View view, int attrResId) {
        ((TextView) view).setTextColor(ThemeUtils.getColorStateList(theme, resources, attrResId));
    }

    private void setTextColorLink(Resources.Theme theme, Resources resources, View view, int attrResId) {
        ((TextView) view).setLinkTextColor(ThemeUtils.getColorStateList(theme, resources, attrResId));
    }

    private void setTextColorHint(Resources.Theme theme, Resources resources, View view, int attrResId) {
        ((TextView) view).setHintTextColor(ThemeUtils.getColorStateList(theme, resources, attrResId));
    }

    private void setDrawableBottom(Resources.Theme theme, Resources resources, View view, int attrResId) {
        Drawable drawable = ThemeUtils.getDrawable(theme, resources, attrResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        ((TextView) view).setCompoundDrawables(null, null, null, drawable);
    }

    private void setDrawableLeft(Resources.Theme theme, Resources resources, View view, int attrResId) {
        Drawable drawable = ThemeUtils.getDrawable(theme, resources, attrResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        ((TextView) view).setCompoundDrawables(drawable, null, null, null);
    }

    private void setDrawableRight(Resources.Theme theme, Resources resources, View view, int attrResId) {
        Drawable drawable = ThemeUtils.getDrawable(theme, resources, attrResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        ((TextView) view).setCompoundDrawables(null, null, drawable, null);
    }

    private void setDrawableTop(Resources.Theme theme, Resources resources, View view, int attrResId) {
        Drawable drawable = ThemeUtils.getDrawable(theme, resources, attrResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        ((TextView) view).setCompoundDrawables(null, drawable, null, null);
    }
}
