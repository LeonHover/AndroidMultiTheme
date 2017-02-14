package io.github.leonhover.theme.widget;

import android.support.annotation.AttrRes;
import android.view.View;
import android.widget.AbsSeekBar;

import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.annotation.MultiThemeAttrs;

/**
 * Created by leonhover on 16-9-27.
 */
@MultiThemeAttrs({
        android.R.attr.thumb
})
public class SeekBarWidget extends ProgressBarWidget {

    @Override
    public void appleElementTheme(View view, @AttrRes int themeElementKey, @AttrRes int themeElementValue) {
        super.appleElementTheme(view, themeElementKey, themeElementValue);
        AbsSeekBar seekBar = (AbsSeekBar) view;
        switch (themeElementKey) {
            case android.R.attr.thumb:
                setThumb(seekBar, themeElementValue);
                break;
        }
    }

    public static void setThumb(AbsSeekBar seekBar, @AttrRes int attrResId) {
        if (seekBar == null) {
            return;
        }

        saveThemeElementPair(seekBar, android.R.attr.thumb, attrResId);
        seekBar.setThumb(ThemeUtils.getDrawable(seekBar.getContext(), attrResId));
    }
}
