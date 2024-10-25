/* Copyright(C) 2014 - 2020 Momomo LTD. Proprietary and confidential. Usage of this file on any medium without a written consent by Momomo LTD. is strictly prohibited. All Rights Reserved. */
package momomo.com.db.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Checks that the value set is in a provided list.
 *
 * @author Joseph S.
 */
@Target( { METHOD, FIELD, ANNOTATION_TYPE}) @Retention(RUNTIME)
@Constraint(validatedBy = $InListValidator.class)
@Documented
public @interface $InList {
    
    String[] value() default {};
    
    String message() default "{momomo.com.constraints.$InList.message}";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
}
