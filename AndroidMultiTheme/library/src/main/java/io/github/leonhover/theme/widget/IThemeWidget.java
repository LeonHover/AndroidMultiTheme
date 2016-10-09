package io.github.leonhover.theme.widget;

import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by leonhover on 16-9-27.
 */

public interface IThemeWidget {

    /**
     * 组装主题的信息到View的TAG中。
     *
     * @param view         View
     * @param attributeSet View在Inflate时的Attribute.
     */
    void assemble(View view, AttributeSet attributeSet);

    /**
     * view应用主题Theme
     *
     * @param theme     主题
     * @param resources 资源
     * @param view      View
     */
    void applyTheme(Theme theme, Resources resources, View view);

    /**
     * 判断是否是AdapterView，AdapterView不支持对子View应用主题
     *
     * @return true or false;
     */
    boolean isAdapterView();
}
