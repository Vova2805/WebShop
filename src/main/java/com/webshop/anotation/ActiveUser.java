package com.webshop.anotation;

import java.lang.annotation.*;

/**
 * Used for getting logged user during request
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ActiveUser {
}