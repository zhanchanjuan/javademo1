package com.javademo;

import com.javademo.entity.system.AppUser;
import com.javademo.entity.system.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author shuyi
 * @date 2020/5/27
 */
@Slf4j
public class Java8Test {


    @Test
    public void givenEmptyValue_whenCompare_thenOk() {
        User user = null;
        log.debug("Using orElse");
        User result = Optional.ofNullable(user).orElse(createNewUser());
        log.debug("Using orElseGet");
        User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());
        System.out.println("orElse结果："+result);
        System.out.println("orElseGet结果："+result2);
    }

    private User createNewUser() {
        log.debug("Creating New User");
        return new User("extra@gmail.com", "1234");
    }

    @Test
    public void whenMap_thenOk() {
        User user = new User("anna@gmail.com", "1234");
        String email = Optional.ofNullable(user)
                .map(u -> u.getEmail()).orElse("default@gmail.com");
        assertEquals(email, user.getEmail());
        System.out.println("email的值是："+email);
    }

    @Test
    public void whenFlatMap_thenOk() {
        User user = new User("anna@gmail.com", "1234");
        user.setPosition("Developer");
        String position = Optional.ofNullable(user)
                .flatMap(u -> u.getPosition()).orElse("default");
        assertEquals(position, user.getPosition().get());
    }

    //过滤值
    @Test
    public void whenFilter_thenOk() {
        User user = new User("anna@gmail.com", "1234");
        Optional<User> result = Optional.ofNullable(user)
                .filter(u -> u.getEmail() != null && u.getEmail().contains("@"));

        assertTrue(result.isPresent());
    }

}
