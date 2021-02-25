package org.learning.hazelcast.jet.job;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.function.FunctionEx;
import com.hazelcast.jet.JetInstance;
import com.hazelcast.jet.Util;
import com.hazelcast.jet.cdc.CdcSinks;
import com.hazelcast.jet.cdc.ChangeRecord;
import com.hazelcast.jet.config.JobConfig;
import com.hazelcast.jet.pipeline.Pipeline;
import com.hazelcast.jet.pipeline.Sink;
import com.hazelcast.jet.pipeline.Sinks;
import com.hazelcast.jet.pipeline.StreamSource;
import lombok.extern.slf4j.Slf4j;
import org.learning.hazelcast.jet.dto.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.hazelcast.jet.cdc.mysql.MySqlCdcSources;

import java.util.Map;


@Component
@Slf4j
public class LocationCDCJetJob {

    private static final String DB_SERVER_NAME = "userdbcache";
    private static final String DB_SCHEMA = "userdb";
    private static final String DB_NAMESPACED_TABLE = DB_SCHEMA + ".location";

    @Autowired
    private JetInstance jet;

    public void submitMysqlCDCJob() {
        log.info(" submitMysqlCDCJob start");
        JobConfig jobConfig = new JobConfig().setName("mysql-monitor");
        jet.newJob(pipeline(), jobConfig);
        log.info(" submitMysqlCDCJob end");

    }

    private Pipeline pipeline() {
        Pipeline pipeline = Pipeline.create();
        StreamSource<ChangeRecord> source = cdc();
        FunctionEx<ChangeRecord, Location> toLocation = record -> record.value().toObject(Location.class);
        FunctionEx<Location, Map.Entry<Integer, Location>> toMapEntry = location -> Util.entry(location.getLocationId(), location);
        pipeline.readFrom(source)
                .withoutTimestamps()
                .peek()
                .map(toLocation.andThen(toMapEntry))
                .writeTo(cache());
        return pipeline;
    }

    private StreamSource<ChangeRecord> cdc() {
        return MySqlCdcSources.mysql("mysql-connector")
                .setDatabaseAddress("localhost")
                .setDatabasePort(Integer.parseInt("3306"))
                .setDatabaseUser("root")
                .setDatabasePassword("mysql")
                .setClusterName(DB_SERVER_NAME)
                .setDatabaseWhitelist(DB_SCHEMA)
                .setTableWhitelist(DB_NAMESPACED_TABLE)
                .build();
    }

    private Sink<Map.Entry<Integer, Location>> cache() {
        ClientConfig config = new ClientConfig();
        config.getNetworkConfig().addAddress("127.0.0.1:5701");

        return Sinks.remoteMap(
                "location_cdc_cache",
                config);
    }
}
