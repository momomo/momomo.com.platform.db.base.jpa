package momomo.com.db;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author Joseph S.
 */
public final class $EntityValidation {
    private static final ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();
    public  static final Validator        VALIDATOR         = VALIDATOR_FACTORY.getValidator();
}
