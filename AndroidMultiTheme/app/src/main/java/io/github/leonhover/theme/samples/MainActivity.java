package io.github.leonhover.theme.samples;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import io.github.leonhover.theme.ThemeManager;
import io.github.leonhover.theme.base.BaseThemeActivity;

public class MainActivity extends BaseThemeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void configTheme(ActivityTheme activityTheme) {
        activityTheme.setThemes(new int[]{R.style.AppTheme_Red, R.style.AppTheme_Blue});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_change_theme:
                int current = ThemeManager.getInstance().getCurrentThemeIndex();
                ThemeManager.getInstance().changeTheme(current == 0 ? 1 : 0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
