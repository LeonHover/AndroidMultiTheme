package io.github.leonhover.theme.widget;

import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.view.View;
import android.widget.TextView;

import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.annotation.MultiThemeAttrs;

/**
 * Created by leonhover on 16-9-27.
 */
@MultiThemeAttrs({
        android.R.attr.textColor,
        android.R.attr.textColorHint,
        android.R.attr.textColorLink,
        android.R.attr.drawableBottom,
        android.R.attr.drawableTop,
        android.R.attr.drawableLeft,
        android.R.attr.drawableRight,
})
public class TextViewWidget extends ViewWidget {

    @Override
    public void appleElementTheme(View view, @AttrRes int themeElementKey, @AttrRes int themeElementValue) {
        super.appleElementTheme(view, themeElementKey, themeElementValue);
        TextView textView = (TextView) view;
        switch (themeElementKey) {
            case android.R.attr.textColor:
                setTextColor(textView, themeElementValue);
                break;
            case android.R.attr.textColorHint:
                setTextColorHint(textView, themeElementValue);
                break;
            case android.R.attr.textColorLink:
                setTextColorLink(textView, themeElementValue);
                break;
            case android.R.attr.drawableBottom:
                setDrawableBottom(textView, themeElementValue);
                break;
            case android.R.attr.drawableTop:
                setDrawableTop(textView, themeElementValue);
                break;
            case android.R.attr.drawableLeft:
                setDrawableLeft(textView, themeElementValue);
                break;
            case android.R.attr.drawableRight:
                setDrawableRight(textView, themeElementValue);
                break;
            default:
        }
    }

    public static void setTextColor(TextView textView, @AttrRes int attrResId) {
        if (textView == null) {
            return;
        }

        saveThemeElementPair(textView, android.R.attr.textColor, attrResId);

        textView.setTextColor(ThemeUtils.getColorStateList(textView.getContext(), attrResId));
    }

    public static void setTextColorLink(TextView textView, @AttrRes int attrResId) {
        if (textView == null) {
            return;
        }

        saveThemeElementPair(textView, android.R.attr.textColorLink, attrResId);

        textView.setLinkTextColor(ThemeUtils.getColorStateList(textView.getContext(), attrResId));
    }

    public static void setTextColorHint(TextView textView, @AttrRes int attrResId) {
        if (textView == null) {
            return;
        }
        saveThemeElementPair(textView, android.R.attr.textColorHint, attrResId);

        textView.setHintTextColor(ThemeUtils.getColorStateList(textView.getContext(), attrResId));
    }

    public static void setDrawableBottom(TextView textView, @AttrRes int attrResId) {
        if (textView == null) {
            return;
        }

        saveThemeElementPair(textView, android.R.attr.drawableBottom, attrResId);

        Drawable drawable = ThemeUtils.getDrawable(textView.getContext(), attrResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, null, null, drawable);
    }

    public static void setDrawableLeft(TextView textView, @AttrRes int attrResId) {
        if (textView == null) {
            return;
        }

        saveThemeElementPair(textView, android.R.attr.drawableLeft, attrResId);
        Drawable drawable = ThemeUtils.getDrawable(textView.getContext(), attrResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    public static void setDrawableRight(TextView textView, @AttrRes int attrResId) {
        if (textView == null) {
            return;
        }

        saveThemeElementPair(textView, android.R.attr.drawableRight, attrResId);
        Drawable drawable = ThemeUtils.getDrawable(textView.getContext(), attrResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, null, drawable, null);
    }

    public static void setDrawableTop(TextView textView, @AttrRes int attrResId) {
        if (textView == null) {
            return;
        }

        saveThemeElementPair(textView, android.R.attr.drawableTop, attrResId);
        Drawable drawable = ThemeUtils.getDrawable(textView.getContext(), attrResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, drawable, null, null);
    }
}
