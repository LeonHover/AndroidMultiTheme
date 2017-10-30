package io.github.leonhover.theme.custom;

import android.support.annotation.AnyRes;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorRes;
import android.view.View;

import io.github.leonhover.theme.R;
import io.github.leonhover.theme.base.widget.CoverImageView;
import io.github.leonhover.theme.widget.ImageViewWidget;

/**
 * Created by wangzongliang on 2016/11/8.
 */

public class CoverImageWidget extends ImageViewWidget {

    @Override
    protected void initializeLibraryElements() {
        super.initializeLibraryElements();
        addThemeElementKey(R.attr.coverColor);
    }

    @Override
    public void applyElementTheme(View view, @AttrRes int themeElementKey, @AnyRes int resId) {
        super.applyElementTheme(view, themeElementKey, resId);
        CoverImageView coverImageView = (CoverImageView) view;
        if (R.attr.coverColor == themeElementKey) {
            setCoverColor(coverImageView, resId);
        }
    }

    public static void setCoverColor(CoverImageView coverImageView, @ColorRes int colorResId) {

        if (coverImageView == null) {
            return;
        }

        coverImageView.setCoverColor(getColor(coverImageView, colorResId));
    }
}
