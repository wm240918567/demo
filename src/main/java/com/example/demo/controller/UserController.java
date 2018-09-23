package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public Flux<User> findAll(){
        return userRepository.findAll();
    }

    @PostMapping("/")
    public Mono<User> save(@RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<User>> update(@PathVariable("id")String id, @RequestBody User user){
        return userRepository.findById(id).flatMap(u->{
            u.setName(user.getName());
            u.setAge(user.getAge());
            return userRepository.save(u);
        }).map(x->new ResponseEntity<>(x, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable("id")String id){
        return userRepository.findById(id).flatMap(
                user->userRepository.delete(user).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                ).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
