package io.github.leonhover.theme.widget;

import android.support.annotation.AttrRes;
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

    public LinearLayoutWidget() {
        super();
    }

    @Override
    protected void initializeElements() {
        super.initializeElements();
        add(new ThemeElement(R.id.amt_tag_linear_layout_divider, ATTR_NAME_DIVIDER));
    }

    @Override
    public void appleElementTheme(View view, ThemeElement element, @AttrRes int attrResId) {
        super.appleElementTheme(view, element, attrResId);
        LinearLayout linearLayout = (LinearLayout) view;
        if (R.id.amt_tag_linear_layout_divider == element.getTagKey()) {
            setDividerDrawable(linearLayout, attrResId);
        }
    }

    public void setDividerDrawable(LinearLayout linearLayout, @AttrRes int attrResId) {
        if (linearLayout == null) {
            return;
        }

        linearLayout.setTag(R.id.amt_tag_linear_layout_divider, attrResId);

        linearLayout.setDividerDrawable(ThemeUtils.getDrawable(linearLayout.getContext(), attrResId));
    }
}
