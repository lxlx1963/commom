package com.du.common.annotation;

import java.lang.annotation.*;

/**
 * 标识子孙地段注解
 *
 * @author dxy
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Children {
}
