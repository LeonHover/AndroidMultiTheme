package io.github.leonhover.theme.widget;

import android.view.View;
import android.widget.LinearLayout;

import io.github.leonhover.theme.R;
import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.model.ThemeElement;


/**
 * Created by leonhover on 16-9-27.
 */

public class LinearLayoutWidget extends ViewWidget {


    private static final String ATTR_NAME_DIVIDER = "divider";

    public LinearLayoutWidget(Class master) {
        super(master);
    }

    @Override
    protected void initializeElements() {
        super.initializeElements();
        ThemeElement element;
        element = new ThemeElement(R.id.amt_tag_linear_layout_divider, ATTR_NAME_DIVIDER);
        add(element);
    }

    @Override
    public void appleElementTheme(View view, ThemeElement element, int attrResId) {
        super.appleElementTheme(view, element, attrResId);
        if (R.id.amt_tag_linear_layout_divider == element.getTagKey()) {
            setDividerDrawable(view, attrResId);
        }
    }

    public void setDividerDrawable(View view, int attrResId) {
        ((LinearLayout) view).setDividerDrawable(ThemeUtils.getDrawable(view.getContext(), attrResId));
    }
}
