package com.javademo.entity.system;

import lombok.Data;


import java.io.Serializable;
import java.util.Optional;

/**
 * @author shuyi
 * @date 2020/5/27
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 5642714689230399982L;

    private String email;

    private String tel;

    private String position;

    public Optional<String> getPosition() {
        return Optional.ofNullable(position);
    }

    public User(String email,String tel){
        this.email=email;
        this.tel=tel;
    }


}
