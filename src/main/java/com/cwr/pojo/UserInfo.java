package com.cwr.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cwr.util.UuidTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user_info", autoResultMap = true)
public class UserInfo {

    @TableId
    private Integer userId;
    private String username;
    private String passwordHash;
    private String email;
    private LocalDateTime registerTime;
    private Integer isBanned;
    private LocalDateTime lastLoginTime;
    private String lastLoginIp;
    private LocalDateTime deletedTime;
    @TableField(typeHandler = UuidTypeHandler.class, jdbcType = JdbcType.BINARY)
    private UUID avatarUuid;

}
