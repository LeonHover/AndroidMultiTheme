package io.github.leonhover.theme.widget;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import io.github.leonhover.theme.R;
import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.model.ThemeElement;

/**
 * Created by leonhover on 16-9-27.
 */

public class ListViewWidget extends AbsListViewWidget {

    public ListViewWidget() {
        super();
        ThemeElement element;
        element = new ThemeElement(R.id.tag_theme_widget_drawable_03, "divider");
        add(element);
    }

    @Override
    public void applyTheme(Resources.Theme theme, Resources resources, View view) {
        super.applyTheme(theme, resources, view);
    }

    @Override
    public void appleElementTheme(Resources.Theme theme, Resources resources, View view, ThemeElement element, int attrResId) {
        super.appleElementTheme(theme, resources, view, element, attrResId);
        switch (element.getTagKey()) {
            case R.id.tag_theme_widget_drawable_03:
                setDividerDrawable(theme, resources, view, attrResId);
                break;
        }
    }

    private void setDividerDrawable(Resources.Theme theme, Resources resources, View view, int attrResId) {
        ListView listView = (ListView) view;
        int dividerHeight = listView.getDividerHeight();
        listView.setDivider(ThemeUtils.getDrawable(theme, resources, attrResId));
        listView.setDividerHeight(dividerHeight);
        Log.d(TAG, "setDividerDrawable dividerHeight:" + dividerHeight);

    }

    @Override
    public boolean isAdapterView() {
        return true;
    }
}
