/* Copyright(C) 2014 - 2020 Momomo LTD. Proprietary and confidential. Usage of this file on any medium without a written consent by Momomo LTD. is strictly prohibited. All Rights Reserved. */
package momomo.com.db.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Joseph S.
 */
public class $NotEqualValidator implements ConstraintValidator<$NotEqual, Object> {
    
    private $NotEqual annotation;
    
    @Override
    public void initialize($NotEqual annotation) {
        this.annotation = annotation;
    }
    
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintContext) {
        if ( object == null ) {
            return true;
        }
        
        return !annotation.value().equals(object);
    }
    
}
