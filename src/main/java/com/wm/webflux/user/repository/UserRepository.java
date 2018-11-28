package com.wm.webflux.user.repository;

import com.wm.webflux.user.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * userDA0接口
 * @author wm
 * @since 2018/11/9 16:15
 * @version 1.0
 */

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

}
