package io.github.leonhover.theme.widget;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.view.View;
import android.widget.ProgressBar;

import io.github.leonhover.theme.R;
import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.model.ThemeElement;

/**
 * Created by leonhover on 16-9-27.
 */

public class ProgressBarWidget extends ViewWidget {

    private static final String ATTR_NAME_PROGRESS_DRAWABLE = "progressDrawable";
    private static final String ATTR_NAME_INDETERMINATE_DRAWABLE = "indeterminateDrawable";

    public ProgressBarWidget() {
        super();
    }

    @Override
    protected void initializeElements() {
        super.initializeElements();
        add(new ThemeElement(R.id.amt_tag_progress_bar_progress_drawable, ATTR_NAME_PROGRESS_DRAWABLE));
        add(new ThemeElement(R.id.amt_tag_progress_bar_indeterminate_drawable, ATTR_NAME_INDETERMINATE_DRAWABLE));
    }

    @Override
    public void appleElementTheme(View view, ThemeElement element, @AttrRes int attrResId) {
        super.appleElementTheme(view, element, attrResId);
        ProgressBar progressBar = (ProgressBar) view;
        if (R.id.amt_tag_progress_bar_progress_drawable == element.getTagKey()) {
            setProgressDrawable(progressBar, attrResId);
        } else if (R.id.amt_tag_progress_bar_indeterminate_drawable == element.getTagKey()) {
            setIndeterminateDrawable(progressBar, attrResId);
        }
    }

    public void setProgressDrawable(ProgressBar progressBar, @AttrRes int attrResId) {
        if (progressBar == null) {
            return;
        }

        progressBar.setTag(R.id.amt_tag_progress_bar_progress_drawable, attrResId);


        Drawable drawable = ThemeUtils.getDrawable(progressBar.getContext(), attrResId);

        if (progressBar.getIndeterminateDrawable() != null) {
            Rect bounds = progressBar.getIndeterminateDrawable().copyBounds();
            drawable.setBounds(bounds);
            progressBar.setProgressDrawable(drawable);
        } else {
            progressBar.setProgressDrawable(drawable);
            int width = progressBar.getWidth();
            int height = progressBar.getHeight();

            ThemeUtils.invodeMethod(progressBar, "updateDrawableBounds", width, height);
        }

        ThemeUtils.invodeMethod(progressBar, "startAnimation");
    }

    public void setIndeterminateDrawable(ProgressBar progressBar, @AttrRes int attrResId) {
        if (progressBar == null) {
            return;
        }

        progressBar.setTag(R.id.amt_tag_progress_bar_indeterminate_drawable, attrResId);
        Drawable drawable = ThemeUtils.getDrawable(progressBar.getContext(), attrResId);

        if (progressBar.getIndeterminateDrawable() != null) {
            Rect bounds = progressBar.getIndeterminateDrawable().copyBounds();
            drawable.setBounds(bounds);
            progressBar.setIndeterminateDrawable(drawable);
        } else {
            progressBar.setIndeterminateDrawable(drawable);
            int width = progressBar.getWidth();
            int height = progressBar.getHeight();

            ThemeUtils.invodeMethod(progressBar, "updateDrawableBounds", width, height);
        }

        ThemeUtils.invodeMethod(progressBar, "startAnimation");

    }
}
