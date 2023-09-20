package momomo.com.db;

import momomo.com.db.entities.$Entity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Keeps track of iterations over found things
 * 
 * @author Joseph S.
 */
public class $EntityCache { private $EntityCache() {}
    private static final HashMap<Class<? extends $Entity>, $EntityCache> CACHE = new HashMap<>();
    
    /**
     * Unique fields cache per T
     */
    public final HashSet<Field> associations = new HashSet<>();
    public final HashSet<Field> uniques      = new HashSet<>();
    public final HashSet<Field> passwords    = new HashSet<>();

    public static <Y extends $Entity> $EntityCache get(Class<Y> entityClass) {
        $EntityCache cache = CACHE.get(entityClass);
        
        if ( cache == null ) {
            cache = new $EntityCache(); CACHE.put(entityClass, cache);
        }
        
        return cache;
    }
}
