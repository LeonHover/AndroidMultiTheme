package io.github.leonhover.theme.widget;

import android.support.annotation.AnyRes;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.CompoundButton;

import io.github.leonhover.theme.annotation.MultiThemeAttrs;

/**
 * Created by leonhover on 16-9-27.
 */
@MultiThemeAttrs({
        android.R.attr.button
})
public class CompoundButtonWidget extends TextViewWidget {

    @Override
    public void applyElementTheme(View view, @AttrRes int themeElementKey, @AnyRes int resId) {
        super.applyElementTheme(view, themeElementKey, resId);
        CompoundButton compoundButton = (CompoundButton) view;
        switch (themeElementKey) {
            case android.R.attr.button:
                setButtonDrawable(compoundButton, resId);
                break;
        }
    }

    public static void setButtonDrawable(CompoundButton compoundButton, @DrawableRes int drawableResId) {

        if (compoundButton == null) {
            return;
        }

        compoundButton.setButtonDrawable(getDrawable(compoundButton, drawableResId));
    }
}
