package org.learning.cache.hazelcast.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity table for User
 */
@Entity
@Table(name="location")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
