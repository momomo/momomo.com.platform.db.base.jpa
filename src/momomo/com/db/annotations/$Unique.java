/* Copyright(C) 2014 - 2020 Momomo LTD. Proprietary and confidential. Usage of this file on any medium without a written consent by Momomo LTD. is strictly prohibited. All Rights Reserved. */
package momomo.com.db.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validates uniqueness prior to being saved at the DB level. 
 * 
 * This is useful to be able to return a correct error message to the user rather than just failing at the DB level with some random exception. 
 *
 * @author Joseph S.
 */
@Target( { METHOD, FIELD, ANNOTATION_TYPE}) @Retention(RUNTIME) public @interface $Unique {
    String CODE_UNIQUE = "unique.fail";
    
    String message = "{momomo.com.constraints." + CODE_UNIQUE + "}";
    
}
