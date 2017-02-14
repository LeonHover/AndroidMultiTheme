package io.github.leonhover.theme.widget;

import android.support.annotation.AttrRes;
import android.view.View;
import android.widget.AbsListView;

import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.annotation.MultiThemeAttrs;

/**
 * Created by leonhover on 16-9-27.
 */
@MultiThemeAttrs({
        android.R.attr.listSelector
})
public class AbsListViewWidget extends ViewWidget {

    @Override
    public void appleElementTheme(View view, @AttrRes int themeElementKey, @AttrRes int themeElementValue) {
        super.appleElementTheme(view, themeElementKey, themeElementValue);
        AbsListView absListView = (AbsListView) view;
        switch (themeElementKey) {
            case android.R.attr.listSelector:
                setSelector(absListView, themeElementValue);
                break;
        }
    }

    public static void setSelector(AbsListView absListView, int attrResId) {
        if (absListView == null) {
            return;
        }

        saveThemeElementPair(absListView,android.R.attr.listSelector,attrResId);

        absListView.setSelector(ThemeUtils.getDrawable(absListView.getContext(), attrResId));
    }

}
