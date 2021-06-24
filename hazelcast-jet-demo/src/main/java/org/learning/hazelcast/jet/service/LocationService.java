package org.learning.hazelcast.jet.service;

import org.learning.hazelcast.jet.dto.Location;
import org.learning.hazelcast.jet.job.LocationCDCDebJetJob;
import org.learning.hazelcast.jet.job.LocationCDCJetJob;
import org.learning.hazelcast.jet.job.LocationJDBCJetJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


// TODO IT should be schedule base for testing it is controller

@RestController
public class LocationService {

    @Autowired
    private LocationJDBCJetJob locationJDBCJetJob;

    @Autowired
    private LocationCDCJetJob cdcJetJob;

    @Autowired
    private LocationCDCDebJetJob cdcDebJetJob;


    @GetMapping("/submitCacheJob")
    public void submitJob() {
        locationJDBCJetJob.go();
    }

    @GetMapping("/cdc")
    public void submitCdcJob() {
        cdcJetJob.submitMysqlCDCJob();

    }

    @GetMapping("/cdcdeb")
    public void submitCdcDebJob() {
        cdcDebJetJob.mysqlCdcJob();
    }

    @GetMapping("/cache")
    public List<Location> locations(@RequestParam(value = "locationId",required = false) Integer id, @RequestParam(value = "accountId",required = false) String accountId, @RequestParam(value = "currency",required = false) String currency) {
        return locationJDBCJetJob.locations(id,accountId,currency);
    }


}
