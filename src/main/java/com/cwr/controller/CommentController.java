package com.cwr.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "回帖接口", description = "回复帖子")
@RestController
@RequestMapping("/comment")
public class CommentController {
}
