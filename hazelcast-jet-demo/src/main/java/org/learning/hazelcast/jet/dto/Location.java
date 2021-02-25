package org.learning.hazelcast.jet.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location implements Serializable  {

    private static final long serialVersionUID=1L;

    private Integer locationId;

    private String name;

    private String accountId;
    private String currency;

    private String region;
    private boolean isActive;
}
