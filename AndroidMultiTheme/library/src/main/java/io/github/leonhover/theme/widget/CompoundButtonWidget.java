package io.github.leonhover.theme.widget;

import android.view.View;
import android.widget.CompoundButton;

import io.github.leonhover.theme.R;
import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.model.ThemeElement;

/**
 * Created by leonhover on 16-9-27.
 */

public class CompoundButtonWidget extends TextViewWidget {

    private static final String ATTR_NAME_BUTTON = "button";

    public CompoundButtonWidget(Class master) {
        super(master);
    }

    @Override
    protected void initializeElements() {
        super.initializeElements();
        ThemeElement element = new ThemeElement(R.id.amt_tag_compound_button_button, ATTR_NAME_BUTTON);
        add(element);
    }

    @Override
    public void appleElementTheme(View view, ThemeElement element, int attrResId) {
        super.appleElementTheme(view, element, attrResId);
        if (R.id.amt_tag_compound_button_button == element.getTagKey()) {
            setButtonDrawable(view, attrResId);
        }
    }

    public void setButtonDrawable(View view, int attrResId) {
        ((CompoundButton) view).setButtonDrawable(ThemeUtils.getDrawable(view.getContext(), attrResId));
    }
}
