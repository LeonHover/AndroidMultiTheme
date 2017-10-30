package io.github.leonhover.theme.widget;

import android.graphics.drawable.Drawable;
import android.support.annotation.AnyRes;
import android.support.annotation.AttrRes;
import android.view.View;

import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.annotation.MultiThemeAttrs;

/**
 * Created by leonhover on 16-9-27.
 */
@MultiThemeAttrs({
        android.R.attr.background
})
public class ViewWidget extends AbstractThemeWidget {

    @Override
    protected void initializeLibraryElements() {
        super.initializeLibraryElements();
    }

    @Override
    public void applyElementTheme(View view, @AttrRes int themeElementKey, @AnyRes int resId) {
        super.applyElementTheme(view, themeElementKey, resId);
        switch (themeElementKey) {
            case android.R.attr.background:
                setBackground(view, resId);
                break;
        }
    }

    @SuppressWarnings("NewApi")
    public static void setBackground(View view, @AnyRes int resId) {
        if (view == null) {
            return;
        }

        Drawable background = getDrawable(view, resId);

        if (ThemeUtils.IS_JELLY_BEAN) {
            view.setBackground(background);
        } else {
            view.setBackgroundDrawable(background);
        }
    }

}
