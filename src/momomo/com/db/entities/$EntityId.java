/* Copyright(C) 2014 - 2020 Momomo LTD. Proprietary and confidential. Usage of this file on any medium without a written consent by Momomo LTD. is strictly prohibited. All Rights Reserved. */
package momomo.com.db.entities;

import momomo.com.db.entities.$Entity;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author Joseph S.
 */
@MappedSuperclass public interface $EntityId<T extends Serializable> extends $Entity {
    public static class Cons extends $Entity.Cons {
        public static final String id = "id";
    }
    
    T getId();
}
