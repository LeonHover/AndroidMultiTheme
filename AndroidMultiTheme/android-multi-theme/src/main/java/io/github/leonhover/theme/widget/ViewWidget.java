package io.github.leonhover.theme.widget;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.view.View;

import io.github.leonhover.theme.R;
import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.model.ThemeElement;

/**
 * Created by leonhover on 16-9-27.
 */

public class ViewWidget extends AbstractThemeWidget {

    private static final String ATTR_NAME_BACKGROUND = "background";

    public ViewWidget() {
        super();
    }

    @Override
    protected void initializeElements() {
        add(new ThemeElement(R.id.amt_tag_view_background, ATTR_NAME_BACKGROUND));
    }

    @Override
    public void appleElementTheme(View view, ThemeElement element, int attrResId) {
        super.appleElementTheme(view, element, attrResId);
        if (R.id.amt_tag_view_background == element.getTagKey()) {
            setBackground(view, attrResId);
        }
    }

    public void setBackground(View view, @AttrRes int attrResId) {
        if (view == null) {
            return;
        }

        Drawable background = ThemeUtils.getDrawable(view.getContext(), attrResId);

        view.setTag(R.id.amt_tag_view_background, attrResId);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            view.setBackground(background);
        } else {
            view.setBackgroundDrawable(background);
        }
    }

}
