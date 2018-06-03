package com.itheima.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface JdbcAnnotation {
	// driverClassName=com.mysql.jdbc.Driver
	// url=jdbc:mysql:///student
	// user=root
	// password=1234
	String driverClassName() default "com.mysql.jdbc.Driver";
	String url();
	String user() default "root";
	String password() default "1234";
}
