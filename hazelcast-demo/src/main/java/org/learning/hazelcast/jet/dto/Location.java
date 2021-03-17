package org.learning.hazelcast.jet.dto;


import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location implements Portable {

    public final static int ID = 5;

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
        return ID;
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
}
