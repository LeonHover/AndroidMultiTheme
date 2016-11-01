package io.github.leonhover.theme.widget;

import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.v7.widget.Toolbar;
import android.view.View;

import io.github.leonhover.theme.R;
import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.model.ThemeElement;

/**
 * Created by wangzongliang on 2016/10/22.
 */

public class ToolBarWidget extends ViewWidget {

    //    private final static String ATTR_NAME_COLLAPSE_ICON = "collapseIcon";
    private final static String ATTR_NAME_LOGO = "logo";
    private final static String ATTR_NAME_NAVIGATION_ICON = "navigationIcon";
    private final static String ATTR_NAME_SUBTITLE_TEXT_COLOR = "subtitleTextColor";
    private final static String ATTR_NAME_TITLE_TEXT_COLOR = "titleTextColor";

    public ToolBarWidget() {
        super();
    }

    @Override
    protected void initializeElements() {
        super.initializeElements();
        add(new ThemeElement(R.id.amt_tag_tool_bar_logo, ATTR_NAME_LOGO));
        add(new ThemeElement(R.id.amt_tag_tool_bar_navigation_icon, ATTR_NAME_NAVIGATION_ICON));
        add(new ThemeElement(R.id.amt_tag_tool_bar_subtitle_text_color, ATTR_NAME_SUBTITLE_TEXT_COLOR));
        add(new ThemeElement(R.id.amt_tag_tool_bar_title_text_color, ATTR_NAME_TITLE_TEXT_COLOR));
    }

    @Override
    public void appleElementTheme(View view, ThemeElement element, @AttrRes int attrResId) {
        super.appleElementTheme(view, element, attrResId);
        Toolbar toolbar = (Toolbar) view;
        if (R.id.amt_tag_tool_bar_overflow_icon == element.getTagKey()) {
            setOverflowIcon(toolbar, attrResId);
        } else if (R.id.amt_tag_tool_bar_logo == element.getTagKey()) {
            setLogo(toolbar, attrResId);
        } else if (R.id.amt_tag_tool_bar_subtitle_text_color == element.getTagKey()) {
            setSubTitleTextColor(toolbar, attrResId);
        } else if (R.id.amt_tag_tool_bar_navigation_icon == element.getTagKey()) {
            setNavigationIcon(toolbar, attrResId);
        } else if (R.id.amt_tag_tool_bar_title_text_color == element.getTagKey()) {
            setTitleTextColor(toolbar, attrResId);
        }
    }

    public void setLogo(Toolbar toolBar, @AttrRes int attrResId) {
        if (toolBar == null) {
            return;
        }

        toolBar.setTag(R.id.amt_tag_tool_bar_logo, attrResId);

        Drawable logoDrawable = ThemeUtils.getDrawable(toolBar.getContext(), attrResId);
        toolBar.setLogo(logoDrawable);
    }

    public void setNavigationIcon(Toolbar toolBar, @AttrRes int attrResId) {
        if (toolBar == null) {
            return;
        }

        toolBar.setTag(R.id.amt_tag_tool_bar_navigation_icon, attrResId);

        Drawable iconDrawable = ThemeUtils.getDrawable(toolBar.getContext(), attrResId);
        toolBar.setNavigationIcon(iconDrawable);
    }

    public void setOverflowIcon(Toolbar toolBar, @AttrRes int attrResId) {
        if (toolBar == null) {
            return;
        }

        toolBar.setTag(R.id.amt_tag_tool_bar_overflow_icon, attrResId);

        Drawable iconDrawable = ThemeUtils.getDrawable(toolBar.getContext(), attrResId);
        toolBar.setOverflowIcon(iconDrawable);
    }

    public void setTitleTextColor(Toolbar toolBar, @AttrRes int attrResId) {

        if (toolBar == null) {
            return;
        }

        toolBar.setTag(R.id.amt_tag_tool_bar_title_text_color, attrResId);

        toolBar.setTitleTextColor(ThemeUtils.getColor(toolBar.getContext(), attrResId));
    }

    public void setSubTitleTextColor(Toolbar toolBar, @AttrRes int attrResId) {

        if (toolBar == null) {
            return;
        }

        toolBar.setTag(R.id.amt_tag_tool_bar_subtitle_text_color, attrResId);

        toolBar.setSubtitleTextColor(ThemeUtils.getColor(toolBar.getContext(), attrResId));
    }
}
