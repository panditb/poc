package org.learning.hazelcast.jet.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Data")
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Data {

    private Integer id;
    private String dataVersion;
    private String name;
}
