package com.qingchen.shiro.studyshiro.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Created by QingChen on 2019/11/14 16:25
 */
@Data
public class Role implements Serializable {

    private int id;
    private String role;
    private String desc;

}
