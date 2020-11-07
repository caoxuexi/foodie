package com.caostudy.controller;

import com.caostudy.service.StuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author 曹学习
 * @description HelloController
 * @date 2020/11/1 10:22
 */
@Controller
public class BaseController {
    public static final Integer COMMENT_PAGE_SIZE=10;
    public static final Integer PAGE_SIZE=20;
}
