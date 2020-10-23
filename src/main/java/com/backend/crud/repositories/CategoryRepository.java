package com.backend.crud.repositories;

import com.backend.crud.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Андрей on 21.10.2020.
 */
@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Category findBySlug(String slug);
}
