package io.github.leonhover.theme.widget;

import android.support.annotation.AnyRes;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.AbsListView;

import io.github.leonhover.theme.annotation.MultiThemeAttrs;

/**
 * Created by leonhover on 16-9-27.
 */
@MultiThemeAttrs({
        android.R.attr.listSelector
})
public class AbsListViewWidget extends ViewWidget {

    @Override
    public void applyElementTheme(View view, @AttrRes int themeElementKey, @AnyRes int resId) {
        super.applyElementTheme(view, themeElementKey, resId);
        AbsListView absListView = (AbsListView) view;
        switch (themeElementKey) {
            case android.R.attr.listSelector:
                setSelector(absListView, resId);
                break;
        }
    }

    public static void setSelector(AbsListView absListView, @DrawableRes int drawableResId) {
        if (absListView == null) {
            return;
        }

        absListView.setSelector(getDrawable(absListView, drawableResId));
    }

}
