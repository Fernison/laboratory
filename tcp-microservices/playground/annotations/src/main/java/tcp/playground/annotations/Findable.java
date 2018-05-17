package tcp.playground.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
 
// Make the annotation available at runtime:
@Retention(RetentionPolicy.RUNTIME)
// Allow to use only on types:
@Target(ElementType.TYPE)
public @interface Findable {
 
    /**
     * User friendly name of annotated class.
     */
    String name();
}
