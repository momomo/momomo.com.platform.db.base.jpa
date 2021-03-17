package momomo.com.db;

import momomo.com.annotations.informative.Private;

import javax.persistence.Embeddable;

/**
 * @author Joseph S.
 *
 */
public interface $RepositoryOther {
    
    Object  getId($Entity entity);
    boolean hasId($Entity entity);
    boolean isNew($Entity entity);
    
    /////////////////////////////////////////////////////////////////////
    
    default Object getId($EntityId entity) {
        return entity.getId();
    }
    default boolean hasId($EntityId entity) {
        return getId(entity) != null;
    }
    
    /////////////////////////////////////////////////////////////////////
    
    default boolean isAssociation( Object obj ) {
        return obj instanceof $Entity;
    }
    default boolean isEmbedded( Object obj ) {
        return obj.getClass().getDeclaredAnnotation(Embeddable.class) != null;
    }
    
    
    @Private final class Static {
        public static boolean isAssociation( Class<?> klass ) {
            return $Entity.class.isAssignableFrom(klass);
        }
        public static boolean isAssociation( Object obj ) {
            return obj instanceof $Entity;
        }
    }
    
}
