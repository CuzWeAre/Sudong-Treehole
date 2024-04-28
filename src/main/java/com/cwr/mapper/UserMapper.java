package com.cwr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cwr.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<UserInfo> {
    void addUser(UserInfo userInfo);

    UserInfo getUserById(Integer id);

    void updateUser(UserInfo userInfo);

    void markUserDeletedTime(List<Integer> ids);
}
