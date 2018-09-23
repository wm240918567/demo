package com.example.demo.handlers;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 *user处理器
 */
@Component
public class UserHandler {

    @Autowired
    private UserRepository repository;

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(repository.findAll(), User.class);
    }

    public Mono<ServerResponse> save(ServerRequest request){
        //这里MediaType.TEXT_EVENT_STREAM设置的是返回值得格式
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(repository.saveAll(request.bodyToMono(User.class)),User.class);
    }

}
