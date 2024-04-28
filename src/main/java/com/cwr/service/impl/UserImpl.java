package com.cwr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cwr.mapper.UserMapper;
import com.cwr.pojo.UserInfo;
import com.cwr.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImpl extends ServiceImpl<UserMapper,UserInfo> implements UserService {

    private final UserMapper userMapper;

    public UserImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public void createUser(UserInfo userInfo) {
        userMapper.addUser(userInfo);
    }

    @Override
    public void updateUser(UserInfo userInfo) {
        userMapper.updateUser(userInfo);
    }

    @Override
    public void softDeleteUser(List<Integer> ids) {
        userMapper.markUserDeletedTime(ids);
    }

    @Override
    public UserInfo getUserById(Integer id) {
        return userMapper.selectById(id);
    }

}
