package momomo.com.db;

import momomo.com.db.entities.$Entity;
import momomo.com.db.entities.$EntityId;
import momomo.com.sources.Constants;
import momomo.com.Is;
import momomo.com.Lambda;
import momomo.com.Reflects;
import momomo.com.db.annotations.$Password;
import momomo.com.db.annotations.$Unique;

import java.lang.reflect.Field;
import java.util.HashSet;

/**
 * @author Joseph S.
 */
public interface $RepositoryValidation extends $RepositoryDeclaration {

    <T extends $Entity> T findByField(Class<T> entityClass, String property, Object value);

    /////////////////////////////////////////////////////////////////////

    /**
     * Goes beyong the hibernate validation and performs a deep validation.
     */
    default <T extends $Entity> $EntityErrors validate(T entity){
        return validate(entity, null);
    }

    default <T extends $Entity> $EntityErrors validate(T entity, $EntityErrors errors){
        $EntityCache cache = $EntityCache.get(entity.getClass());
        return validate(entity, cache, errors);
    }

    default <T extends $EntityId> ValidateSaveResult<T> validateSave(T entity) {
        return validateSave(entity, null);
    }

    default  <T extends $EntityId> ValidateSaveResult<T> validateSave(T entity, $EntityErrors errors) {
        $EntityCache cache = $EntityCache.get(entity.getClass());

        errors = validate(entity, cache, errors);

        if (errors.isEmpty( )) {
            T saved = repository().save(entity);

            return new ValidateSaveResult<>(saved, null);
        }
        else {
            return new ValidateSaveResult<>(null, errors);
        }
    }

    /////////////////////////////////////////////////////////////////////
    public static final class ValidateSaveResult<T extends $Entity> {
        private final T            entity;
        private final $EntityErrors errors;

        private ValidateSaveResult(T entity, $EntityErrors errors) {
            this.entity = entity;
            this.errors = errors;
        }

        public <E1 extends Exception, E2 extends Exception> boolean on(Lambda.V1E<T, E1> success, Lambda.V1E<$EntityErrors, E2> error) throws E1, E2 {
            // Has errors
            if ( Is.Ok(errors) ) {
                error.call(errors);

                return false;
            }
            // Was saved
            else {
                success.call(entity);

                return true;
            }
        }

        public boolean success() {
            return on(e -> {}, e -> {});
        }
        public boolean errors() {
            return !success();
        }

        public T getEntity() {
            return entity;
        }

        public $EntityErrors getErrors() {
            return errors;
        }
    }
    /////////////////////////////////////////////////////////////////////

    private <T extends $Entity> $EntityErrors validate(T entity, $EntityCache cache, $EntityErrors errors) {
        if ( errors == null ) {
            errors = new $EntityErrors( );

            errors.add( $EntityValidation.VALIDATOR.validate( entity ) );
        }

        loadProperties(entity, cache);

        validateAssociations(entity, cache.associations, errors);

        if ( repository().isNew(entity) ) {
            validateUniques(entity, cache.uniques, errors);
        }

        validatePasswords (entity, cache.passwords, errors);

        return errors;
    }

    private <T extends $Entity> void loadProperties(T entity, $EntityCache cache) {
        // Only called once per entity, unless in development
        if ( !Is.Production() || !( Is.Ok(cache.associations) && Is.Ok(cache.uniques) ) ) {
            cache.uniques      .clear();
            cache.associations .clear();

            // Loop through annotations, here look for @Unique
            Field[] fields = Reflects.getFieldsLocal(entity.getClass());
            for ( Field field : fields ) {

                if ( repository().isAssociation(Reflects.getValue(entity, field)) ) {
                    cache.associations.add(field);
                }

                if (field.isAnnotationPresent( $Unique.class )) {
                    cache.uniques.add(field);
                }

                if (field.isAnnotationPresent( $Password.class )) {
                    cache.passwords.add(field);
                }
            }
        }
    }

    private <T extends $Entity> void validateAssociations(T entity, HashSet<Field> associations, $EntityErrors errors) {
        // Validate all associations
        for ( Field field : associations ) {
            validateAssociation(entity, field, errors);
        }
    }

    private <T extends $Entity> void validateUniques(T entity, HashSet<Field> uniques, $EntityErrors errors) {
        // Also, all that explicitly declare to be $uniques shall be DB validated by this field value
        // @See buildCriteria
        for ( Field field : uniques) {
            validateUnique(entity, field, errors);
        }
    }

    private <T extends $Entity> void validatePasswords(T entity, HashSet<Field> passwords, $EntityErrors errors) {
        for ( Field field : passwords) {
            validatePassword(entity, field, errors);
        }
    }

    // Validation for the masses. Can be used on a random entity and field. 
    // Not really that useful outside of the build in validation.
    // Currently left private unless shown to be needed later.
    private <Z extends $Entity, T extends $Entity> void validateAssociation(T entity, Field field, $EntityErrors errors) {
        Z             subEntity = Reflects.cast(Reflects.getValue(entity, field));
        $EntityErrors subErrors = validate(subEntity);

        errors.add(field.getType().getSimpleName(), subErrors);
    }


    private <T extends $Entity> void validateUnique(T entity, Field field, $EntityErrors errors) {
        String property = field.getName();

        Object value = Reflects.getValue(entity, field);

        Class<T> entityClass = Reflects.cast(entity.getClass());

        T t = findByField(entityClass, property, value);

        if (Is.Ok(t)) {

            $EntityError entityError = new $EntityError(
                    property,
                    $Unique.message, value
            );

            if ( repository().isAssociation(value) ) {
                errors.add(field.getType().getSimpleName(), entityError);
            } else {
                errors.add(entityError);
            }
        }
    }

    private <T extends $Entity> void validatePassword(T entity, Field field, $EntityErrors errors) {
        String property = field.getName();
        Object value    = Reflects.getValue(entity, field);

        String error = null;
        if ( Constants.Password.PASSWORD_INCORRECT_REPEAT_VAL.equals(value) ) {
            error = $Password.INCORRECT_REPEAT;
        }
        else if ( Constants.Password.PASSWORD_INCORRECT_LENGTH_VAL.equals(value) ) {
            error = $Password.INCORRECT_LENGTH;
        }

        if ( error != null ) {
            errors.add( new $EntityError(property, error, "!") );  // We do not include the actual password
        }
    }

    /////////////////////////////////////////////////////////////////////

}
