package com.du.common.annotation;

import java.lang.annotation.*;

/**
 * @author dxy
 * @date 2018/7/9 15:30
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Id {
}
