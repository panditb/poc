package org.learning.cache.hazelcast.entity;

import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;
import com.hazelcast.nio.serialization.VersionedPortable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;

/**
 * Entity table for User
 */
@Entity
@Table(name="location")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location implements VersionedPortable {
    public final static int ID = 5;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="location_id")
    private Integer locationId;

    private String name;

    private String accountId;
    private String currency;

    private String region;
    private boolean isActive;

    @Override
    public int getFactoryId() {
        return 1;
    }

    @Override
    public int getClassId() {
        return 5;
    }

    @Override
    public void writePortable(PortableWriter writer) throws IOException {
        writer.writeInt( "l", locationId );
        writer.writeUTF("n" , name);
        writer.writeUTF("a", accountId);
        writer.writeUTF("c", currency);
        writer.writeUTF("r", region);
        writer.writeBoolean("isA", isActive);

    }

    @Override
    public void readPortable(PortableReader reader) throws IOException {
        reader.readInt("l");
        reader.readUTF("n" );
        reader.readUTF("a");
        reader.readUTF("c");
        reader.readUTF("r");
        reader.readBoolean("isA");

    }

    @Override
    public int getClassVersion() {
        return 1;
    }
}
