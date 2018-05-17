package tcp.playground.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface FindableMethod {
 
  String text() default "Default text for static text annotation";
  String value() default "Default value";
  
}