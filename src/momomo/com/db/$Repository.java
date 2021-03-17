package momomo.com.db;

/**
 * @author Joseph S.
 */
public interface $Repository extends $RepositoryPersistence, $SqlOperations, $RepositoryOther {

    @Override
    default $Repository repository() {
        return this;
    }

}
