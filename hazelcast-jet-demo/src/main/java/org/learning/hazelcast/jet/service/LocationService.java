package org.learning.hazelcast.jet.service;

import org.learning.hazelcast.jet.job.LocationJDBCJetJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


// TODO IT should be schedule base for testing it is controller

@RestController
public class LocationService {

    @Autowired
    private LocationJDBCJetJob locationJDBCJetJob;

    @GetMapping("/submitCacheJob")
    public void submitJob() {
        locationJDBCJetJob.go();
    }
}
