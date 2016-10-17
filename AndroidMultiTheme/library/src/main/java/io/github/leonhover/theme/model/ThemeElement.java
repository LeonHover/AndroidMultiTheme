package io.github.leonhover.theme.model;

/**
 * Created by leonhover on 16-9-27.
 */

public class ThemeElement {

    private int tagKey;
    private String attrName;
    private String targetMethod;

    public ThemeElement(int tagKey, String attrName, String targetMethod) {
        this.tagKey = tagKey;
        this.attrName = attrName;
        this.targetMethod = targetMethod;
    }

    public int getTagKey() {
        return tagKey;
    }

    public String getAttrName() {
        return attrName;
    }

    public String getTargetMethod() {
        return this.targetMethod;
    }

    @Override
    public String toString() {
        return "ThemeElement:{ tagKey:" + this.tagKey + ", attrName:" + this.attrName + ",targetMethod:" + this.targetMethod + "}";
    }
}
