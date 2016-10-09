package io.github.leonhover.theme.widget;

import android.content.res.Resources;
import android.view.View;
import android.widget.LinearLayout;

import io.github.leonhover.theme.R;
import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.model.ThemeElement;


/**
 * Created by leonhover on 16-9-27.
 */

public class LinearLayoutWidget extends ViewWidget {

    public LinearLayoutWidget() {
        super();
        ThemeElement element;
        element = new ThemeElement(R.id.tag_theme_widget_drawable_02, "divider");
        add(element);
    }

    @Override
    public void appleElementTheme(Resources.Theme theme, Resources resources, View view, ThemeElement element, int attrResId) {
        super.appleElementTheme(theme, resources, view, element, attrResId);
        switch (element.getTagKey()) {
            case R.id.tag_theme_widget_drawable_02:
                setDividerDrawable(theme, resources, view, attrResId);
                break;
        }
    }

    private void setDividerDrawable(Resources.Theme theme, Resources resources, View view, int attrResId) {
        ((LinearLayout) view).setDividerDrawable(ThemeUtils.getDrawable(theme, resources, attrResId));
    }
}
