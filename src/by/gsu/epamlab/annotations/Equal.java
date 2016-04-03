/**
 * 
 */
package by.gsu.epamlab.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author rdv
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Equal {
	public enum CompareBy {
		REFERENCE, VALUE
	}
	
	CompareBy compareBy() default CompareBy.VALUE;
}
