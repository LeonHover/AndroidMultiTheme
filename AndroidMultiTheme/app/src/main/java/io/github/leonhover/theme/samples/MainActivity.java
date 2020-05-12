package io.github.leonhover.theme.samples;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.github.leonhover.theme.ActivityTheme;
import io.github.leonhover.theme.DarkMode;
import io.github.leonhover.theme.MultiTheme;
import io.github.leonhover.theme.ThemeUtils;
import io.github.leonhover.theme.base.BaseThemeActivity;

public class MainActivity extends BaseThemeActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private TextView appDescriptionTextView;
    private boolean attrChanged = false;

    private RecyclerView recyclerView;
    ImageView mBackground;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "isNightMode:" + ThemeUtils.isSystemDarkMode(this));
        initActionBar();

        appDescriptionTextView = (TextView) findViewById(R.id.app_description_text);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LinearLayout linearLayout = findViewById(R.id.contaier);

        mBackground = new ImageView(this);
        MultiTheme.applySingleElementTheme(mBackground, android.R.attr.background, R.attr.sub_title_text_color);
        MultiTheme.applySingleElementTheme(mBackground, android.R.attr.src, R.attr.icon_launcher);
        linearLayout.addView(mBackground);

        List<ThemeInfo> themeInfoList = new ArrayList<>();
        themeInfoList.add(new ThemeInfo(R.drawable.blue_ic_theme,
                R.string.theme_blue_title,
                R.string.theme_blue_description
        ));
        themeInfoList.add(new ThemeInfo(R.drawable.red_ic_theme,
                R.string.theme_red_title,
                R.string.theme_red_description
        ));
        themeInfoList.add(new ThemeInfo(R.drawable.green_ic_theme,
                R.string.theme_green_title,
                R.string.theme_green_description
        ));
        themeInfoList.add(new ThemeInfo(R.drawable.yellow_ic_theme,
                R.string.theme_yellow_title,
                R.string.theme_yellow_description
        ));
        themeInfoList.add(new ThemeInfo(R.drawable.black_ic_theme,
                R.string.theme_Black_title,
                R.string.theme_Black_description
        ));


        ThemeAdapter themeAdapter = new ThemeAdapter(this);
        themeAdapter.setThemeInfoList(themeInfoList);
        recyclerView.setAdapter(themeAdapter);

    }

    private void initActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        this.setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dark_theme_setings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        DarkMode mode = MultiTheme.getDarkMode();
        switch (mode) {
            case off:
                menu.findItem(R.id.amt_dark_theme_on).setChecked(false);
                menu.findItem(R.id.amt_dark_theme_off).setChecked(true);
                menu.findItem(R.id.amt_dark_theme_follow_system).setChecked(false);
                return true;
            case on:
                menu.findItem(R.id.amt_dark_theme_on).setChecked(true);
                menu.findItem(R.id.amt_dark_theme_off).setChecked(false);
                menu.findItem(R.id.amt_dark_theme_follow_system).setChecked(false);
                return true;
            case followSystem:
                menu.findItem(R.id.amt_dark_theme_on).setChecked(false);
                menu.findItem(R.id.amt_dark_theme_off).setChecked(false);
                menu.findItem(R.id.amt_dark_theme_follow_system).setChecked(true);
                return true;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.amt_dark_theme_off:
                MultiTheme.setDarkMode(DarkMode.off);
                item.setChecked(true);
                return true;
            case R.id.amt_dark_theme_on:
                MultiTheme.setDarkMode(DarkMode.on);
                item.setChecked(true);
                return true;
            case R.id.amt_dark_theme_follow_system:
                MultiTheme.setDarkMode(DarkMode.followSystem);
                item.setChecked(true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void configTheme(ActivityTheme activityTheme) {
        activityTheme.setThemes(4, new int[]{R.style.AppTheme_Blue, R.style.AppTheme_Red, R.style.AppTheme_Green, R.style.AppTheme_Yellow, R.style.AppTheme_Black});
        activityTheme.setStatusBarColorAttrRes(R.attr.colorPrimary);
        activityTheme.setSupportMenuItemThemeEnable(true);
    }

    public void clickButton(View view) {
        attrChanged = !attrChanged;
        int textColorAttrId = attrChanged ? R.attr.sub_title_text_color : R.attr.title_text_color;
        MultiTheme.applySingleElementTheme(appDescriptionTextView, android.R.attr.textColor, textColorAttrId);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged uiMode:" + newConfig.uiMode);
        switch (newConfig.uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                Log.d(TAG, "UI_MODE_NIGHT_YES");
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                Log.d(TAG, "UI_MODE_NIGHT_NO");
                break;
            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                Log.d(TAG, "UI_MODE_NIGHT_UNDEFINED");
                break;
        }
    }
}
