package com.du.common.export.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 14-5-6.
 */
public class Entity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String text;
    private String type;

    /**
     * 设置excel列宽，必须输入大于零小于255*256的整数
     */
    private String width;

    public String getNeme() {
        return this.name; }

    public void setNeme(String neme) {
        this.name = neme;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
}
