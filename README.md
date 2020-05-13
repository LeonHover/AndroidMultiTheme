## 概述

Android-Mulit-Theme可以让轻松地对Android应用添加多主题支持，并且支持在不销毁重建Activity的情况下动态切换主题。利用Android自身支持的不同Style中可复写相同的attribute的值的特性，通过代码动态设置不同的Style来达到不同主题的切换效果。它支持静态设置控件使用主题元素的方式——layout的xml中定义控件时使用，也支持程序动态设置——主题控件辅助类来动态改变主题元素。

*与Android提倡的主题设置思想一致，通过设置不同的ThemeResource来切换主题。*
*改变主题不会重新创建Activity，自然切换，体验更好。*
*适配了Android Q的暗黑模式，新增可以跟随系统主题设置来切换主题。*
*目前已经支持Style中设置attrRes来支持多主题控件属性切换。*
*扩展性好，可以自由结合自定义的控件编写控制主题的辅助类。*

## 版本

根据自己的选择决定使用对应的版本
v1.0.0版本基于android.support.library构建
v2.0.0版本基于androidx构建

 [![Download](https://api.bintray.com/packages/leonhover/android/Android-Multi-Theme/images/download.svg) ](https://bintray.com/leonhover/android/Android-Multi-Theme/_latestVersion)

 ![效果图](https://github.com/LeonHover/Android-Multi-Theme/blob/master/assets/2016-11-09%2011_57_38.gif)

## 快速使用

1. 修改build.gradle

```
    compile 'io.github.leonhover:android-multi-theme:$version'
```

2. 在attrs.xml中定义主题控制的attr

```  
    <attr name="title_text_color" format="reference|color"/>
```

3. 定义不同的Activity主题

```
    <style name="AppTheme.Red" parent="AppTheme">
        ...
        <item name="title_text_color">@color/red_title_text_color</item>
        ...
    </style>

    <style name="AppTheme.Blue" parent="AppTheme">
        ...
        <item name="title_text_color">@color/blue_title_text_color</item>
        ...
    </style>
```

4. 对Acitivity设定主题集合

```
    activityTheme.setThemes(new int[]{R.style.AppTheme_Blue,
       R.style.AppTheme_Red});
```
5. 在XML中静态使用

```
    <TextView
      android:id="@+id/theme_title"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      android:layout_toRightOf="@+id/theme_img"
      android:padding="3dp"
      android:textColor="?attr/title_text_color"
      android:textSize="18sp" />
```

6. 在代码中动态使用

```
        attrChanged = !attrChanged;
        int textColorAttrId = attrChanged ? R.attr.sub_title_text_color :
        R.attr.title_text_color;
        MultiTheme.applySingleElementTheme(android.R.attr.textColor, textColorAttrId);
```

7. 切换主题

```
            MultiTheme.setAppTheme(indexOfthemes);//blue 0,red 1;
```

8. 暗黑主题

```
            MultiTheme.setDarkMode(darkMode); // DarkMode.off(关),DarkMode.on（开）,DarkMode.followSystem（跟随系统主题）;
```

## 进阶
1. CoverImageView与CoverImageWidget

  在主题的需求中，有一些情况一般是通过对图片添加不同主题的颜色蒙层来实现改变主题效果。CoverImageView就是这样的自定义ImageView，添加attribute——coverColor以及setCoverColor(int color)方法。支持对任意形状的图标控件添加颜色蒙层效果，而且不会忽略图片中的alpha值。

2. 自定义主题控件

  开发中，我们都会遇到需要自定义View来满足需求得情况，进阶第1项中的CoverImageView以及CoverImageWidget就是很好的例子，可供参考。
  在0.3.x版本以后，添加注解扩展主题控件属性方式，可参考TextViewWidget的编写。

3. AdapterView

  由于AdapterView具有ItemView的缓存机制，所以不一定能在Window的decorView中找到，这个就需要在View被attach到window的时候应用下当前的主题Style。
  Sample中写了RecyclerView的使用场景。
  ListView的Adapter中，应该在getView方法中调用：
  ```  
        MultiTheme.applyTheme(holder.itemView);
  ```
4. "放养的孩子"

  AdapterView中添加HeaderView或FooterView的情况，他们无法再AdapterView的Adapter回调方法中知道何时attach到Window上。这种就需要我们自己记录，并在主题切换的时候，主动对View的应用主题。
  `ThemeViewEntities`就是这种情况的辅助类，只需要将上面情况的View添加到`ThemeViewEntities`中，主题框架就会自动管理这些View的主题。

## 注意
1. 框架利用AppCompatActivity中的接口覆盖了XML解释的部分逻辑，所以这个框架只支持AppCompatActivity内使用。
2. 出现inflate的异常，一般情况是没有attrRes指向的资源在当前使用的Style中。
