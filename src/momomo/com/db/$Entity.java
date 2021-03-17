/* Copyright(C) 2014 - 2020 Momomo LTD. Proprietary and confidential. Usage of this file on any medium without a written consent by Momomo LTD. is strictly prohibited. All Rights Reserved. */
package momomo.com.db;

import momomo.com.Is;
import momomo.com.Lambda;
import momomo.com.annotations.informative.Private;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author Joseph S.
 */
@MappedSuperclass public interface $Entity extends Serializable {
    
    /////////////////////////////////////////////////////////////////////
    
    static final String FIND_BY_ENTITY = "!!!###FIND_IGNORE###!!!";
    
    /////////////////////////////////////////////////////////////////////
    
    class Cons {
        public static final String owner = "owner";
        
        public static final String DISCRIMINATOR_COLUMN    = "class";
        public static final long   INDEX_START             = 1L;
        
        public static final String GENERATOR_ID = "momomo.com.SequenceStyleGenerator";
        public static final String GENERATOR_STRATEGY = "org.hibernate.id.enhanced.SequenceStyleGenerator";
        
    }
    
    /////////////////////////////////////////////////////////////////////
    
    interface Events {
        default void beforeSave() {
            // Do nothing
        }
        
        default void afterSave() {
            // Do nothing
        }
    }
    
    /////////////////////////////////////////////////////////////////////
    
    /**
     * @author Joseph S.
     */
    public static abstract class Setter<T extends $EntityId, S extends Setter<T, S>> {
        protected final S THIS() {
            return (S) this;
        }
        
        /**
         * Not supposed to be called by users, only implemented
         */
        @Private("To only place which calls it") public abstract T set(T entity);
        
        protected final <TYPE, E extends Exception> boolean ok(TYPE obj, Lambda.VE<E> lambda) throws E {
            return ok(obj, lambda, null);
        }
        
        protected final <TYPE, E extends Exception> boolean ok(TYPE obj, Lambda.VE<E> lambda, Lambda.VE<E> lambdaElse) throws E {
            return ok(obj, lambda.V1E(), lambdaElse);
        }
        
        protected final <TYPE, E extends Exception> boolean ok(TYPE obj) throws E {
            return ok(obj, (Lambda.V1E<TYPE, RuntimeException>) null);
        }
        
        protected final <TYPE, E extends Exception> boolean ok(TYPE obj, Lambda.V1E<TYPE, E> lambda) throws E {
            return ok(obj, lambda, null);
        }
        
        protected final <TYPE, E extends Exception> boolean ok(TYPE obj, Lambda.V1E<TYPE, E> lambda, Lambda.VE<E> lambdaElse) throws E {
            boolean isBoolean;
            if (obj != null && ((isBoolean = (obj instanceof Boolean)) && Is.True((Boolean) obj) || !isBoolean)) {
                if (lambda != null) {
                    lambda.call(obj);
                }
                
                return true;
            } else if (lambdaElse != null) {
                lambdaElse.call();
            }
            
            return false;
        }
        
    }
    
    /////////////////////////////////////////////////////////////////////
}
