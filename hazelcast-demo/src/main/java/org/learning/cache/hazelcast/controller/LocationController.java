package org.learning.cache.hazelcast.controller;

import org.learning.cache.hazelcast.entity.Location;
import org.learning.cache.hazelcast.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/locations")
public class LocationController {

   @Autowired
   private LocationService service;

    @GetMapping("/load-all")
    public String loadAllDataInCache() {
        service.loadAllDataInCache();
        return "Loaded data";

    }

    @GetMapping("/cache")
    public List<Location> locations(@RequestParam(value = "locationId",required = false) Integer id, @RequestParam(value = "accountId",required = false) String accountId, @RequestParam(value = "currency",required = false) String currency) {
        return service.locations(id,accountId,currency);
    }
}


