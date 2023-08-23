package com.wlm.exam.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author wlm
 * @date 2023/8/15 - 16:31
 */
@Entity
@Data
public class User {

    @Id
    private String userId;

    private String nickname;

    private String username;

    private String password;

    private Integer userType;
}