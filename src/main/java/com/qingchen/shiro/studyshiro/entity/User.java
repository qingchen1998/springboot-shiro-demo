package com.qingchen.shiro.studyshiro.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Created by QingChen on 2019/11/14 16:24
 */
@Data
public class User implements Serializable {

    private int id;
    private String account;
    private String password;
    private String username;
    private int status;
    private int permissionVersion;

}
