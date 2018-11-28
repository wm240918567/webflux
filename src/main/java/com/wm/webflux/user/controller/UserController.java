package com.wm.webflux.user.controller;

import com.wm.webflux.user.entity.User;
import com.wm.webflux.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

/**
 * user控制器
 * webflux使用mvc方式
 * @author wm
 * @since 2018/11/9 16:15
 * @version 1.0
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test")
    public Mono<String> test(){
        return Mono.just("test wm");
    }

    /**
     * 流式返回所有数据
     * 为了效果，每次线程停1秒
     * @return Flux<User>
     */
    @GetMapping(value = "/findAllStream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> findAllStream(){
        return userRepository.findAll().map(user->{
            try {
                log.info(user.toString());
                //单位秒
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
     * @return  Flux<User>
     */
    @GetMapping(value = "/findAll",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Flux<User> findAll(){
        return userRepository.findAll();
    }

    /**
     * 查找所有返回带有http状态码
     * @return Mono<ResponseEntity<Flux<User>>>
     */
    @GetMapping(value = "/findAllResp")
    public Mono<ResponseEntity<Flux<User>>> findAllResp(){
        return Mono.just(new ResponseEntity<Flux<User>>(userRepository.findAll(),HttpStatus.OK));
    }

    /**
     * 保存一个对象
     * @param user SON格式user数据
     * @return Mono<User>
     */
    @PostMapping("/")
    public Mono<User> save(@RequestBody User user){
        return userRepository.save(user);
    }

    /**
     * 根据主键更新
     * @param id rest；url中的{id}
     * @param user JSON格式的user数据
     * @return Mono<ResponseEntity<User>>
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<User>> update(@PathVariable("id")String id, @RequestBody User user){
        return userRepository.findById(id).flatMap(u->{
            u.setName(user.getName());
            u.setAge(user.getAge());
            u.setBirthday(user.getBirthday());
            return userRepository.save(u);
        }).map(x->new ResponseEntity<>(x, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 根据ID删除
     * @param id rest；url中的{id}
     * @return Mono<ResponseEntity<Void>>
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable("id")String id){
        return userRepository.findById(id).flatMap(
                user->userRepository.delete(user).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
        ).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
