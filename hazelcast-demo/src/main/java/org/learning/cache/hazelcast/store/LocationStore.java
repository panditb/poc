package org.learning.cache.hazelcast.store;

import com.hazelcast.map.MapStore;
import org.learning.cache.hazelcast.entity.Location;
import org.learning.cache.hazelcast.repository.ILocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class LocationStore implements MapStore<Integer, Location> {


    @Autowired
    private ILocationRepository repository;


    @Override
    public void store(Integer key, Location value) {
        repository.save(value);

    }

    @Override
    public void storeAll(Map<Integer, Location> data) {
        repository.saveAll(data.values());
    }

    @Override
    public void delete(Integer key) {
        repository.deleteById(key);

    }

    @Override
    public void deleteAll(Collection<Integer> keys) {
        //todo

    }

    @Override
    public Location load(Integer key) {
        return repository.findById(key).orElse(null);
    }

    @Override
    public Map<Integer, Location> loadAll(Collection<Integer> keys) {
        return StreamSupport.stream(repository.findAllById(keys).spliterator(),false)
                .collect(Collectors.toMap(Location::getLocationId, Function.identity()));
    }

    @Override
    public Iterable<Integer> loadAllKeys() {
        return StreamSupport.stream(repository.findAll().spliterator(),false)
                .map(Location::getLocationId)
                .collect(Collectors.toList());
    }
}
