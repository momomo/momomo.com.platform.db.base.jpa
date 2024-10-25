/* Copyright(C) 2014 - 2020 Momomo LTD. Proprietary and confidential. Usage of this file on any medium without a written consent by Momomo LTD. is strictly prohibited. All Rights Reserved. */
package momomo.com.db;

import momomo.com.annotations.$Exclude;
import momomo.com.db.entities.$Entity;

import javax.validation.ConstraintViolation;
import java.io.Serializable;

/**
 * @see momomo.com.db.$EntityErrors
 * 
 * @author Joseph S.
 */
public final class $EntityError implements Serializable, Comparable<$EntityError> {
    private static final String SUBSTRING_MESSAGE_CODE_FROM = "constraints.";

    @$Exclude public final String property;
    @$Exclude public       Object value;
              public final String code;

    public $EntityError(ConstraintViolation<? extends $Entity> error) {
        property   = error.getPropertyPath().toString();
        this.code  = code(error.getMessageTemplate());
        this.value = error.getInvalidValue();

    }
    public $EntityError(String property, String code) {
        this(property, code, null);
    }
    public $EntityError(String property, String code, Object value) {
        this.property = property;
        this.code     = code(code);
        this.value    = value;
    }

    private String code(String code) {
        if ( code.endsWith("}") ) {
            code = code.substring( code.indexOf(SUBSTRING_MESSAGE_CODE_FROM) + SUBSTRING_MESSAGE_CODE_FROM.length(), code.length()-1 );
        }
        return code;
    }

    @Override
    public int compareTo($EntityError o) {
        return (this.property + this.code).compareTo(o.property + o.code);
    }
}
