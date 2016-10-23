package io.github.leonhover.theme.widget;

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
        add(new ThemeElement(R.id.amt_tag_abs_list_view_list_selector, ATTR_NAME_LIST_SELECTOR));
    }

    @Override
    public void appleElementTheme(View view, ThemeElement element, int attrResId) {
        super.appleElementTheme(view, element, attrResId);
        AbsListView absListView = (AbsListView) view;
        if (R.id.amt_tag_abs_list_view_list_selector == element.getTagKey()) {
            setSelector(absListView, attrResId);
        }
    }

    public void setSelector(AbsListView absListView, int attrResId) {
        if (absListView == null) {
            return;
        }

        absListView.setTag(R.id.amt_tag_abs_list_view_list_selector, attrResId);

        absListView.setSelector(ThemeUtils.getDrawable(absListView.getContext(), attrResId));
    }

}
