package com.cwr.controller;

import com.cwr.pojo.ApiResponse;
import com.cwr.pojo.UserInfo;
import com.cwr.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "用户管理", description = "添加、查询、删除、修改用户")
@RestController()
@RequestMapping("/user")
public class UserContoller {

    private final UserService userService;

    public UserContoller(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "创建用户")
    @PostMapping()
    public ApiResponse<Object> createUser(@RequestBody UserInfo userInfo) {
        userService.createUser(userInfo);
        return ApiResponse.success();
    }

    @Operation(summary = "批量软删除用户")
    @DeleteMapping("/{ids}")
    public ApiResponse<Object> softDeleteUser(@PathVariable("ids") List<Integer> ids) {
        userService.softDeleteUser(ids);
        return ApiResponse.success();
    }

    @Operation(summary = "根据ID查询用户")
    @GetMapping("/{id}")
    public ApiResponse<UserInfo> getUserById(@PathVariable Integer id) {

        UserInfo userInfo = userService.getUserById(id);
        return ApiResponse.success(userInfo);
    }

    @Operation(summary = "修改用户")
    @PutMapping()
    public ApiResponse<Object> modifyUser(@RequestBody UserInfo userInfo) {
        userService.updateUser(userInfo);
        return ApiResponse.success();
    }

}
