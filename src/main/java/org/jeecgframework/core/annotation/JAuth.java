package org.jeecgframework.core.annotation;

import org.jeecgframework.core.enums.Permission;

import java.lang.annotation.*;

/**
 * jeecg权限认证注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface JAuth {
    String value() default "";

    Permission auth() default Permission.SKIP_AUTH;
}