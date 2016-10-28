package io.github.leonhover.theme;

import android.view.View;

import java.util.Collection;
import java.util.HashSet;


/**
 * Created by leonhover on 16-10-9.
 */

public class ThemeViewEntities extends HashSet<View> implements IThemeObserver {

    private boolean underObservation = false;

    @Override
    public boolean add(View object) {
        boolean ret = super.add(object);
        checkObserver();
        return ret;
    }

    @Override
    public boolean addAll(Collection<? extends View> collection) {
        boolean ret = super.addAll(collection);
        checkObserver();
        return ret;
    }

    @Override
    public boolean remove(Object object) {
        boolean ret = super.remove(object);
        checkObserver();
        return ret;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean ret = super.removeAll(collection);
        checkObserver();
        return ret;
    }

    private void checkObserver() {
        if (this.size() > 0) {
            if (!underObservation) {
                MultiTheme.addObserver(this);
                underObservation = true;
            }
        } else {
            if (underObservation) {
                MultiTheme.removeObserver(this);
                underObservation = false;
            }
        }
    }

    @Override
    public void clear() {
        super.clear();
        checkObserver();
    }

    @Override
    public int getPriority() {
        return PRIORITY_VIEW;
    }

    @Override
    public void onThemeChanged(int whichTheme) {
        for (View view : this) {
            MultiTheme.applyTheme(view);
        }
    }

    @Override
    public int compareTo(IThemeObserver o) {
        return getPriority() > o.getPriority() ? 1 : -1;
    }
}
