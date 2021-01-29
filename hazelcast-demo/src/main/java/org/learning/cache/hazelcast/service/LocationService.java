package org.learning.cache.hazelcast.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.SqlPredicate;
import org.learning.cache.hazelcast.entity.Location;
import org.learning.cache.hazelcast.repository.ILocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    @Autowired
    private ILocationRepository locationRepository;

    @Autowired
    private HazelcastInstance hazelcastInstance;

    public void loadAllDataInCache() {
        IMap<Integer, Location> locationIMap = hazelcastInstance.getMap("location");

        Iterable<Location> locations = locationRepository.findAll();
        locations.forEach(l -> {
            locationIMap.put(l.getLocationId(), l);
        });
    }

    public List<Location> locations(Integer id, String accountId, String currency) {
        IMap<Integer, Location> locationIMap = hazelcastInstance.getMap("location");
        if (id != null && accountId != null && currency != null) {
            String query = "locationId = " + id + " AND accountId =" + accountId + " AND currency =" + currency;
            Collection<Location> result = locationIMap.values(new SqlPredicate(query));
            if (result != null) {
                return result.stream().collect(Collectors.toList());
            }
        }
        else if (currency != null) {
            Collection<Location> result = locationIMap.values(new SqlPredicate("currency =" + currency));
            if (result != null) {
              return   result.stream().collect(Collectors.toList());
            }
        }
        else if (id != null) {
            Collection<Location> result = locationIMap.values(new SqlPredicate("locationId = " + id));
            if (result != null) {
                return   result.stream().collect(Collectors.toList());
            }
        }
        return new ArrayList<>();
    }
}
