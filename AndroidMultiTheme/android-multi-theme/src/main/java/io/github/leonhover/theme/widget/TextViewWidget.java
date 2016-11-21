package io.github.leonhover.theme.widget;

import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.util.Log;
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


    public TextViewWidget() {
        super();
    }

    @Override
    protected void initializeElements() {
        super.initializeElements();
        add(new ThemeElement(R.id.amt_tag_text_view_text_color, ATTR_NAME_TEXT_COLOR));
        add(new ThemeElement(R.id.amt_tag_text_view_text_color_hint, ATTR_NAME_TEXT_COLOR_HINT));
        add(new ThemeElement(R.id.amt_tag_text_view_text_color_link, ATTR_NAME_TEXT_COLOR_LINK));
        add(new ThemeElement(R.id.amt_tag_text_view_drawable_bottom, ATTR_NAME_DRAWABLE_BOTTOM));
        add(new ThemeElement(R.id.amt_tag_text_view_drawable_left, ATTR_NAME_DRAWABLE_LEFT));
        add(new ThemeElement(R.id.amt_tag_text_view_drawable_right, ATTR_NAME_DRAWABLE_RIGHT));
        add(new ThemeElement(R.id.amt_tag_text_view_drawable_top, ATTR_NAME_DRAWABLE_TOP));
    }

    @Override
    public void appleElementTheme(View view, ThemeElement element, @AttrRes int attrResId) {
        super.appleElementTheme(view, element, attrResId);
        TextView textView = (TextView) view;
        if (R.id.amt_tag_text_view_text_color == element.getTagKey()) {
            setTextColor(textView, attrResId);
        } else if (R.id.amt_tag_text_view_text_color_hint == element.getTagKey()) {
            setTextColorHint(textView, attrResId);
        } else if (R.id.amt_tag_text_view_text_color_link == element.getTagKey()) {
            setTextColorLink(textView, attrResId);
        } else if (R.id.amt_tag_text_view_drawable_top == element.getTagKey()) {
            setDrawableTop(textView, attrResId);
        } else if (R.id.amt_tag_text_view_drawable_bottom == element.getTagKey()) {
            setDrawableBottom(textView, attrResId);
        } else if (R.id.amt_tag_text_view_drawable_left == element.getTagKey()) {
            setDrawableLeft(textView, attrResId);
        } else if (R.id.amt_tag_text_view_drawable_right == element.getTagKey()) {
            setDrawableRight(textView, attrResId);
        }
    }

    public void setTextColor(TextView textView, @AttrRes int attrResId) {
        if (textView == null) {
            return;
        }

        textView.setTag(R.id.amt_tag_text_view_text_color, attrResId);

        textView.setTextColor(ThemeUtils.getColorStateList(textView.getContext(), attrResId));
    }

    public void setTextColorLink(TextView textView, @AttrRes int attrResId) {
        if (textView == null) {
            return;
        }

        textView.setTag(R.id.amt_tag_text_view_text_color_link, attrResId);

        textView.setLinkTextColor(ThemeUtils.getColorStateList(textView.getContext(), attrResId));
    }

    public void setTextColorHint(TextView textView, @AttrRes int attrResId) {
        if (textView == null) {
            return;
        }

        textView.setTag(R.id.amt_tag_text_view_text_color_hint, attrResId);
        textView.setHintTextColor(ThemeUtils.getColorStateList(textView.getContext(), attrResId));
    }

    public void setDrawableBottom(TextView textView, @AttrRes int attrResId) {
        if (textView == null) {
            return;
        }

        textView.setTag(R.id.amt_tag_text_view_drawable_bottom, attrResId);

        Drawable drawable = ThemeUtils.getDrawable(textView.getContext(), attrResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, null, null, drawable);
    }

    public void setDrawableLeft(TextView textView, @AttrRes int attrResId) {
        if (textView == null) {
            return;
        }

        textView.setTag(R.id.amt_tag_text_view_drawable_left, attrResId);
        Drawable drawable = ThemeUtils.getDrawable(textView.getContext(), attrResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    public void setDrawableRight(TextView textView, @AttrRes int attrResId) {
        if (textView == null) {
            return;
        }

        textView.setTag(R.id.amt_tag_text_view_drawable_right, attrResId);
        Drawable drawable = ThemeUtils.getDrawable(textView.getContext(), attrResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, null, drawable, null);
    }

    public void setDrawableTop(TextView textView, @AttrRes int attrResId) {
        if (textView == null) {
            return;
        }

        textView.setTag(R.id.amt_tag_text_view_drawable_top, attrResId);
        Drawable drawable = ThemeUtils.getDrawable(textView.getContext(), attrResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, drawable, null, null);
    }
}
