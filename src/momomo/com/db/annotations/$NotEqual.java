/* Copyright(C) 2014 - 2020 Momomo LTD. Proprietary and confidential. Usage of this file on any medium without a written consent by Momomo LTD. is strictly prohibited. All Rights Reserved. */
package momomo.com.db.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Joseph S.
 */
@Target( { METHOD, FIELD, ANNOTATION_TYPE}) @Retention(RUNTIME)
@Constraint(validatedBy = $NotEqualValidator.class)
@Documented
public @interface $NotEqual {
    
    String value();
    
    String message() default "{momomo.com.constraints.$NotEqual.message}";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
}
