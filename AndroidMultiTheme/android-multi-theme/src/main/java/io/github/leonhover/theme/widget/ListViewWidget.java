package io.github.leonhover.theme.widget;

import androidx.annotation.AnyRes;
import androidx.annotation.AttrRes;
import androidx.annotation.DrawableRes;
import android.view.View;
import android.widget.ListView;

import io.github.leonhover.theme.annotation.MultiThemeAttrs;

/**
 * Created by leonhover on 16-9-27.
 */

@MultiThemeAttrs({
        android.R.attr.divider
})
public class ListViewWidget extends AbsListViewWidget {
    @Override
    public void applyElementTheme(View view, @AttrRes int themeElementKey, @AnyRes int resId) {
        super.applyElementTheme(view, themeElementKey, resId);
        ListView listView = (ListView) view;
        switch (themeElementKey) {
            case android.R.attr.divider:
                setDividerDrawable(listView, resId);
                break;
        }
    }

    public static void setDividerDrawable(ListView listView, @DrawableRes int drawableResId) {

        if (listView == null) {
            return;
        }

        int dividerHeight = listView.getDividerHeight();
        listView.setDivider(getDrawable(listView, drawableResId));
        listView.setDividerHeight(dividerHeight);
    }

}
