package io.github.leonhover.theme;

/**
 * 暗黑模式设置类型
 */
public enum DarkMode {
    /**
     * 未开启
     */
    off,
    /**
     * 开启
     */
    on,
    /**
     * 如果支持的话，跟随系统设置，不支持就相当于打开
     */
    followSystem
}
