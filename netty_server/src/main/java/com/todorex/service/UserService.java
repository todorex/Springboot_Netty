package com.todorex.service;


/**
 * @Author rex
 * 2018/7/25
 */
public interface UserService {
    /**
     * 将表示一个User的字符串数组转化成User实体并存入数据库
     * @param userInfo
     * @return
     */
    int saveUser(String[] userInfo);
}

