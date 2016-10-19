package io.github.leonhover.theme.widget;

import android.content.res.Resources;
import android.view.View;
import android.widget.AbsListView;

import io.github.leonhover.theme.R;
import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.model.ThemeElement;

/**
 * Created by leonhover on 16-9-27.
 */

public class AbsListViewWidget extends ViewWidget {

    private static final String ATTR_NAME_LIST_SELECTOR = "listSelector";

    public AbsListViewWidget(Class master) {
        super(master);
    }

    @Override
    protected void initializeElements() {
        super.initializeElements();
        ThemeElement element = new ThemeElement(R.id.amt_tag_abs_list_view_list_selector, ATTR_NAME_LIST_SELECTOR);
        add(element);
    }

    @Override
    public void appleElementTheme(View view, ThemeElement element, int attrResId) {
        super.appleElementTheme(view, element, attrResId);
        if (R.id.amt_tag_abs_list_view_list_selector == element.getTagKey()) {
            setSelector(view, attrResId);
        }
    }

    public void setSelector(View view, int attrResId) {
        ((AbsListView) view).setSelector(ThemeUtils.getDrawable(view.getContext(), attrResId));
    }

}
