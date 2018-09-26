package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * webflux使用mvc方式控制器
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * 流式返回所有数据
     * @return Flux包装的user对象
     */
    @ApiOperation(value = "查找所有",notes = "查找所有")
    @GetMapping(value = "/findAllStream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> findAllStream(){
        return userRepository.findAll().map(user->{
            try {
                log.info(user.toString());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return user;
        });
    }

    /**
     * JSON直接返回所有
     * APPLICATION_JSON_UTF8_VALUE == @ResponseBody
     * @return Flux包装的user对象
     */
    @ApiOperation(value = "JSON直接返回所有",notes = "JSON直接返回所有")
    @GetMapping(value = "/findAll",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Flux<User> findAll(){
        return userRepository.findAll();
    }

    /**
     * 查找所有返回带有http状态码
     * @return
     */
    @ApiOperation(value = "查找所有返回带有http状态码",notes = "查找所有返回带有http状态码")
    @GetMapping(value = "/findAllResp")
    public Mono<ResponseEntity<Flux<User>>> findAllResp(){
        return Mono.just(new ResponseEntity<Flux<User>>(userRepository.findAll(),HttpStatus.OK));
    }

    /**
     * 保存一个对象
      * @param user 规定JSON格式
     * @return
     */
    @ApiOperation(value = "保存一个对象",notes = "保存一个对象")
    @PostMapping("/")
    public Mono<User> save(@RequestBody User user){
        return userRepository.save(user);
    }

    /**
     * 根据主键更新
     * @param id
     * @param user
     * @return
     */
    @ApiOperation(value = "根据主键更新",notes = "根据主键更新")
    @PutMapping("/{id}")
    public Mono<ResponseEntity<User>> update(@PathVariable("id")String id, @RequestBody User user){
        return userRepository.findById(id).flatMap(u->{
            u.setName(user.getName());
            u.setAge(user.getAge());
            return userRepository.save(u);
        }).map(x->new ResponseEntity<>(x, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID删除",notes = "根据ID删除")
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable("id")String id){
        return userRepository.findById(id).flatMap(
                user->userRepository.delete(user).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                ).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
