/* Copyright(C) 2014 - 2020 Momomo LTD. Proprietary and confidential. Usage of this file on any medium without a written consent by Momomo LTD. is strictly prohibited. All Rights Reserved. */
package momomo.com.db;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author Joseph S.
 */
@MappedSuperclass public interface $EntityId<ID extends Serializable> extends $Entity {
    public static class Cons extends $Entity.Cons {
        public static final String id = "id";
    }
    
    ID getId();
}
