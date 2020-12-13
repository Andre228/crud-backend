package com.backend.crud.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Андрей on 21.10.2020.
 */
@Entity
@Table(name= "\"post\"")
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private Long id;

    @Column
    private String slug;
    @Column
    private String alias;
    @Column
    private String description;
    @Column
    private String title;
    @Column
    private String excerpt;
    @Column
    private boolean is_published;
    @Column
    private Date publiched_at;
    @Column
    private Date created_at;
    @Column
    private Date updated_at;
    @Column
    private Date deleted_at;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Category.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

//    @OneToMany(mappedBy = "post")
//    private Set<Event> event;
//
    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;
//
//    public Set<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(Set<Comment> comments) {
//        this.comments = comments;
//    }
//
//    public Set<Event> getEvent() {
//        return event;
//    }
//
//    public void setEvent(Set<Event> event) {
//        this.event = event;
//    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public boolean isIs_published() {
        return is_published;
    }

    public void setIs_published(boolean is_published) {
        this.is_published = is_published;
    }

    public Date getPubliched_at() {
        return publiched_at;
    }

    public void setPubliched_at(Date publiched_at) {
        this.publiched_at = publiched_at;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }


}
