package io.github.leonhover.theme.widget;

import android.graphics.drawable.Drawable;
import android.support.annotation.AnyRes;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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
    public void applyElementTheme(View view, @AttrRes int themeElementKey, @AnyRes int resId) {
        super.applyElementTheme(view, themeElementKey, resId);
        TextView textView = (TextView) view;
        switch (themeElementKey) {
            case android.R.attr.textColor:
                setTextColor(textView, resId);
                break;
            case android.R.attr.textColorHint:
                setTextColorHint(textView, resId);
                break;
            case android.R.attr.textColorLink:
                setTextColorLink(textView, resId);
                break;
            case android.R.attr.drawableBottom:
                setDrawableBottom(textView, resId);
                break;
            case android.R.attr.drawableTop:
                setDrawableTop(textView, resId);
                break;
            case android.R.attr.drawableLeft:
                setDrawableLeft(textView, resId);
                break;
            case android.R.attr.drawableRight:
                setDrawableRight(textView, resId);
                break;
            default:
        }
    }

    public static void setTextColor(TextView textView, @ColorRes int colorResId) {
        if (textView == null) {
            return;
        }
        Log.d(TAG, "setTextColor textColor:"+colorResId);
        textView.setTextColor(getColorStateList(textView, colorResId));
    }

    public static void setTextColorLink(TextView textView, @ColorRes int colorResId) {
        if (textView == null) {
            return;
        }

        textView.setLinkTextColor(getColorStateList(textView, colorResId));
    }

    public static void setTextColorHint(TextView textView, @ColorRes int colorResId) {
        if (textView == null) {
            return;
        }

        textView.setHintTextColor(getColorStateList(textView, colorResId));
    }

    public static void setDrawableBottom(TextView textView, @DrawableRes int drawableResId) {
        if (textView == null) {
            return;
        }

        Drawable drawable = getDrawable(textView, drawableResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, null, null, drawable);
    }

    public static void setDrawableLeft(TextView textView, @DrawableRes int drawableResId) {
        if (textView == null) {
            return;
        }

        Drawable drawable = getDrawable(textView, drawableResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    public static void setDrawableRight(TextView textView, @DrawableRes int drawableResId) {
        if (textView == null) {
            return;
        }

        Drawable drawable = getDrawable(textView, drawableResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, null, drawable, null);
    }

    public static void setDrawableTop(TextView textView, @DrawableRes int drawableResId) {
        if (textView == null) {
            return;
        }

        Drawable drawable = getDrawable(textView, drawableResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, drawable, null, null);
    }
}
