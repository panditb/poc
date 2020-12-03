package org.learning.cache.hazelcast.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity table for User
 */
@Entity
@Table(name="users")
@Data
public class Users  implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer userId;

    @Column(name="username",nullable=false)
    private String username;

    @Column(name="email",nullable=false)
    private String email;

}
