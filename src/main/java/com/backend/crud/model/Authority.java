package com.backend.crud.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by Андрей on 10.10.2020.
 */
@Entity
@Table
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private Long id;

    @Column
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
