package io.github.leonhover.theme.widget;

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


    private static final String ATTR_NAME_TEXT_COLOR = "textColor";
    private static final String ATTR_NAME_TEXT_COLOR_HINT = "textColorHint";
    private static final String ATTR_NAME_TEXT_COLOR_LINK = "textColorLink";
    private static final String ATTR_NAME_DRAWABLE_BOTTOM = "drawableBottom";
    private static final String ATTR_NAME_DRAWABLE_LEFT = "drawableLeft";
    private static final String ATTR_NAME_DRAWABLE_RIGHT = "drawableRight";
    private static final String ATTR_NAME_DRAWABLE_TOP = "drawableTop";


    public TextViewWidget(Class master) {
        super(master);
    }

    @Override
    protected void initializeElements() {
        super.initializeElements();
        ThemeElement element;
        element = new ThemeElement(R.id.amt_tag_text_view_text_color, ATTR_NAME_TEXT_COLOR);
        add(element);
        element = new ThemeElement(R.id.amt_tag_text_view_text_color_hint, ATTR_NAME_TEXT_COLOR_HINT);
        add(element);
        element = new ThemeElement(R.id.amt_tag_text_view_text_color_link, ATTR_NAME_TEXT_COLOR_LINK);
        add(element);
        element = new ThemeElement(R.id.amt_tag_text_view_drawable_top, ATTR_NAME_DRAWABLE_BOTTOM);
        add(element);
        element = new ThemeElement(R.id.amt_tag_text_view_drawable_left, ATTR_NAME_DRAWABLE_LEFT);
        add(element);
        element = new ThemeElement(R.id.amt_tag_text_view_drawable_right, ATTR_NAME_DRAWABLE_RIGHT);
        add(element);
        element = new ThemeElement(R.id.amt_tag_text_view_drawable_top, ATTR_NAME_DRAWABLE_TOP);
        add(element);
    }

    @Override
    public void appleElementTheme(View view, ThemeElement element, int attrResId) {
        super.appleElementTheme(view, element, attrResId);
        if (R.id.amt_tag_text_view_text_color == element.getTagKey()) {
            setTextColor(view, attrResId);
        } else if (R.id.amt_tag_text_view_text_color_hint == element.getTagKey()) {
            setTextColorHint(view, attrResId);
        } else if (R.id.amt_tag_text_view_text_color_link == element.getTagKey()) {
            setTextColorLink(view, attrResId);
        } else if (R.id.amt_tag_text_view_drawable_top == element.getTagKey()) {
            setDrawableTop(view, attrResId);
        } else if (R.id.amt_tag_text_view_drawable_bottom == element.getTagKey()) {
            setDrawableBottom(view, attrResId);
        } else if (R.id.amt_tag_text_view_drawable_left == element.getTagKey()) {
            setDrawableLeft(view, attrResId);
        } else if (R.id.amt_tag_text_view_drawable_right == element.getTagKey()) {
            setDrawableRight(view, attrResId);
        }
    }

    private void setTextColor(View view, int attrResId) {
        ((TextView) view).setTextColor(ThemeUtils.getColorStateList(view.getContext(), attrResId));
    }

    private void setTextColorLink(View view, int attrResId) {
        ((TextView) view).setLinkTextColor(ThemeUtils.getColorStateList(view.getContext(), attrResId));
    }

    private void setTextColorHint(View view, int attrResId) {
        ((TextView) view).setHintTextColor(ThemeUtils.getColorStateList(view.getContext(), attrResId));
    }

    private void setDrawableBottom(View view, int attrResId) {
        Drawable drawable = ThemeUtils.getDrawable(view.getContext(), attrResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        ((TextView) view).setCompoundDrawables(null, null, null, drawable);
    }

    private void setDrawableLeft(View view, int attrResId) {
        Drawable drawable = ThemeUtils.getDrawable(view.getContext(), attrResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        ((TextView) view).setCompoundDrawables(drawable, null, null, null);
    }

    private void setDrawableRight(View view, int attrResId) {
        Drawable drawable = ThemeUtils.getDrawable(view.getContext(), attrResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        ((TextView) view).setCompoundDrawables(null, null, drawable, null);
    }

    private void setDrawableTop(View view, int attrResId) {
        Drawable drawable = ThemeUtils.getDrawable(view.getContext(), attrResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        ((TextView) view).setCompoundDrawables(null, drawable, null, null);
    }
}
