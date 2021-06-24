package org.learning.cache.hazelcast.service;

import org.learning.cache.hazelcast.entity.DIFResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WebclientXML {

    public DIFResult getXMLResult() {
        return  getWebClient()
                .get()
                .uri("/xml-resp/dif")
                .retrieve()
                .bodyToMono(DIFResult.class)
                .block();
    }

    private WebClient getWebClient() {
         return  WebClient.builder()
                .baseUrl("http://localhost:8087")
                .build();
    }

}
