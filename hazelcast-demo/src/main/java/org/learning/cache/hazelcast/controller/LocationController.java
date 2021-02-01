package org.learning.cache.hazelcast.controller;

import lombok.extern.slf4j.Slf4j;
import org.learning.cache.hazelcast.entity.Location;
import org.learning.cache.hazelcast.store.LocationStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/v1/locations")
@Slf4j
public class LocationController {

    @Autowired
    private LocationStore locationStore;




    @GetMapping("/store")
    public void store() {
        locationStore.store(new Random().nextInt(),location(new Random().nextInt()));
    }


    @GetMapping("/store-all")
    public void storeAll() {
        Map<Integer, Location> data = new HashMap<>();
        data.put(new Random().nextInt(), location(new Random().nextInt()));
        data.put(new Random().nextInt(), location(new Random().nextInt()));
        locationStore.storeAll(data);
    }

    @GetMapping("/load/{location-id}")
    public Location load(@PathVariable("location-id") Integer locationId) {

        return locationStore.load(locationId);
    }

    @GetMapping("/load-all-keys")
    public Iterable<Integer>  loadAllKeys() {
        return locationStore.loadAllKeys();
    }

    @GetMapping("/load-all")
    public Map<Integer, Location> loadAll() {
        Collection<Integer> keys = new ArrayList<>();
        keys.add(1);
        keys.add(2);

        return locationStore.loadAll(keys);
    }

    @DeleteMapping("/delete/{location-id}")
    public void  deletById(@PathVariable("location-id") Integer locationId) {
        locationStore.delete(locationId);
    }

    private Location location(Integer id) {
        return  Location.builder()
                .locationId(id)
                .accountId("accountid" +id)
                .currency("USD")
                .isActive(true)
                .name("nameOf"+id)
                .region("region"+id)
                .build();
    }

}


