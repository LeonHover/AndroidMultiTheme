package io.github.leonhover.theme.widget;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.view.View;
import android.widget.ProgressBar;

import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.annotation.MultiThemeAttrs;

/**
 * Created by leonhover on 16-9-27.
 */
@MultiThemeAttrs({
        android.R.attr.progressDrawable,
        android.R.attr.indeterminateDrawable,
})
public class ProgressBarWidget extends ViewWidget {

    @Override
    public void appleElementTheme(View view, @AttrRes int themeElementKey, @AttrRes int themeElementValue) {
        super.appleElementTheme(view, themeElementKey, themeElementValue);
        ProgressBar progressBar = (ProgressBar) view;
        switch (themeElementKey) {
            case android.R.attr.progressDrawable:
                setProgressDrawable(progressBar, themeElementValue);
                break;
            case android.R.attr.indeterminateDrawable:
                setIndeterminateDrawable(progressBar, themeElementValue);
                break;
        }
    }

    public static void setProgressDrawable(ProgressBar progressBar, @AttrRes int attrResId) {
        if (progressBar == null) {
            return;
        }

        saveThemeElementPair(progressBar, android.R.attr.progressDrawable, attrResId);

        Drawable drawable = ThemeUtils.getDrawable(progressBar.getContext(), attrResId);

        if (progressBar.getIndeterminateDrawable() != null) {
            Rect bounds = progressBar.getIndeterminateDrawable().copyBounds();
            drawable.setBounds(bounds);
            progressBar.setProgressDrawable(drawable);
        } else {
            progressBar.setProgressDrawable(drawable);
            int width = progressBar.getWidth();
            int height = progressBar.getHeight();

            ThemeUtils.invokeMethod(progressBar, "updateDrawableBounds", width, height);
        }

        ThemeUtils.invokeMethod(progressBar, "startAnimation");
    }

    public static void setIndeterminateDrawable(ProgressBar progressBar, @AttrRes int attrResId) {
        if (progressBar == null) {
            return;
        }

        saveThemeElementPair(progressBar, android.R.attr.indeterminateDrawable, attrResId);
        Drawable drawable = ThemeUtils.getDrawable(progressBar.getContext(), attrResId);

        if (progressBar.getIndeterminateDrawable() != null) {
            Rect bounds = progressBar.getIndeterminateDrawable().copyBounds();
            drawable.setBounds(bounds);
            progressBar.setIndeterminateDrawable(drawable);
        } else {
            progressBar.setIndeterminateDrawable(drawable);
            int width = progressBar.getWidth();
            int height = progressBar.getHeight();

            ThemeUtils.invokeMethod(progressBar, "updateDrawableBounds", width, height);
        }

        ThemeUtils.invokeMethod(progressBar, "startAnimation");

    }
}
