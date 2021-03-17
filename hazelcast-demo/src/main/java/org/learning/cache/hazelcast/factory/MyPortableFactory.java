package org.learning.cache.hazelcast.factory;

import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableFactory;
import org.learning.cache.hazelcast.entity.Location;

public class MyPortableFactory implements PortableFactory {

    @Override
    public Portable create(int classId ) {
        if ( Location.ID == classId )
            return new Location();
        else
            return null;
    }
}
