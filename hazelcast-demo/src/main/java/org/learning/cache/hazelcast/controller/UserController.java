package org.learning.cache.hazelcast.controller;

import org.learning.cache.hazelcast.entity.Users;
import org.learning.cache.hazelcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class
 */
@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value ="/{userId}")
    public Users getUserbyId(@PathVariable("userId") Integer userId) {
        return userService.getUserbyId(userId);
    }

    @DeleteMapping(value ="/{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
    }

    @PutMapping(value ="/{userId}")
    public Users updateUser(@PathVariable("userId") Integer userId, @RequestBody  Users users) {
        return userService.updateUser(userId,users);
    }
}
