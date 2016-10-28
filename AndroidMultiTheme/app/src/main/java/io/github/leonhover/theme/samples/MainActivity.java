package io.github.leonhover.theme.samples;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import io.github.leonhover.theme.ActivityTheme;
import io.github.leonhover.theme.MultiTheme;
import io.github.leonhover.theme.ThemeManager;
import io.github.leonhover.theme.base.BaseThemeActivity;

public class MainActivity extends BaseThemeActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActionBar();
    }

    private void initActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        this.setSupportActionBar(toolbar);
    }

    @Override
    protected void configTheme(ActivityTheme activityTheme) {
        activityTheme.setThemes(new int[]{R.style.AppTheme_Red, R.style.AppTheme_Blue});
        activityTheme.setStatusBarColorAttrRes(R.attr.colorPrimary);
        activityTheme.setSupportMenuItemThemeEnable(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu");
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.d(TAG, "onPrepareOptionsMenu");
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void supportInvalidateOptionsMenu() {
        super.supportInvalidateOptionsMenu();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_change_theme:
                int current = MultiTheme.getCurrentThemeIndex();
                MultiTheme.changeTheme(current == 0 ? 1 : 0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void clickMoreInfo(View view) {

    }
}
