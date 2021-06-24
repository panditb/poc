package org.learning.cache.hazelcast.controller;


import org.learning.cache.hazelcast.entity.DIFResult;
import org.learning.cache.hazelcast.service.WebclientXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("xml-resp")
public class XMLWebResponse {

    @Autowired
    private WebclientXML webclientXML;

    @GetMapping
    public DIFResult getDIF() {
        return  webclientXML.getXMLResult();
    }
}
