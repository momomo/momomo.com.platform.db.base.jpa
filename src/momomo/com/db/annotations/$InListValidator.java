/* Copyright(C) 2014 - 2020 Momomo LTD. Proprietary and confidential. Usage of this file on any medium without a written consent by Momomo LTD. is strictly prohibited. All Rights Reserved. */
package momomo.com.db.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Makes sure a value on an entity exists in this list
 * 
 * @author Joseph S.
 */
public class $InListValidator implements ConstraintValidator<$InList, Object> {
    
    private $InList annotation;
    
    @Override
    public void initialize($InList annotation) {
        this.annotation = annotation;
    }
    
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintContext) {
        if ( value == null ) {
            return true;
        }
        
        value = value.toString();
        
        for ( String val : annotation.value() ) {
            if ( val.equals(value) ) {
                return true;
            }
        }
        
        return false;
    }
    
}
