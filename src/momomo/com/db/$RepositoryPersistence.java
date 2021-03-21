package momomo.com.db;

import momomo.com.Lambda;
import momomo.com.annotations.informative.Protected;
import momomo.com.db.entities.$Entity;
import momomo.com.exceptions.$DatabaseSaveException;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author Joseph S.
 */
public interface $RepositoryPersistence extends $RepositoryDeclaration {
    
    /////////////////////////////////////////////////////////////////////
    
    @Protected
    <T extends $Entity> void $save$(T entity);
    
    /////////////////////////////////////////////////////////////////////
    
    @Protected
    <T extends $Entity> void persist (T entity);
    
    <T extends $Entity> boolean contains(T entity);
    
    <T extends $Entity> T merge(T entity);
    
    <T extends $Entity> void refresh(T entity);
    
    <T extends $Entity> T load(Class<T> entityClass, Serializable id);
    
    <T extends $Entity> void delete(T entity);
    
    void clear();
    
    
    /**
     * Do note, a flush should sometimes be cleared using clear() 
     */ 
    void flush();
    default void flush(boolean flush) {
        if ( flush ) {
            flush();
        }
    }
    
    /////////////////////////////////////////////////////////////////////
    
    default <T extends $Entity> T saveIfNew(T entity) {
        if ( repository().isNew(entity) ) {
            return save(entity);
        }
        return entity;
    }
    
    default <T extends $Entity> T save(T entity) {
        return this.save(entity, (e) -> {
            if ( repository().isNew(e) ) {
                // If still new we throw exception
                throw new $DatabaseSaveException();
            }
        });
    }
    
    default <T extends $Entity> void save(Collection<T> collection) {
        for (T t : collection) {
            save(t);
        }
    }
    
    default <T extends $Entity> T save(T entity, Lambda.V1<T> confirm) {
        if ( entity instanceof $Entity.Events) {
            (($Entity.Events) entity).beforeSave();
        }
        
        try {
            $save$(entity);
        } catch (Throwable e) {
            // Will throw a detached exception if detached, so we try to merge if that happens
            
            try {
                entity = merge(entity);
            } catch (Throwable ee) {
                throw e;
            }
        }
        
        if ( confirm != null ) {
            confirm.call(entity);
        }
        
        if ( entity instanceof $Entity.Events) {
            (($Entity.Events) entity).afterSave();
        }
        
        return entity;
    }
    
    /////////////////////////////////////////////////////////////////////
    
    default <T extends $Entity> void delete (T entity, boolean flush) {
        delete(entity);
        
        if ( flush ) flush(flush);
    }
    
    default <T extends $Entity> void delete(List<T> entities) {
        for (T entity : entities) {
            delete(entity);
        }
    }
    
    default <T extends $Entity> void delete(List<T> entities, boolean flush) {
        delete(entities);
        
        // For safety, if a delete is done, in order to be able to add right after, we need/should flush to get desired behaviour
        if (  flush )  flush();
    }
    
    /////////////////////////////////////////////////////////////////////
    
}
