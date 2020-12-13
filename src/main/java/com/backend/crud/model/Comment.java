package com.backend.crud.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Андрей on 02.12.2020.
 */
@Entity
@Table(name= "\"comment\"")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    @Column
    private String text;
    @Column
    private Date created_at;

    @OneToOne()
    @JoinColumn(name = "post_id", nullable = false, referencedColumnName = "id")
    private Post post;

    @OneToOne()
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
