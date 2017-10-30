package io.github.leonhover.theme.widget;

import android.support.annotation.AnyRes;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.LinearLayout;

import io.github.leonhover.theme.annotation.MultiThemeAttrs;


/**
 * Created by leonhover on 16-9-27.
 */
@MultiThemeAttrs({
        android.R.attr.divider
})
public class LinearLayoutWidget extends ViewWidget {
    @Override
    public void applyElementTheme(View view, @AttrRes int themeElementKey, @AnyRes int resId) {
        super.applyElementTheme(view, themeElementKey, resId);
        LinearLayout linearLayout = (LinearLayout) view;
        switch (themeElementKey) {
            case android.R.attr.divider:
                setDividerDrawable(linearLayout, resId);
                break;
        }
    }

    public static void setDividerDrawable(LinearLayout linearLayout, @DrawableRes int drawableResId) {
        if (linearLayout == null) {
            return;
        }

        linearLayout.setDividerDrawable(getDrawable(linearLayout,drawableResId));
    }
}
