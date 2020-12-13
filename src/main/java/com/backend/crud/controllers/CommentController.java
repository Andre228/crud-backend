package com.backend.crud.controllers;

import com.backend.crud.model.Comment;
import com.backend.crud.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Андрей on 02.12.2020.
 */
@RestController
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(value = "/comments/all/{id}")
    public List<Comment> getAllCommentsByPost(@PathVariable("id") Long postId) {
        List<Comment> comments = commentRepository.findAllByPost(postId);
        return comments;
    }

    @PostMapping(value = "/comments/create")
    public Comment createCommentByPost(@RequestBody Comment comment) {
        Comment creatingComment = commentRepository.save(comment);
        return creatingComment;
    }

    @DeleteMapping(value = "/comments/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        Comment deletingComment = commentRepository.findById(id).get();
        try {
            commentRepository.delete(deletingComment);
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error");
        }
    }

    @PostMapping(value = "/comments/delete/selected")
    public ResponseEntity<?> deleteSelectedComments(@RequestBody List<? extends Comment> compositIdList) {
        try {
            commentRepository.deleteAll(compositIdList);
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Deleting error");
        }
    }


}
