package io.github.leonhover.theme.widget;

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

    public SeekBarWidget() {
        super();
    }

    @Override
    protected void initializeElements() {
        super.initializeElements();
        add(new ThemeElement(R.id.amt_tag_seek_bar_thumb, ATTR_NAME_THUMB));
    }

    @Override
    public void appleElementTheme(View view, ThemeElement element, int attrResId) {
        super.appleElementTheme(view, element, attrResId);
        AbsSeekBar seekBar = (AbsSeekBar) view;
        if (R.id.amt_tag_seek_bar_thumb == element.getTagKey()) {
            setThumb(seekBar, attrResId);
        }
    }

    public void setThumb(AbsSeekBar seekBar, int attrResId) {
        if (seekBar == null) {
            return;
        }

        seekBar.setTag(R.id.amt_tag_seek_bar_thumb, attrResId);

        seekBar.setThumb(ThemeUtils.getDrawable(seekBar.getContext(), attrResId));
    }
}
