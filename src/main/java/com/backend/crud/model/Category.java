package com.backend.crud.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Андрей on 21.10.2020.
 */
@Entity
@Table(name= "\"category\"")
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private Long id;
    @Column
    private String title;
    @Column
    private String slug;
    @Column
    private String description;
    @Column
    private Long parentId;
    @Column
    private String createdAt;
    @Column
    private String updatedAt;
    @Column
    private String deletedAt;
//    @OneToMany(mappedBy = "category", fetch=FetchType.LAZY)
//    private Set<Post> posts = new HashSet<>();
//
//    public Set<Post> getPosts() {
//        return posts;
//    }
//
//    public void setPosts(Set<Post> posts) {
//        this.posts = posts;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }


}
