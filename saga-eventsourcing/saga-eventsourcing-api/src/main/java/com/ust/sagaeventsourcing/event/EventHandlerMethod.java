package com.ust.sagaeventsourcing.event;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotacion que determina qué metodos se lanzan tras recibir uno de los eventos de la lista
 * @author fcabrero
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventHandlerMethod {

//	public String[] events() default ""; // Un array es buena opción? Si lo combinamos con el estado ya no lo es tanto
	public String event() default "";
	public String status() default "";
		
}