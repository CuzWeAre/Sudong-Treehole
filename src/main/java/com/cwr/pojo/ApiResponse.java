package com.cwr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private Integer status_code; //响应码
    private String message; //返回的信息
    private T data; //返回的数据

    //增删改 成功响应
    public static ApiResponse<Object> success() {
        return new ApiResponse<>(1, "success", null);
    }

    //查 成功相应
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(1, "success", data);
    }

    //失败响应
    public static ApiResponse<Object> error() {
        return new ApiResponse<>(0, "error", null);
    }

    public static ApiResponse<Object> error(String msg) {
        return new ApiResponse<>(0, msg, null);
    }
}
