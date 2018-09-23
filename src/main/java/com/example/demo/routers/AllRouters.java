package com.example.demo.routers;

import com.example.demo.handlers.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * 路由器
 */
@Configuration
public class AllRouters {

    /**
     * user路由器
     * @param handler 处理器
     * @return 路由器
     */
    @Bean
    public RouterFunction<ServerResponse> userRouter(UserHandler handler){

        return RouterFunctions.nest(RequestPredicates.path("/routerUser"),
                RouterFunctions.route(RequestPredicates.GET("/"),handler::findAll)
        .andRoute(RequestPredicates.POST("/").
                and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)),handler::save));

    }
}
