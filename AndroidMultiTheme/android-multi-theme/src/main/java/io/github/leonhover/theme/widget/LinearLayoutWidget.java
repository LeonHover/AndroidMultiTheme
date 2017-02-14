package io.github.leonhover.theme.widget;

import android.support.annotation.AttrRes;
import android.view.View;
import android.widget.LinearLayout;

import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.annotation.MultiThemeAttrs;


/**
 * Created by leonhover on 16-9-27.
 */
@MultiThemeAttrs({
        android.R.attr.divider
})
public class LinearLayoutWidget extends ViewWidget {

    @Override
    public void appleElementTheme(View view, @AttrRes int themeElementKey, @AttrRes int themeElementValue) {
        super.appleElementTheme(view, themeElementKey, themeElementValue);
        LinearLayout linearLayout = (LinearLayout) view;
        switch (themeElementKey) {
            case android.R.attr.divider:
                setDividerDrawable(linearLayout, themeElementValue);
                break;
        }
    }

    public static void setDividerDrawable(LinearLayout linearLayout, @AttrRes int attrResId) {
        if (linearLayout == null) {
            return;
        }

        saveThemeElementPair(linearLayout,android.R.attr.divider,attrResId);

        linearLayout.setDividerDrawable(ThemeUtils.getDrawable(linearLayout.getContext(), attrResId));
    }
}
