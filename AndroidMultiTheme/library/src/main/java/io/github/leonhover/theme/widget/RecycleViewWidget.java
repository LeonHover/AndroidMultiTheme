package io.github.leonhover.theme.widget;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by leonhover on 16-9-27.
 */

public class RecycleViewWidget extends ViewWidget {

    @Override
    public void applyTheme(Resources.Theme theme, Resources resources, View view) {
        super.applyTheme(theme, resources, view);
        if (view instanceof RecyclerView) {
            RecyclerView.Adapter adapter = ((RecyclerView) view).getAdapter();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean isAdapterView() {
        return true;
    }
}
