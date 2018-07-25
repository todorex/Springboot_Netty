package com.todorex.service.impl;

import com.todorex.dao.UserMapper;
import com.todorex.entity.User;
import com.todorex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author rex
 * 2018/7/25
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public int saveUser(String[] userInfo) {
        User user = new User();
        user.setName(userInfo[0]);
        user.setAge(Integer.valueOf(userInfo[1]));
        userMapper.insertSelective(user);
        return user.getId();
    }
}
