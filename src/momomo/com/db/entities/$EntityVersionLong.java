/* Copyright(C) 2014 - 2020 Momomo LTD. Proprietary and confidential. Usage of this file on any medium without a written consent by Momomo LTD. is strictly prohibited. All Rights Reserved. */
package momomo.com.db.entities;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class $EntityVersionLong implements $EntityVersion<Long> {
    
    @Version
    Long version;
    
    @Override
    public Long getVersion() {
        return version;
    }
    
    @Override
    public Long setVersion(Long version) {
        return this.version = version;
    }
    
}
