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
        add(new ThemeElement(R.id.amt_tag_compound_button_button, ATTR_NAME_BUTTON));
    }

    @Override
    public void appleElementTheme(View view, ThemeElement element, int attrResId) {
        super.appleElementTheme(view, element, attrResId);
        CompoundButton compoundButton = (CompoundButton) view;
        if (R.id.amt_tag_compound_button_button == element.getTagKey()) {
            setButtonDrawable(compoundButton, attrResId);
        }
    }

    public void setButtonDrawable(CompoundButton compoundButton, int attrResId) {

        if (compoundButton == null) {
            return;
        }

        compoundButton.setTag(R.id.amt_tag_compound_button_button, attrResId);

        compoundButton.setButtonDrawable(ThemeUtils.getDrawable(compoundButton.getContext(), attrResId));
    }
}
