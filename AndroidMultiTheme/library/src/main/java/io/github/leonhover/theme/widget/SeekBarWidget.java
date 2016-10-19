package io.github.leonhover.theme.widget;

import android.content.res.Resources;
import android.view.View;
import android.widget.AbsSeekBar;

import io.github.leonhover.theme.R;
import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.model.ThemeElement;

/**
 * Created by leonhover on 16-9-27.
 */

public class SeekBarWidget extends ProgressBarWidget {

    private final static String ATTR_NAME_THUMB = "thumb";

    public SeekBarWidget(Class master) {
        super(master);
    }

    @Override
    protected void initializeElements() {
        super.initializeElements();
        ThemeElement element = new ThemeElement(R.id.amt_tag_seek_bar_thumb, ATTR_NAME_THUMB);
        add(element);
    }

    @Override
    public void appleElementTheme(View view, ThemeElement element, int attrResId) {
        super.appleElementTheme(view, element, attrResId);
        if (R.id.amt_tag_seek_bar_thumb == element.getTagKey()) {
            setThumb(view, attrResId);
        }
    }

    public void setThumb(View view, int attrResId) {
        ((AbsSeekBar) view).setThumb(ThemeUtils.getDrawable(view.getContext(), attrResId));
    }
}
