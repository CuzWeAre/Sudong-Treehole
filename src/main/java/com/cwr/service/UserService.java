package com.cwr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cwr.pojo.UserInfo;

import java.util.List;

public interface UserService extends IService<UserInfo> {
    void createUser(UserInfo userInfo);

    UserInfo getUserById(Integer id);

    void updateUser(UserInfo userInfo);

    void softDeleteUser(List<Integer> ids);
}
