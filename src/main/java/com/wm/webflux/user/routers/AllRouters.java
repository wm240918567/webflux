package com.wm.webflux.user.routers;

import com.wm.webflux.user.handlers.UserHandler;
import com.wm.webflux.myframework.target.MyLog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * 路由器配置
 * 每个路由配置为一个bean
 * webflux方法
 * @author wm
 * @since 2018/11/9 16:15
 * @version 1.0
 */
@Configuration
public class AllRouters {

    /**
     * user路由器
     * @param handler 处理器
     * @return 路由器
     */
    @MyLog(value = "==========================22222222222222222222222222222=========================")
    @Bean
    public RouterFunction<ServerResponse> userRouter(UserHandler handler) {

        return RouterFunctions.nest(RequestPredicates.path("/routerUser"),
                RouterFunctions
                        .route(RequestPredicates.GET("/"),
                                handler::findAll)
                        .andRoute(RequestPredicates.POST("/").
                                and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)), handler::save));
    }
}




















