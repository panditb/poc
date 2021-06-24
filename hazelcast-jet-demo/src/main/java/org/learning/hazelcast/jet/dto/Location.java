package org.learning.hazelcast.jet.dto;


import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;
import lombok.*;
import lombok.Data;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location implements Portable {

    public final static int ID = 7;

    private Integer locationId;

    private String name;

    private String accountId;
    private String currency;

    private String region;
    private boolean isActive;
    private Date calendarDate;
    private LocalDate localDate;


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
        writer.writeInt("l", locationId);
        writer.writeString("n", name);
        writer.writeString("a", accountId);
        writer.writeString("c", currency);
        writer.writeString("r", region);
        writer.writeBoolean("isA", isActive);
        writer.writeString("calendarDate", DateConverter.convertToString(calendarDate));
        writer.writeDate("localDate", localDate);

    }

    @SneakyThrows
    @Override
    public void readPortable(PortableReader reader) {
        locationId = reader.readInt("l");
        name = reader.readString("n");
        accountId = reader.readString("a");
        currency = reader.readString("c");
        region = reader.readString("r");
        isActive = reader.readBoolean("isA");
        calendarDate = DateConverter.convertTodate(reader.readString("calendarDate"));
        localDate = reader.readDate("localDate");
    }
}
