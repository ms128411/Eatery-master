package net.togogo.domain;

import lombok.Data;

//使用@Data后可不用手动生成Getter和Setter，需要安装lombok插件
@Data
public class User {

    private int userID;//用户ID
    private String userName;//用户名
    private String password;//用户密码
    private int userType;//管理员级别

}

