package org.learning.cache.hazelcast.repository;

import org.learning.cache.hazelcast.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILocationRepository  extends CrudRepository<Location,Integer> {
}
