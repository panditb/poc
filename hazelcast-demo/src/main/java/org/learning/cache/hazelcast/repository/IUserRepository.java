package org.learning.cache.hazelcast.repository;

import org.learning.cache.hazelcast.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for User entity
 */
@Repository
public interface IUserRepository extends CrudRepository<Users,Integer> {
}
