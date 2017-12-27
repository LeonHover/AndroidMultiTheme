package io.github.leonhover.theme.annotation;

import android.support.annotation.AttrRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * MultiThemeAttrs是一个Attribute的数组，用来注解MultiTheme支持的主题控件类。
 * <p>
 * 这些Attribute是具体View的对应Styleable中的值
 * 这些值不但是作为MultiTheme中用来索引的Key，也是通过其从XML解析出的Attribute中取出主题元素的值的关键。
 * <p>
 * 例:
 * android:background="?attr/theme_background"
 * <p>
 * View中都有'android.R.attr.background'的标签，添加其作为View的MultiThemeAttrs，那么MultiTheme
 * 就会将'background'标签下的'theme_background'对应attrRes取出，作为主题元素的值来存储。当主题改变
 * 的时候，通过'android.R.attr.background'找到attrRes，然后分发到对应的
 * {@link io.github.leonhover.theme.widget.ViewWidget}的'applyElementTheme'。
 * <p>
 * Created by wangzongliang on 2017/2/14.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface MultiThemeAttrs {
    @AttrRes int[] value();
}
