package io.github.leonhover.theme.widget;

import android.content.res.Resources;
import android.view.View;
import android.widget.ProgressBar;

import io.github.leonhover.theme.R;
import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.model.ThemeElement;

/**
 * Created by leonhover on 16-9-27.
 */

public class ProgressBarWidget extends ViewWidget {

    public ProgressBarWidget() {
        super();
        ThemeElement element = null;
        element = new ThemeElement(R.id.tag_theme_widget_drawable_02, "progressDrawable");
        add(element);
        element = new ThemeElement(R.id.tag_theme_widget_drawable_03, "indeterminateDrawable");
        add(element);
    }

    @Override
    public void appleElementTheme(Resources.Theme theme, Resources resources, View view, ThemeElement element, int attrResId) {
        super.appleElementTheme(theme, resources, view, element, attrResId);
        switch (element.getTagKey()) {
            case R.id.tag_theme_widget_drawable_02:
                setProgressDrawable(theme, resources, view, attrResId);
                break;
            case R.id.tag_theme_widget_drawable_03:
                setIndeterminateDrawable(theme, resources, view, attrResId);
                break;
        }
    }

    private void setProgressDrawable(Resources.Theme theme, Resources resources, View view, int attrResId) {
        ((ProgressBar) view).setProgressDrawable(ThemeUtils.getDrawable(theme, resources, attrResId));
    }

    private void setIndeterminateDrawable(Resources.Theme theme, Resources resources, View view, int attrResId) {
        ((ProgressBar) view).setIndeterminateDrawable(ThemeUtils.getDrawable(theme, resources, attrResId));
    }
}
