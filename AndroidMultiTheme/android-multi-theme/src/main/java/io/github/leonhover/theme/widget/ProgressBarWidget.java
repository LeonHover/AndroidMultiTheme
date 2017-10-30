package io.github.leonhover.theme.widget;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.AnyRes;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
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
    public void applyElementTheme(View view, @AttrRes int themeElementKey, @AnyRes int resId) {
        super.applyElementTheme(view, themeElementKey, resId);
        ProgressBar progressBar = (ProgressBar) view;
        switch (themeElementKey) {
            case android.R.attr.progressDrawable:
                setProgressDrawable(progressBar, resId);
                break;
            case android.R.attr.indeterminateDrawable:
                setIndeterminateDrawable(progressBar, resId);
                break;
        }
    }

    public static void setProgressDrawable(ProgressBar progressBar, @DrawableRes int drawableResId) {
        if (progressBar == null) {
            return;
        }

        Drawable drawable = getDrawable(progressBar, drawableResId);

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

    public static void setIndeterminateDrawable(ProgressBar progressBar, @DrawableRes int drawableResId) {
        if (progressBar == null) {
            return;
        }

        Drawable drawable = getDrawable(progressBar, drawableResId);

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
