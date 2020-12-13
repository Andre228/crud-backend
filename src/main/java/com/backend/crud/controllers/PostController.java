package com.backend.crud.controllers;

import com.backend.crud.model.Category;
import com.backend.crud.model.Comment;
import com.backend.crud.model.Post;
import com.backend.crud.model.User;
import com.backend.crud.repositories.CategoryRepository;
import com.backend.crud.repositories.PostRepository;
import com.backend.crud.repositories.UserRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.*;
import java.lang.Object;


/**
 * Created by Андрей on 28.11.2020.
 */
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/posts/all")
    public List<Post> getAllPosts() {
        List<Post> posts = (List<Post>)postRepository.findAll();
        return posts;
    }

//    @GetMapping(value = "/get-comments/{id}")
//    public Set<Comment> getAllCommentsByPost(@PathVariable Long postId) {
//        Set<Comment> comments = postRepository.findById(postId).get().getComments();
//        return comments;
//    }

    @RequestMapping(value="/posts/get/{id}", method = RequestMethod.GET)
    public Post getPost(@PathVariable("id") Long id) {
        Post post = postRepository.findById(id).get();
        return post;
    }

    @DeleteMapping(value = "/posts/delete/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        Post post = postRepository.findById(id).get();
        try {
            postRepository.delete(post);
            deleteFile(post.getAlias());
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("It is impossible to delete this post because it contains some related info");
        }
    }

    @RequestMapping(value = "/posts/create", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<?> createPost(@RequestParam("title") String title,
                                        @RequestParam("description") String description,
                                        @RequestParam("slug") String slug,
                                        @RequestParam("excerpt") String excerpt,
                                        @RequestParam("is_published") Boolean is_published,
                                        @RequestParam("user") String userId,
                                        @RequestParam("category") String categoryId,
                                        @RequestParam("isFileUploading") String isFileUploading,
                                        @RequestParam("file") MultipartFile file) throws ParseException {

        try {
            Post post = new Post();

            post.setTitle(title);
            post.setDescription(description);
            post.setSlug(slug);
            post.setExcerpt(excerpt);
            post.setIs_published(is_published);

            boolean hasFile = Boolean.parseBoolean(isFileUploading);

            User user = userRepository.findById(Long.parseLong(userId)).get();
            if (user != null) {
                post.setUser(user);
            } else {
                return ResponseEntity.badRequest().body("This user is not exists");
            }

            Category category = categoryRepository.findById(Long.parseLong(categoryId)).get();
            if (category != null) {
                post.setCategory(category);
            } else {
                return ResponseEntity.badRequest().body("This category is not exists");
            }


            if (file != null && hasFile) {
                post = createFile(file, post);
            }

            Post createdPost = postRepository.save(post);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/posts/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePost(@RequestParam("id") String id,
                                        @RequestParam("title") String title,
                                        @RequestParam("description") String description,
                                        @RequestParam("slug") String slug,
                                        @RequestParam("excerpt") String excerpt,
                                        @RequestParam("alias") String alias,
                                        @RequestParam("is_published") Boolean is_published,
                                        @RequestParam("user") String userId,
                                        @RequestParam("category") String categoryId,
                                        @RequestParam("isUpdatingFile") String isUpdatingFile,
                                        @RequestParam("file") MultipartFile file) throws ParseException {

        try {

            boolean isNewFile = Boolean.parseBoolean(isUpdatingFile);

            Post post = postRepository.findById(Long.parseLong(id)).get();

            post.setTitle(title);
            post.setDescription(description);
            post.setSlug(slug);
            post.setExcerpt(excerpt);
            post.setIs_published(is_published);

            User user = userRepository.findById(Long.parseLong(userId)).get();
            if (user != null) {
                post.setUser(user);
            } else {
                return ResponseEntity.badRequest().body("This user is not exists");
            }

            Category category = categoryRepository.findById(Long.parseLong(categoryId)).get();
            if (category != null) {
                post.setCategory(category);
            } else {
                return ResponseEntity.badRequest().body("This category is not exists");
            }

            if (file != null && isNewFile) {

//                String deletingFileName = alias;
//
//                if (!deletingFileName.equals("")) {
//                    File fileToDelete = new File(uploadPath + "/" + deletingFileName);
//                    boolean success = fileToDelete.delete();
//                }

                boolean success = deleteFile(alias);

                post = createFile(file, post);

            }

            Post createdPost = postRepository.save(post);;

            return ResponseEntity.status(HttpStatus.OK).body(createdPost);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


//    @GetMapping(
//            value = "/get-image/{id}",
//            produces = MediaType.IMAGE_PNG_VALUE
//    )
//    public @ResponseBody byte[] getImage(@PathVariable("id") Long id) throws IOException {
//        Post post = postRepository.findById(id).get();
//        File serverFile = new File(uploadPath + "/" + post.getAlias());
//        System.out.println("serverFile : " + serverFile);
//        return Files.readAllBytes(serverFile.toPath());
//    }

//    @RequestMapping(value = "/image-response-entity/{id}", method = RequestMethod.GET)
//    public ResponseEntity<byte[]> getImageAsResponseEntity(@PathVariable("id") Long id) {
//        HttpHeaders headers = new HttpHeaders();
//        InputStream in = servletContext.getResourceAsStream("/WEB-INF/images/image-example.jpg");
//        byte[] media = IOUtils.toByteArray(in);
//        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
//
//        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
//        return responseEntity;
//    }

    @GetMapping(
            value = "/get-image/{id}",
            produces = MediaType.IMAGE_PNG_VALUE
    )
    public FileSystemResource getImageWithMediaType(@PathVariable("id") Long id) throws IOException {
        Post post = postRepository.findById(id).get();
        if (post.getAlias()!= null && !post.getAlias().equals("")) {
            File serverFile = new File(uploadPath + "/" + post.getAlias());
            return new FileSystemResource(serverFile.toPath());
        } else {
            File serverFile = new File(uploadPath + "/" + "Flag_of_None.png");
            return new FileSystemResource(serverFile.toPath());
        }

    }

    public Post createFile(MultipartFile file, Post post) throws IOException {

        if (file != null) {

            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            Date date = new Date();

            String uuidFile = UUID.randomUUID().toString();
            String fileName = uuidFile + "_" + post.getTitle() + "_" + date.getDate() + "." + date.getTime() + "_" + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + fileName));

            post.setAlias(fileName);
        }

        return post;

    }

    public boolean deleteFile(String alias) {
        String deletingFileName = alias;
        boolean isDeleted = false;

        if (!deletingFileName.equals("")) {
            File fileToDelete = new File(uploadPath + "/" + deletingFileName);
            isDeleted = fileToDelete.delete();
        }

        return isDeleted;
    }
}
