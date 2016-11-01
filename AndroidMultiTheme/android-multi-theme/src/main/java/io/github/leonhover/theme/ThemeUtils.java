package io.github.leonhover.theme;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

/**
 * Created by leonhover on 16-9-26.
 */

public class ThemeUtils {

    public static final String ANDROID_NAMESPACE = "http://schemas.android.com/apk/res/android";
    public static final String APP_NAMESPACE = "http://schemas.android.com/apk/res-auto";

    /**
     * 判断是否是以“?attr/**”引用的资源
     *
     * @param attrValue AttributeValue
     * @return true or false
     */
    public static boolean isAttrReference(String attrValue) {
        if (!TextUtils.isEmpty(attrValue) && attrValue.startsWith("?")) {
            return true;
        }

        return false;
    }

    /**
     * 获取Attr的资源ID
     *
     * @param attrValue AttributeValue
     * @return resid
     */
    public static int getAttrResId(String attrValue) {
        if (TextUtils.isEmpty(attrValue)) {
            return -1;
        }

        String resIdStr = attrValue.substring(1);
        return Integer.valueOf(resIdStr);
    }

    /**
     * 获取attrResId指向的颜色Color
     *
     * @param context
     * @param attrResId attr资源id
     * @return Color
     */
    public static int getColor(Context context, @AttrRes int attrResId) {
        if (context == null) {
            return -1;
        }
        return getColor(context.getTheme(), context.getResources(), attrResId);
    }

    /**
     * 获取attrResId指向的颜色Color
     *
     * @param theme     主题
     * @param resources 资源
     * @param attrResId attr资源id
     * @return Color
     */
    public static int getColor(Resources.Theme theme, Resources resources, @AttrRes int attrResId) {
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(attrResId, typedValue, true);
        int color = resources.getColor(typedValue.resourceId);
        return color;
    }

    /**
     * 获取attrResId指向的颜色ColorStateList
     *
     * @param context
     * @param attrResId attr资源id
     * @return ColorStateList
     */
    public static ColorStateList getColorStateList(Context context, @AttrRes int attrResId) {
        if (context == null) {
            return null;
        }
        return getColorStateList(context.getTheme(), context.getResources(), attrResId);
    }

    /**
     * 获取attrResId指向的颜色ColorStateList
     *
     * @param theme     主题
     * @param resources 资源
     * @param attrResId attr资源id
     * @return ColorStateList
     */
    public static ColorStateList getColorStateList(Resources.Theme theme, Resources resources, @AttrRes int attrResId) {
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(attrResId, typedValue, true);
        ColorStateList colorStateList = resources.getColorStateList(typedValue.resourceId);
        return colorStateList;
    }


    /**
     * 获取attrResId指向的Drawable
     *
     * @param context
     * @param attrResId attr资源id
     * @return Drawable
     */
    public static Drawable getDrawable(Context context, @AttrRes int attrResId) {
        if (context == null) {
            return null;
        }

        return getDrawable(context.getTheme(), context.getResources(), attrResId);
    }

    /**
     * 获取attrResId指向的Drawable
     *
     * @param theme     主题
     * @param resources 资源
     * @param attrResId attr资源id
     * @return Drawable
     */
    public static Drawable getDrawable(Resources.Theme theme, Resources resources, @AttrRes int attrResId) {
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(attrResId, typedValue, true);
        Drawable drawable = resources.getDrawable(typedValue.resourceId);
        return drawable;
    }

    public static <T> T getViewTag(View view, @IdRes int tagKey) throws ClassCastException, NullPointerException {
        try {
            return (T) view.getTag(tagKey);
        } catch (ClassCastException e) {
            throw e;
        } catch (NullPointerException e) {
            throw e;
        }
    }

    /**
     * 获取StatusBar高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

}
