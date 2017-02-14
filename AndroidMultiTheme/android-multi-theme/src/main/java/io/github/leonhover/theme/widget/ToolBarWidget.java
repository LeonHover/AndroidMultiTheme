package io.github.leonhover.theme.widget;

import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.v7.widget.Toolbar;
import android.view.View;

import io.github.leonhover.theme.R;
import io.github.leonhover.theme.ThemeUtils;

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
    public void appleElementTheme(View view, @AttrRes int themeElementKey, @AttrRes int themeElementValue) {
        super.appleElementTheme(view, themeElementKey, themeElementValue);
        Toolbar toolbar = (Toolbar) view;
        if (android.R.attr.logo == themeElementKey) {
            setLogo(toolbar, themeElementValue);
        } else if (R.attr.subtitleTextColor == themeElementKey) {
            setSubTitleTextColor(toolbar, themeElementValue);
        } else if (R.attr.navigationIcon == themeElementKey) {
            setNavigationIcon(toolbar, themeElementValue);
        } else if (R.attr.titleTextColor == themeElementKey) {
            setTitleTextColor(toolbar, themeElementValue);
        }
    }

    public static void setLogo(Toolbar toolBar, @AttrRes int attrResId) {
        if (toolBar == null) {
            return;
        }

        saveThemeElementPair(toolBar,R.attr.logo,attrResId);

        Drawable logoDrawable = ThemeUtils.getDrawable(toolBar.getContext(), attrResId);
        toolBar.setLogo(logoDrawable);
    }

    public static void setNavigationIcon(Toolbar toolBar, @AttrRes int attrResId) {
        if (toolBar == null) {
            return;
        }

        saveThemeElementPair(toolBar,R.attr.navigationIcon,attrResId);

        Drawable iconDrawable = ThemeUtils.getDrawable(toolBar.getContext(), attrResId);
        toolBar.setNavigationIcon(iconDrawable);
    }

    public static void setTitleTextColor(Toolbar toolBar, @AttrRes int attrResId) {

        if (toolBar == null) {
            return;
        }

        saveThemeElementPair(toolBar,R.attr.titleTextColor,attrResId);

        toolBar.setTitleTextColor(ThemeUtils.getColor(toolBar.getContext(), attrResId));
    }

    public static void setSubTitleTextColor(Toolbar toolBar, @AttrRes int attrResId) {

        if (toolBar == null) {
            return;
        }

        saveThemeElementPair(toolBar,R.attr.subtitleTextColor,attrResId);

        toolBar.setSubtitleTextColor(ThemeUtils.getColor(toolBar.getContext(), attrResId));
    }
}
