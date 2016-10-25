package io.github.leonhover.theme.widget;

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
    public void appleElementTheme(View view, ThemeElement element, int attrResId) {
        super.appleElementTheme(view, element, attrResId);
        ProgressBar progressBar = (ProgressBar) view;
        if (R.id.amt_tag_progress_bar_progress_drawable == element.getTagKey()) {
            setProgressDrawable(progressBar, attrResId);
        } else if (R.id.amt_tag_progress_bar_indeterminate_drawable == element.getTagKey()) {
            setIndeterminateDrawable(progressBar, attrResId);
        }
    }

    private void setProgressDrawable(ProgressBar progressBar, int attrResId) {
        if (progressBar == null) {
            return;
        }

        progressBar.setTag(R.id.amt_tag_progress_bar_progress_drawable, attrResId);

        progressBar.setProgressDrawable(ThemeUtils.getDrawable(progressBar.getContext(), attrResId));
    }

    private void setIndeterminateDrawable(ProgressBar progressBar, int attrResId) {
        if (progressBar == null) {
            return;
        }

        progressBar.setTag(R.id.amt_tag_progress_bar_indeterminate_drawable, attrResId);
        progressBar.setIndeterminateDrawable(ThemeUtils.getDrawable(progressBar.getContext(), attrResId));
    }
}
