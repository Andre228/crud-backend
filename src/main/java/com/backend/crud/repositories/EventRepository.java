package com.backend.crud.repositories;

import com.backend.crud.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Андрей on 12.12.2020.
 */
@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

    @Query(value = "SELECT * FROM event c WHERE c.post_id = :post_id", nativeQuery = true)
    List<Event> findAllByPost(@Param("post_id") Long id);
}
