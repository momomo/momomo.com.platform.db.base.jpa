package momomo.com.db;

import momomo.com.db.$Repository;

/**
 * Note, we need this hack to make Java "get" that it is the same method provided by an implementing interface when a Service is extending an abstract class.
 * The abstract class indeed need to implements this exact method through an interface and not a cloned identical declaration, ie: "public abstract RepositoryApp repository()" won't work.
 * 
 * @author Joseph S.
 */
public interface $RepositoryDeclaration {
    $Repository repository();
}
