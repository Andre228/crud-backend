package com.backend.crud.repositories;

import com.backend.crud.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Андрей on 28.11.2020.
 */
@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
}
