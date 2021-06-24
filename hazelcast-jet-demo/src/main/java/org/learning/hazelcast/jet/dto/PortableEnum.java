package org.learning.hazelcast.jet.dto;

import com.hazelcast.nio.serialization.Portable;
import lombok.Getter;

@Getter
public enum  PortableEnum {

    ACCOUNT_CLASS(1,5,"accountMap") {
        @Override
        public Portable getPortable() {
            return null;
        }
    },
    SDDAACCOUNT_CLASS(1,5,"sddaaccountmap") {
        @Override
        public Portable getPortable() {
            return null;
        }
    };

    private final int classId;
    private final int factoryId;
    private final String mapName;

     PortableEnum(int classId, int factoryId, String mapName) {
        this.classId = classId;
        this.factoryId = factoryId;
        this.mapName = mapName;
    }

    public abstract Portable getPortable();


}
