package io.github.leonhover.theme.widget;

import android.support.annotation.AttrRes;
import android.view.View;
import android.widget.ImageView;

import io.github.leonhover.theme.R;
import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.annotation.MultiThemeAttrs;

/**
 * Created by leonhover on 16-9-27.
 */
@MultiThemeAttrs({
        android.R.attr.src,
})
public class ImageViewWidget extends ViewWidget {

    @Override
    protected void initializeLibraryElements() {
        super.initializeLibraryElements();
        addThemeElementKey(R.attr.srcCompat);
    }

    @Override
    public void appleElementTheme(View view, @AttrRes int themeElementKey, @AttrRes int themeElementValue) {
        super.appleElementTheme(view, themeElementKey, themeElementValue);
        ImageView imageView = (ImageView) view;
        if (themeElementKey == android.R.attr.src) {
            setImageDrawable(imageView, themeElementValue);
        } else if (themeElementKey == R.attr.srcCompat) {
            setImageCompatDrawable(imageView, themeElementValue);
        }
    }

    public static void setImageDrawable(ImageView imageView, @AttrRes int attrResId) {
        if (imageView == null) {
            return;
        }

        saveThemeElementPair(imageView, android.R.attr.src, attrResId);

        imageView.setImageDrawable(ThemeUtils.getDrawable(imageView.getContext(), attrResId));
    }

    public static void setImageCompatDrawable(ImageView imageView, @AttrRes int attrResId) {
        if (imageView == null) {
            return;
        }

        saveThemeElementPair(imageView, R.attr.srcCompat, attrResId);

        imageView.setImageDrawable(ThemeUtils.getDrawable(imageView.getContext(), attrResId));
    }

}
