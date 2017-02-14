package io.github.leonhover.theme.widget;

import android.graphics.drawable.Drawable;
import android.os.Build;
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
    public void appleElementTheme(View view, @AttrRes int themeElementKey, @AttrRes int themeElementValue) {
        super.appleElementTheme(view, themeElementKey, themeElementValue);
        switch (themeElementKey) {
            case android.R.attr.background:
                setBackground(view, themeElementValue);
                break;
        }
    }

    @SuppressWarnings("NewApi")
    public static void setBackground(View view, @AttrRes int attrResId) {
        if (view == null) {
            return;
        }

        Drawable background = ThemeUtils.getDrawable(view.getContext(), attrResId);

        saveThemeElementPair(view, android.R.attr.background, attrResId);

        if (ThemeUtils.IS_JELLY_BEAN) {
            view.setBackground(background);
        } else {
            view.setBackgroundDrawable(background);
        }
    }

}
