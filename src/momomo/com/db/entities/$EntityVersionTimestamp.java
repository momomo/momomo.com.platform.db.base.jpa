/* Copyright(C) 2014 - 2020 Momomo LTD. Proprietary and confidential. Usage of this file on any medium without a written consent by Momomo LTD. is strictly prohibited. All Rights Reserved. */
package momomo.com.db.entities;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.sql.Timestamp;

@MappedSuperclass
public abstract class $EntityVersionTimestamp implements $EntityVersion<Timestamp> {
    
    @Version
    Timestamp version;
    
    @Override
    public Timestamp getVersion() {
        return version;
    }
    
    @Override
    public Timestamp setVersion(Timestamp version) {
        return this.version = version;
    }
    
}
