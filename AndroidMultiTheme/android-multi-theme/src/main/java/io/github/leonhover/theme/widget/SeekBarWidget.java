package io.github.leonhover.theme.widget;

import android.support.annotation.AnyRes;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.AbsSeekBar;

import io.github.leonhover.theme.annotation.MultiThemeAttrs;

/**
 * Created by leonhover on 16-9-27.
 */
@MultiThemeAttrs({
        android.R.attr.thumb
})
public class SeekBarWidget extends ProgressBarWidget {
    @Override
    public void applyElementTheme(View view, @AttrRes int themeElementKey, @AnyRes int resId) {
        super.applyElementTheme(view, themeElementKey, resId);
        AbsSeekBar seekBar = (AbsSeekBar) view;
        switch (themeElementKey) {
            case android.R.attr.thumb:
                setThumb(seekBar, resId);
                break;
        }
    }

    public static void setThumb(AbsSeekBar seekBar, @DrawableRes int drawableResId) {
        if (seekBar == null) {
            return;
        }

        seekBar.setThumb(getDrawable(seekBar, drawableResId));
    }
}
