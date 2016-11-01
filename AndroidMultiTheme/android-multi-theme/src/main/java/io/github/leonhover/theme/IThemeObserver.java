package io.github.leonhover.theme;

/**
 * Created by leonhover on 16-9-27.
 */

public interface IThemeObserver extends Comparable<IThemeObserver> {

    int PRIORITY_ACTIVITY = 1;
    int PRIORITY_VIEW = 2;

    int getPriority();

    void onThemeChanged(int whichTheme);
}
