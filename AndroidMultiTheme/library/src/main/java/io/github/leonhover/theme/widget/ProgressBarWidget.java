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

    public ProgressBarWidget(Class master) {
        super(master);
    }

    @Override
    protected void initializeElements() {
        super.initializeElements();
        ThemeElement element = null;
        element = new ThemeElement(R.id.amt_tag_progress_bar_progress_drawable, ATTR_NAME_PROGRESS_DRAWABLE);
        add(element);
        element = new ThemeElement(R.id.amt_tag_progress_bar_indeterminate_drawable, ATTR_NAME_INDETERMINATE_DRAWABLE);
        add(element);
    }

    @Override
    public void appleElementTheme(View view, ThemeElement element, int attrResId) {
        super.appleElementTheme(view, element, attrResId);

        if (R.id.amt_tag_progress_bar_progress_drawable == element.getTagKey()) {
            setProgressDrawable(view, attrResId);
        } else if (R.id.amt_tag_progress_bar_indeterminate_drawable == element.getTagKey()) {
            setIndeterminateDrawable(view, attrResId);
        }
    }

    private void setProgressDrawable(View view, int attrResId) {
        ((ProgressBar) view).setProgressDrawable(ThemeUtils.getDrawable(view.getContext(), attrResId));
    }

    private void setIndeterminateDrawable(View view, int attrResId) {
        ((ProgressBar) view).setIndeterminateDrawable(ThemeUtils.getDrawable(view.getContext(), attrResId));
    }
}
