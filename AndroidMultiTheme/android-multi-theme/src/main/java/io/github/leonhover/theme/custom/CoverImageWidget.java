package io.github.leonhover.theme.custom;

import android.support.annotation.AttrRes;
import android.view.View;

import io.github.leonhover.theme.R;
import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.base.widget.CoverImageView;
import io.github.leonhover.theme.model.ThemeElement;
import io.github.leonhover.theme.widget.ImageViewWidget;

/**
 * Created by wangzongliang on 2016/11/8.
 */

public class CoverImageWidget extends ImageViewWidget {

    private static final String ATTR_NAME_COVER_COLOR = "coverColor";

    public CoverImageWidget() {
        super();
    }

    @Override
    protected void initializeElements() {
        super.initializeElements();
        add(new ThemeElement(R.id.amt_tag_cover_image_view_cover_color, ATTR_NAME_COVER_COLOR));
    }

    @Override
    public void appleElementTheme(View view, ThemeElement element, @AttrRes int attrResId) {
        super.appleElementTheme(view, element, attrResId);
        CoverImageView coverImageView = (CoverImageView) view;
        if (R.id.amt_tag_cover_image_view_cover_color == element.getTagKey()) {
            setCoverColor(coverImageView, attrResId);
        }
    }

    public void setCoverColor(CoverImageView coverImageView, @AttrRes int attrResId) {

        if (coverImageView == null) {
            return;
        }

        coverImageView.setTag(R.id.amt_tag_cover_image_view_cover_color, attrResId);

        coverImageView.setCoverColor(ThemeUtils.getColor(coverImageView.getContext(), attrResId));
    }
}
