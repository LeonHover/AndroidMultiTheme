package io.github.leonhover.theme.custom;

import android.support.annotation.AttrRes;
import android.view.View;

import io.github.leonhover.theme.R;
import io.github.leonhover.theme.ThemeUtils;
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
    public void appleElementTheme(View view, @AttrRes int themeElementKey, @AttrRes int themeElementValue) {
        super.appleElementTheme(view, themeElementKey, themeElementValue);
        CoverImageView coverImageView = (CoverImageView) view;
        if (R.attr.coverColor == themeElementKey) {
            setCoverColor(coverImageView, themeElementValue);
        }
    }

    public static void setCoverColor(CoverImageView coverImageView, @AttrRes int attrResId) {

        if (coverImageView == null) {
            return;
        }

        saveThemeElementPair(coverImageView,R.attr.coverColor,attrResId);

        coverImageView.setCoverColor(ThemeUtils.getColor(coverImageView.getContext(), attrResId));
    }
}
