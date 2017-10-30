package io.github.leonhover.theme.widget;

import android.graphics.drawable.Drawable;
import android.support.annotation.AnyRes;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.Toolbar;
import android.view.View;

import io.github.leonhover.theme.R;

/**
 * Created by wangzongliang on 2016/10/22.
 */
public class ToolBarWidget extends ViewWidget {

    @Override
    protected void initializeLibraryElements() {
        super.initializeLibraryElements();
        addThemeElementKey(R.attr.logo);
        addThemeElementKey(R.attr.navigationIcon);
        addThemeElementKey(R.attr.subtitleTextColor);
        addThemeElementKey(R.attr.titleTextColor);
    }

    @Override
    public void applyElementTheme(View view, @AttrRes int themeElementKey, @AnyRes int resId) {
        super.applyElementTheme(view, themeElementKey, resId);
        Toolbar toolbar = (Toolbar) view;
        if (android.R.attr.logo == themeElementKey) {
            setLogo(toolbar, resId);
        } else if (R.attr.subtitleTextColor == themeElementKey) {
            setSubTitleTextColor(toolbar, resId);
        } else if (R.attr.navigationIcon == themeElementKey) {
            setNavigationIcon(toolbar, resId);
        } else if (R.attr.titleTextColor == themeElementKey) {
            setTitleTextColor(toolbar, resId);
        }
    }

    public static void setLogo(Toolbar toolBar, @DrawableRes int drawableResId) {
        if (toolBar == null) {
            return;
        }

        Drawable logoDrawable = getDrawable(toolBar, drawableResId);
        toolBar.setLogo(logoDrawable);
    }

    public static void setNavigationIcon(Toolbar toolBar, @DrawableRes int drawableResId) {
        if (toolBar == null) {
            return;
        }

        Drawable iconDrawable = getDrawable(toolBar, drawableResId);
        toolBar.setNavigationIcon(iconDrawable);
    }

    public static void setTitleTextColor(Toolbar toolBar, @ColorRes int colorResId) {

        if (toolBar == null) {
            return;
        }

        toolBar.setTitleTextColor(getColor(toolBar, colorResId));
    }

    public static void setSubTitleTextColor(Toolbar toolBar, @ColorRes int colorResId) {

        if (toolBar == null) {
            return;
        }

        toolBar.setSubtitleTextColor(getColor(toolBar, colorResId));
    }
}
