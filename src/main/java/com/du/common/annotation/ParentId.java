package com.du.common.annotation;

import java.lang.annotation.*;

/**
 * 标识父级ID字段
 *
 * @author dxy
 * @date 2018/7/9 14:45
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParentId {
}
