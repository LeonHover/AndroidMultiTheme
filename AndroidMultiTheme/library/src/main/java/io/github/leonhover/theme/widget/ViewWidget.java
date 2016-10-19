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

    public ViewWidget(Class master) {
        super(master);
        initializeElements();
    }

    @Override
    protected void initializeElements() {
        ThemeElement element;
        element = new ThemeElement(R.id.amt_tag_view_background, ATTR_NAME_BACKGROUND);
        add(element);
    }

    public void setBackground(View view, @AttrRes int attrResId) {
        if (view == null) {
            return;
        }

        Drawable background = ThemeUtils.getDrawable(view.getContext(), attrResId);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            view.setBackground(background);
        } else {
            view.setBackgroundDrawable(background);
        }
    }

}
