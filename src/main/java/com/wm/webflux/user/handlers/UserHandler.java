package com.wm.webflux.user.handlers;

import com.wm.webflux.user.entity.User;
import com.wm.webflux.user.repository.UserRepository;
import com.wm.webflux.myframework.target.MyLog;
import com.wm.webflux.myframework.target.Statistical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * user处理器
 * 路由风格
 * @author wm
 * @since 2018/11/9 16:15
 * @version 1.0
 */
@Component
public class UserHandler {

    @Autowired
    private UserRepository repository;

    /**
     * 寻找所有
     * @param request 异步非阻塞请求
     * @return 异步非阻塞Mono<ServerResponse>
     */
    @MyLog(value = "==========================333333333333333333333333333333=========================")
    @Statistical(name = "findAll")
    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(repository.findAll(), User.class);
    }

    /**
     * 保存
     * @param request 异步非阻塞请求
     * @return 异步非阻塞Mono<ServerResponse>
     */
    public Mono<ServerResponse> save(ServerRequest request){
        //这里MediaType.TEXT_EVENT_STREAM设置的是返回值得格式
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(repository.saveAll(request.bodyToMono(User.class)),User.class);
    }

}
