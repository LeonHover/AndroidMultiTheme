package io.github.leonhover.theme.widget;

import android.support.annotation.AttrRes;
import android.view.View;
import android.widget.ListView;

import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.annotation.MultiThemeAttrs;

/**
 * Created by leonhover on 16-9-27.
 */

@MultiThemeAttrs({
        android.R.attr.divider
})
public class ListViewWidget extends AbsListViewWidget {

    @Override
    public void appleElementTheme(View view, @AttrRes int themeElementKey, @AttrRes int themeElementValue) {
        super.appleElementTheme(view, themeElementKey, themeElementValue);
        ListView listView = (ListView) view;
        switch (themeElementKey) {
            case android.R.attr.divider:
                setDividerDrawable(listView, themeElementValue);
                break;
        }
    }

    public static void setDividerDrawable(ListView listView, @AttrRes int attrResId) {

        if (listView == null) {
            return;
        }

        saveThemeElementPair(listView,android.R.attr.divider,attrResId);

        int dividerHeight = listView.getDividerHeight();
        listView.setDivider(ThemeUtils.getDrawable(listView.getContext(), attrResId));
        listView.setDividerHeight(dividerHeight);
    }

}
