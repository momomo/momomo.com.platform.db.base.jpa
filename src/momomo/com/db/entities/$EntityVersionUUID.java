/* Copyright(C) 2014 - 2020 Momomo LTD. Proprietary and confidential. Usage of this file on any medium without a written consent by Momomo LTD. is strictly prohibited. All Rights Reserved. */
package momomo.com.db.entities;

import momomo.com.Randoms;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.UUID;

/**
 * @author Joseph S. 
 */
@MappedSuperclass public abstract class $EntityVersionUUID implements $EntityVersion<UUID> {
    
    @Version private UUID version = Randoms.UUID();
    
    @Override
    public UUID getVersion() {
        return version;
    }
    
    @Override
    public UUID setVersion(UUID version) {
        return this.version = version;
    }
    
}
