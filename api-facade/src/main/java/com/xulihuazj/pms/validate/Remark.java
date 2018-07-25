package com.xulihuazj.pms.validate;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Remark {

    String value() default "";
}
