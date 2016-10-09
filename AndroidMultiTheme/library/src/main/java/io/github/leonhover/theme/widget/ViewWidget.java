package io.github.leonhover.theme.widget;

import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.view.View;

import io.github.leonhover.theme.R;
import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.model.ThemeElement;

/**
 * Created by leonhover on 16-9-27.
 */

public class ViewWidget extends AbstractThemeWidget {


    public ViewWidget() {
        ThemeElement element;
        element = new ThemeElement(R.id.tag_theme_widget_drawable_01, "background");
        add(element);
    }

    @Override
    public void appleElementTheme(Theme theme, Resources resources, View view, ThemeElement element, int attrResId) {
        switch (element.getTagKey()) {
            case R.id.tag_theme_widget_drawable_01:
                setBackground(theme, resources, view, attrResId);
                break;
        }
    }

    private void setBackground(Theme theme, Resources resources, View view, int attrResId) {
        view.setBackgroundDrawable(ThemeUtils.getDrawable(theme, resources, attrResId));
    }

}
