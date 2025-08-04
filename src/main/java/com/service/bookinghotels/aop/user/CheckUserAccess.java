package com.service.bookinghotels.aop.user;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckUserAccess {

//    long id() default 0L;
//    UserDetails user() default null;
}