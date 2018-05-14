package com.ust.sagaeventsourcing.event;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotacion que identifica todos los eventos que se recibiran de una determinada saga
 * @author fcabrero
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventSubscriber {

	public String application() default "";
	public String saga() default "";
	
}