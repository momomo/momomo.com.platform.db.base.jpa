/* Copyright(C) 2014 - 2020 Momomo LTD. Proprietary and confidential. Usage of this file on any medium without a written consent by Momomo LTD. is strictly prohibited. All Rights Reserved. */
package momomo.com.db.entities;

import momomo.com.Randoms;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * @author Joseph S. 
 */
@MappedSuperclass public abstract class $EntityVersionLong implements $EntityVersion<Long> {
    
    private @Version Long version = Randoms.Long();
    
    @Override
    public Long getVersion() {
        return version;
    }
    
    @Override
    public Long setVersion(Long version) {
        return this.version = version;
    }
}
