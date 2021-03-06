package org.learning.hazelcast.jet.job;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.jet.JetInstance;
import com.hazelcast.jet.Util;
import com.hazelcast.jet.config.JobConfig;
import com.hazelcast.jet.pipeline.Pipeline;
import com.hazelcast.jet.pipeline.Sinks;
import com.hazelcast.jet.pipeline.Sources;
import com.hazelcast.map.IMap;
import com.hazelcast.query.Predicates;
import com.hazelcast.query.impl.predicates.SqlPredicate;
import org.learning.hazelcast.jet.dto.DateConverter;
import org.learning.hazelcast.jet.dto.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocationJDBCJetJob {

    private static final String MAP_NAME = "locationCache4";
    private static final String TABLE_NAME = "location";

    @Autowired
    private JetInstance jet;
    @Autowired
    private HazelcastInstance hazelcastInstance;

    private static Pipeline buildPipeline(String connectionUrl) {
        Pipeline p = Pipeline.create();

        ClientConfig config = new ClientConfig();
        config.getNetworkConfig().addAddress("127.0.0.1:5701");

        p.readFrom(Sources.jdbc(connectionUrl,
                "SELECT * FROM " + TABLE_NAME,
                resultSet -> buildLocation(resultSet)))
                .map(location -> Util.entry(location.getLocationId(), location))
                .writeTo(Sinks.remoteMap(MAP_NAME,config));
        return p;
    }

    public void go() {
        Pipeline p = buildPipeline("jdbc:mysql://localhost:3306/userdb?user=root&password=mysql");
        JobConfig j = new JobConfig()
                .addClass(LocationJDBCJetJob.class);

        jet.newJob(p,j).join();

    }

    private static Location buildLocation(ResultSet resultSet) throws SQLException {
        return Location.builder()
                .locationId(resultSet.getInt("location_id"))
                .accountId(resultSet.getString("account_id"))
                .name(resultSet.getString("name"))
                .currency(resultSet.getString("currency"))
                .region(resultSet.getString("region"))
                .isActive(resultSet.getBoolean("is_active"))
                .calendarDate(resultSet.getDate("calendarDate"))
                .localDate(DateConverter.sqlDateToLocalDate(resultSet.getDate("localDate")))
                .build();
    }

    public List<Location> locations(Integer id, String accountId, String currency) {
        IMap<Integer, Location> locationIMap = hazelcastInstance.getMap(MAP_NAME);


        if (id != null && accountId != null && currency != null) {
            String query = "l = " + id + " AND a =" + accountId + " AND c =" + currency;
            Collection<Location> result = locationIMap.values(new SqlPredicate(query));
            if (result != null) {
                return result.stream().collect(Collectors.toList());
            }
        }
        else if (accountId != null && currency != null) {
            String query = " a =" + accountId + " AND c =" + currency;
            Collection<Location> result = locationIMap.values(new SqlPredicate(query));
            if (result != null) {
                return result.stream().collect(Collectors.toList());
            }
        }
        else if (currency != null) {
            String q = "c =" + currency +" AND calendarDate >= 2021-02-28";
            Collection<Location> result = locationIMap.values(new SqlPredicate(q));
            if (result != null) {
                return   result.stream().collect(Collectors.toList());
            }
        }
        else if (id != null) {
            LocalDate from  = LocalDate.of(2021,02,28);
            LocalDate toDate = LocalDate.of(2021,05,12);
           String q = "is_active=1 AND localDate = "+from ;
            Collection<Location> result = locationIMap.values(Predicates.greaterEqual("localDate",from));
            if (result != null) {
                return   result.stream().collect(Collectors.toList());
            }
        }

        return new ArrayList<>();
    }
}
