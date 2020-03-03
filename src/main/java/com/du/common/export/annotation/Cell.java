package com.du.common.export.annotation;

import java.lang.annotation.*;

/**
 * excel导出注解
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Cell {
    public abstract String title();
}