package io.github.leonhover.theme;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * Created by leonhover on 16-9-28.
 */

public class ThemeViewCreator {

    private static final Class<?>[] constructorSignature = new Class[]{
            Context.class, AttributeSet.class};

    private static String[] sClassPrefixs = {
            "android.widget.",
//            "android.app."
    };

    private static String[] sViewPostfixs = {
            "View",
            "SurfaceView",
            "TextureView",
    };

    private static String[] sExcludeTags = {
            "fragment",
            "merge",
            "include",
            "ViewStub",
    };


    protected View createView(View parent, String name, Context context, AttributeSet attrs) {
        View view = null;
        if (name.contains(".")) {
            view = createSubViewImpl(parent, "", name, context, attrs);
        } else {
            if (Arrays.asList(sExcludeTags).contains(name)) {
                return null;
            } else if (Arrays.asList(sViewPostfixs).contains(name)) {
                view = createSubViewImpl(parent, "android.view.", name, context, attrs);
            } else {
                for (String prefix : sClassPrefixs) {
                    view = createSubViewImpl(parent, prefix, name, context, attrs);
                    if (view != null) {
                        break;
                    }
                }
            }
        }

        return view;
    }

    private View createSubViewImpl(View parent, String prefix, String name, Context context, AttributeSet attrs) {

        Constructor<?> constructor = null;
        Class<?> clazz = null;
        try {
            clazz = context.getClassLoader()
                    .loadClass(prefix + name).asSubclass(View.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (clazz == null) {
            return null;
        }

        //获取构造函数
        try {
            constructor = clazz.getConstructor(constructorSignature);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            if (constructor != null) {
                constructor.setAccessible(true);
                return (View) constructor.newInstance(context, attrs);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

}
