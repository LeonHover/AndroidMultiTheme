package io.github.leonhover.theme.widget;

import android.support.annotation.AttrRes;
import android.view.View;
import android.widget.CompoundButton;

import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.annotation.MultiThemeAttrs;

/**
 * Created by leonhover on 16-9-27.
 */
@MultiThemeAttrs({
        android.R.attr.button
})
public class CompoundButtonWidget extends TextViewWidget {
    @Override
    public void appleElementTheme(View view, @AttrRes int themeElementKey, @AttrRes int themeElementValue) {
        super.appleElementTheme(view, themeElementKey, themeElementValue);
        CompoundButton compoundButton = (CompoundButton) view;
        switch (themeElementKey) {
            case android.R.attr.button:
                setButtonDrawable(compoundButton, themeElementValue);
                break;
        }
    }

    public static void setButtonDrawable(CompoundButton compoundButton, @AttrRes int attrResId) {

        if (compoundButton == null) {
            return;
        }

        saveThemeElementPair(compoundButton,android.R.attr.button,attrResId);

        compoundButton.setButtonDrawable(ThemeUtils.getDrawable(compoundButton.getContext(), attrResId));
    }
}
