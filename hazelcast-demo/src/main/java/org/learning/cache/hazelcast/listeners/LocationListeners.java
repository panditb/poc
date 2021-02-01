package org.learning.cache.hazelcast.listeners;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.map.listener.EntryAddedListener;
import com.hazelcast.map.listener.EntryLoadedListener;
import com.hazelcast.map.listener.EntryRemovedListener;
import com.hazelcast.map.listener.EntryUpdatedListener;
import lombok.extern.slf4j.Slf4j;
import org.learning.cache.hazelcast.entity.Location;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LocationListeners implements EntryAddedListener<Integer, Location> , EntryUpdatedListener<Integer, Location>
        , EntryRemovedListener<Integer,Location>, EntryLoadedListener<Integer, Location> {


    @Override
    public void entryAdded(EntryEvent<Integer, Location> event) {
        log.info(" entry added  key {}" , event.getKey());


    }

    @Override
    public void entryRemoved(EntryEvent<Integer, Location> event) {
        log.info(" entry removed  key {}" , event.getKey());

    }

    @Override
    public void entryUpdated(EntryEvent<Integer, Location> event) {
        log.info(" entry updated  key {}" , event.getKey());

    }

    @Override
    public void entryLoaded(EntryEvent<Integer, Location> event) {
        log.info(" entry loaded  key {}" , event.getKey());

    }
}
