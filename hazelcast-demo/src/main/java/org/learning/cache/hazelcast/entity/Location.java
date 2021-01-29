package org.learning.cache.hazelcast.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Entity table for User
 */
@Entity
@Table(name="location")
@Data
public class Location implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="location_id")
    private Integer locationId;

    private String name;

    private String accountId;
    private String currency;

    private String region;
    private boolean isActive;

}
