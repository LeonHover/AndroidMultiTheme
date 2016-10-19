package io.github.leonhover.theme.widget;

import android.view.View;
import android.widget.ListView;

import io.github.leonhover.theme.R;
import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.model.ThemeElement;

/**
 * Created by leonhover on 16-9-27.
 */

public class ListViewWidget extends AbsListViewWidget {

    private static final String ATTR_NAME_DIVIDER = "divider";

    public ListViewWidget(Class master) {
        super(master);
        ThemeElement element;
        element = new ThemeElement(R.id.amt_tag_list_view_divider, "divider");
        add(element);
    }

    @Override
    public void appleElementTheme(View view, ThemeElement element, int attrResId) {
        super.appleElementTheme(view, element, attrResId);
        if (R.id.amt_tag_list_view_divider == element.getTagKey()) {
            setDividerDrawable(view, attrResId);
        }
    }

    private void setDividerDrawable(View view, int attrResId) {
        ListView listView = (ListView) view;
        int dividerHeight = listView.getDividerHeight();
        listView.setDivider(ThemeUtils.getDrawable(view.getContext(), attrResId));
        listView.setDividerHeight(dividerHeight);
    }

}
