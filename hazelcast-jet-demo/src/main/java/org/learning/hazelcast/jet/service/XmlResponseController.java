package org.learning.hazelcast.jet.service;


import org.learning.hazelcast.jet.dto.DIFResult;
import org.learning.hazelcast.jet.dto.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/xml-resp")
public class XmlResponseController {


    @GetMapping("/dif")
    public DIFResult getDif() {
        DIFResult result = new DIFResult();
        Data data = new Data(1, "1.0.0", "data-1");
        Data data1 = new Data(2, "1.0.0", "data-1");
        Data data2 = new Data(3, "1.0.0", "data-1");
        List<Data> dataList = new ArrayList<>();
        dataList.add(data);
        dataList.add(data1);
        dataList.add(data2);
        result.setDataLisst(dataList);
        return result;
    }

}
