package io.github.leonhover.theme.samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.github.leonhover.theme.ActivityTheme;
import io.github.leonhover.theme.MultiTheme;
import io.github.leonhover.theme.base.BaseThemeActivity;
import io.github.leonhover.theme.widget.TextViewWidget;

public class MainActivity extends BaseThemeActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private TextView appDescriptionTextView;
    private boolean attrChanged = false;

    private RecyclerView recyclerView;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActionBar();

        appDescriptionTextView = (TextView) findViewById(R.id.app_description_text);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<ThemeInfo> themeInfoList = new ArrayList<>();
        themeInfoList.add(new ThemeInfo(R.drawable.black_ic_theme,
                R.string.theme_Black_title,
                R.string.theme_Black_description
        ));
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


        ThemeAdapter themeAdapter = new ThemeAdapter(this);
        themeAdapter.setThemeInfoList(themeInfoList);
        recyclerView.setAdapter(themeAdapter);

    }

    private void initActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        this.setSupportActionBar(toolbar);
    }

    @Override
    protected void configTheme(ActivityTheme activityTheme) {
        activityTheme.setThemes(new int[]{R.style.AppTheme_Black, R.style.AppTheme_Blue, R.style.AppTheme_Red, R.style.AppTheme_Green, R.style.AppTheme_Yellow});
        activityTheme.setStatusBarColorAttrRes(R.attr.colorPrimary);
        activityTheme.setSupportMenuItemThemeEnable(true);
    }

    public void clickButton(View view) {
        attrChanged = !attrChanged;
        int textColorAttrId = attrChanged ? R.attr.sub_title_text_color : R.attr.title_text_color;
        MultiTheme.appleSingleElementTheme(appDescriptionTextView, android.R.attr.textColor, textColorAttrId);
    }
}
