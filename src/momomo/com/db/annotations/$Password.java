/* Copyright(C) 2014 - 2020 Momomo LTD. Proprietary and confidential. Usage of this file on any medium without a written consent by Momomo LTD. is strictly prohibited. All Rights Reserved. */
package momomo.com.db.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validates uniqueness prior to being saved at the DB level. This is useful to be able to return a correct error message to the user rather than just failing at the DB level
 * Logic used for this is done in $ValidationSupport when validate() is called
 * 
 * @author Joseph S.
 */
@Target( { METHOD, FIELD, ANNOTATION_TYPE}) @Retention(RUNTIME) public @interface $Password {
    
    String CODE_PASSWORD_INCORRECT_LOGIN  = "incorrect.login";
    String CODE_PASSWORD_INCORRECT_REPEAT = "incorrect.repeat";
    String CODE_PASSWORD_INCORRECT_LENGTH = "incorrect.length";

    String INCORRECT_REPEAT = "{momomo.com.constraints." + CODE_PASSWORD_INCORRECT_REPEAT + "}";
    String INCORRECT_LENGTH = "{momomo.com.constraints." + CODE_PASSWORD_INCORRECT_LENGTH + "}";
    
}
