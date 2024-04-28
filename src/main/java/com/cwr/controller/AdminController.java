package com.cwr.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "管理员接口", description = "进行高级管理的接口")
@RestController()
@RequestMapping("/admin")
public class AdminController {

}
