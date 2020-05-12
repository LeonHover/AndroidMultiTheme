package io.github.leonhover.theme.base;

import androidx.fragment.app.Fragment;

import io.github.leonhover.theme.ThemeViewEntities;

/**
 * Created by leonhover on 16-10-9.
 */

public class BaseThemeFragment extends Fragment {

    private ThemeViewEntities themeViewEntities = new ThemeViewEntities();

    public ThemeViewEntities getThemeViewEntities() {
        return themeViewEntities;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        themeViewEntities.clear();
    }
}
