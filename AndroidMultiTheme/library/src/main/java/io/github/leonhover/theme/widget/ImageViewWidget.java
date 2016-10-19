package io.github.leonhover.theme.widget;

import android.view.View;
import android.widget.ImageView;

import io.github.leonhover.theme.R;
import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.model.ThemeElement;


/**
 * Created by leonhover on 16-9-27.
 */

public class ImageViewWidget extends ViewWidget {

    private static final String ATTR_NAME_SRC = "src";
    private static final String ATTR_NAME_SRC_COMPAT = "srcCompat";

    public ImageViewWidget(Class master) {
        super(master);
        ThemeElement element;
        element = new ThemeElement(R.id.amt_tag_image_view_src, ATTR_NAME_SRC);
        add(element);
        element = new ThemeElement(R.id.amt_tag_image_view_src_compat, ATTR_NAME_SRC_COMPAT);
        add(element);
    }

    @Override
    public void appleElementTheme(View view, ThemeElement element, int attrResId) {
        super.appleElementTheme(view, element, attrResId);

        if (R.id.amt_tag_image_view_src == element.getTagKey()) {
            setImageDrawable(view, attrResId);
        } else if (R.id.amt_tag_image_view_src_compat == element.getTagKey()) {
            setImageDrawable(view, attrResId);
        }
    }

    public void setImageDrawable(View view, int attrResId) {
        ((ImageView) view).setImageDrawable(ThemeUtils.getDrawable(view.getContext(), attrResId));
    }
}
