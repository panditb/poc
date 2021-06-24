package org.learning.hazelcast.jet.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "DIFResult")
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class DIFResult {


    private List<Data> dataLisst;

}
