package com.invert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class MainController {

    private final PostRepository postRepository;

    @Autowired
    public MainController(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello there !";
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Post> getPost(@PathVariable(value = "id") Long id){
        Post post = postRepository.findOne(id);
        if (post != null) {
            return new ResponseEntity<>(post, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/post")
    public ResponseEntity<Post> addPost(@RequestBody Post post) {
        postRepository.save(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }


}
