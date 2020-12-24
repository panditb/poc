package org.learning.cache.hazelcast.service;

import org.learning.cache.hazelcast.entity.Users;
import org.learning.cache.hazelcast.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Service layer for User entity
 */
@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    @Cacheable(value = "userCache", key = "#userId", unless = "#result==null")
    public Users getUserbyId(Integer userId) {
        try {
            //JUST FOR TESTING
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userRepository.findById(userId).get();
    }

    @CacheEvict(value = "userCache", key = "#userId")
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    @CachePut(value = "userCache", key = "#userId")
    public Users updateUser(Integer userId, Users users) {
        Users existingUser = userRepository.findById(userId).get();
        if (existingUser != null) {
//            existingUser.setEmail(users.getEmail());
//            existingUser.setUsername(users.getUsername());
            userRepository.save(existingUser);
        }
        return existingUser;

    }
}
