package com.backend.crud.repositories;

import com.backend.crud.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Андрей on 23.09.2020.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
    User findByName(String name);
    User findByEmailAndPassword(String email, String password);
    User findByNameAndPassword(String name, String password);
}
