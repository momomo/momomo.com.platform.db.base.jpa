/* Copyright(C) 2014 - 2020 Momomo LTD. Proprietary and confidential. Usage of this file on any medium without a written consent by Momomo LTD. is strictly prohibited. All Rights Reserved. */
package momomo.com.db;

import momomo.com.$Maps;
import momomo.com.Is;
import momomo.com.Reflects;
import momomo.com.db.entities.$Entity;

import javax.validation.ConstraintViolation;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Used to build errors json map towards the frontend in a constent manner.
 * 
 * Example
 *  sendErrors( 
 *      new $EntityErrors()
 *      .add( new $EntityError(Account.Cons.password, $Password.CODE_PASSWORD_INCORRECT_LOGIN) )
 *  );
 * 
 * @author Joseph S.
 */
public class $EntityErrors extends HashMap<String, Map> implements Serializable {
    
    public static final class Cons {
        public static final String errors = "errors";
    }
    
    public $EntityErrors() {}
    
    public $EntityErrors(Class<? extends $Entity> association, $EntityError entityError) {
        this();
        add(association, entityError);
    }
    public $EntityErrors(Class<? extends $Entity> association, $EntityErrors entityErrors) {
        this();
        add(association, entityErrors);
    }
    
    public $EntityErrors(String association, $EntityErrors entityErrors) {
        this();
        add(association, entityErrors);
    }
    public $EntityErrors(String association, $EntityError entityError) {
        this();
        add(association, entityError);
    }
    
    public <T extends $Entity> $EntityErrors add(Set<ConstraintViolation<T>> hibernateErrors) {
        if ( Is.Ok(hibernateErrors) ) {
            for (ConstraintViolation<T> error : hibernateErrors) {
                add( new $EntityError(error) );
            }
        }
        
        return this;
    }
    
    public $EntityErrors add($EntityError entityError) {
        add(this, entityError);
        return this;
    }
    
    public $EntityErrors add(Class<? extends $Entity> association, $EntityError entityError) {
        return add(association.getSimpleName(), entityError);
    }
    public $EntityErrors add(String association, $EntityError entityError) {
        add(getOrCreateMapForProperty(this, association), entityError );
        return this;
    }
    
    public $EntityErrors add(Class<? extends $Entity> association, $EntityErrors entityErrors) {
        return add(association.getSimpleName(), entityErrors);
    }
    public $EntityErrors add(String association, $EntityErrors entityErrors) {
        if ( Is.Ok(entityErrors) ) {
            $Maps.merge(getOrCreateMapForProperty(this, association), entityErrors, true, true);
        }
        
        return this;
    }
    
    private static void add(Map map, $EntityError entityError) {
        HashMap sub = getOrCreateMapForProperty(map, entityError.property);
        
        HashSet<$EntityError> errors = Reflects.cast(sub.get(Cons.errors));
        if ( !Is.Ok(errors) ) {
            errors = new HashSet<>();
            
            sub.put(Cons.errors, errors);
        }
        errors.add(entityError);
    }
    
    private static HashMap getOrCreateMapForProperty(Map map, String property) {
        HashMap subMap = Reflects.cast(map.get(property));
        if ( !Is.Ok(subMap) ) {
            subMap = new HashMap<>();
            map.put(property, Reflects.cast(subMap));
        }
        return subMap;
    }
    
    public boolean isEmpty() {
        return this.size() == 0;
    }
    public boolean notEmpty() {
        return !isEmpty();
    }
    
    
    // TODO Consider making this error lookup recursive in order to support deeper hierarchies. We might have to change getOrCreateMapForProperty to create a new EntityErrors over a HashMap for this to work
    
    /**
     * Returns true for any error on field in association
     */
    public boolean hasError(String association, String field) {
        return hasError(association, field, null);
    }
    public boolean hasError(String association, String field, String code) {
        return hasError(this, association, field, code);
    }
    /*
    Not used, and not tested, but could be uncommented and should possibly work
    public boolean hasError(String field, String code) {
            return hasError(this, field, code);
    }*/
    private static boolean hasError($EntityErrors entityErrors, String association, String field, String code) {
        return hasError(entityErrors.get(association), field, code);
    }
    private static boolean hasError(Map<String, Map> map, String field, String code) {
        if ( Is.Ok(map) ) {
            map = map.get(field);
            if ( Is.Ok(map) ) {
                HashSet set = Reflects.cast( map.get(Cons.errors) );
                if ( Is.Ok(set) ) {
                    
                    for (Object o : set) {
                        $EntityError er = Reflects.cast(o);
                        /*
                         * Null can be passed as a code to signal that we are interested in any error
                         */
                        if ( er != null && (code == null || er.code.equals(code)) ) {
                            return true;
                        }
                        
                    }
                }
            }
        }
        
        return false;
    }
    
    
    
    
    
}
