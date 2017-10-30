package io.github.leonhover.theme;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by leonhover on 16-9-26.
 */

public class ThemeUtils {

    public static final boolean IS_JELLY_BEAN = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    public static final boolean IS_KITKAT = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    public static final boolean IS_LOLLIPOP = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    public static final boolean IS_M = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;

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
    @SuppressWarnings("NewApi")
    public static int getColor(Resources.Theme theme, Resources resources, @AttrRes int attrResId) {
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(attrResId, typedValue, true);
        MultiTheme.d("a", "Color type:" + typedValue.toString());
        int color;
        if (IS_M) {
            color = resources.getColor(typedValue.resourceId, theme);
        } else {
            color = resources.getColor(typedValue.resourceId);
        }
        return color;
    }


    /**
     * 获取colorResId指向的颜色Color
     *
     * @param context
     * @param colorResId 颜色资源id
     * @return Color
     */
    public static int getColorWithResId(Context context, @ColorRes int colorResId) {
        if (context == null) {
            return -1;
        }
        return getColorWithResId(context.getTheme(), context.getResources(), colorResId);
    }

    /**
     * 获取colorResId指向的颜色Color
     *
     * @param theme      主题
     * @param resources  资源
     * @param colorResId 颜色资源id
     * @return Color
     */
    @SuppressWarnings("NewApi")
    public static int getColorWithResId(Resources.Theme theme, Resources resources, @ColorRes int colorResId) {
        int color;
        if (IS_M) {
            color = resources.getColor(colorResId, theme);
        } else {
            color = resources.getColor(colorResId);
        }
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
    @SuppressWarnings("NewApi")
    public static ColorStateList getColorStateList(Resources.Theme theme, Resources resources, @AttrRes int attrResId) {
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(attrResId, typedValue, true);

        MultiTheme.d("a", "ColorStateList type:" + typedValue.toString());
        ColorStateList colorStateList;

        if (IS_M) {
            colorStateList = resources.getColorStateList(typedValue.resourceId, theme);
        } else {
            colorStateList = resources.getColorStateList(typedValue.resourceId);
        }
        return colorStateList;
    }

    /**
     * 获取ColorStateListResId指向的颜色ColorStateList
     *
     * @param context
     * @param colorStateListResId 资源id
     * @return ColorStateList
     */
    public static ColorStateList getColorStateListWithResId(Context context, @ColorRes int colorStateListResId) {
        if (context == null) {
            return null;
        }
        return getColorStateListWithResId(context.getTheme(), context.getResources(), colorStateListResId);
    }


    /**
     * 获取ColorStateListResId指向的颜色ColorStateList
     *
     * @param theme               主题
     * @param resources           资源
     * @param colorStateListResId 资源id
     * @return ColorStateList
     */
    @SuppressWarnings("NewApi")
    public static ColorStateList getColorStateListWithResId(Resources.Theme theme, Resources resources, @ColorRes int colorStateListResId) {

        ColorStateList colorStateList;

        if (IS_M) {
            colorStateList = resources.getColorStateList(colorStateListResId, theme);
        } else {
            colorStateList = resources.getColorStateList(colorStateListResId);
        }
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
    @SuppressWarnings("NewApi")
    public static Drawable getDrawable(Resources.Theme theme, Resources resources, @AttrRes int attrResId) {
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(attrResId, typedValue, true);
        Drawable drawable;
        MultiTheme.d("a", "drawable type:" + typedValue.toString());
        if (IS_M) {
            drawable = resources.getDrawable(typedValue.resourceId, theme);
        } else {
            drawable = resources.getDrawable(typedValue.resourceId);
        }
        return drawable;
    }


    /**
     * 获取DrawableResId指向的Drawable
     *
     * @param context
     * @param drawableResId Drawable资源id
     * @return Drawable
     */
    public static Drawable getDrawableWithResId(Context context, @DrawableRes int drawableResId) {
        if (context == null) {
            return null;
        }

        return getDrawableWithResId(context.getTheme(), context.getResources(), drawableResId);
    }

    /**
     * 获取DrawableResId指向的Drawable
     *
     * @param theme         主题
     * @param resources     资源
     * @param drawableResId Drawable资源id
     * @return Drawable
     */
    @SuppressLint("NewApi")
    public static Drawable getDrawableWithResId(Resources.Theme theme, Resources resources, @DrawableRes int drawableResId) {
        Drawable drawable;
        if (IS_M) {
            drawable = resources.getDrawable(drawableResId, theme);
        } else {
            drawable = resources.getDrawable(drawableResId);
        }
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
    @SuppressLint("PrivateApi")
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

    public static Object invokeMethod(Object obj, String method, Object... parameters) {
        if (obj == null || TextUtils.isEmpty(method)) {
            return null;
        }

        Class<?>[] parameterTypes = new Class<?>[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            parameterTypes[i] = parameters[i].getClass();
        }

        try {
            Method methodWanted = obj.getClass().getDeclaredMethod(method, parameterTypes);
            methodWanted.setAccessible(true);
            return methodWanted.invoke(obj, parameters);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

}
