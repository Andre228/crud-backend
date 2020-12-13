package com.backend.crud.repositories;

import com.backend.crud.model.Comment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Андрей on 02.12.2020.
 */
@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    Comment findByPost(Long id);

    @Query(value = "SELECT id,text,created_at,post_id,user_id FROM comment c WHERE c.post_id = :post_id", nativeQuery = true)
    List<Comment> findAllByPost(@Param("post_id") Long id);

    @Query(value = "delete from comment c where c.id in ?1", nativeQuery = true)
    void deleteCommentsWithIds(List<Long> ids);
}
