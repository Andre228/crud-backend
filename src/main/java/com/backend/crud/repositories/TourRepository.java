package com.backend.crud.repositories;

import com.backend.crud.model.Tour;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Андрей on 14.12.2020.
 */
@Repository
public interface TourRepository extends CrudRepository<Tour, Long> {

    @Query(value = "SELECT * FROM tour t WHERE t.category_id = :category_id", nativeQuery = true)
    List<Tour> findAllByCategory(@Param("category_id") Long id);
}
