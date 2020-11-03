package com.caostudy.controller;

import com.caostudy.pojo.bo.UserBO;
import com.caostudy.service.UserService;
import com.caostudy.utils.CaoJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 曹学习
 * @description HelloController
 * @date 2020/11/1 10:22
 */
@RestController
@RequestMapping("passport")
public class PassportController {
    @Autowired
    private UserService userService;

    @GetMapping("/usernameIsExist")
    public CaoJSONResult usernameIsExist(@RequestParam String username) {
        //1.判断用户名不能为空
        if (StringUtils.isBlank(username)) {
            return CaoJSONResult.errorMsg("用户名不能为空");
        }
        //2.查找注册的用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return CaoJSONResult.errorMsg("用户名已经存在");
        }
        //3.请求成功，用户名没有重复
        return CaoJSONResult.ok();
    }

    @PostMapping("/regist")
    public CaoJSONResult regist(@RequestBody UserBO userBO) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPwd = userBO.getConfirmPassword();

        //0.判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password) ||
                StringUtils.isBlank(confirmPwd)){
            return CaoJSONResult.errorMsg("用户名或密码不能为空");
        }
        //1.查询用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return CaoJSONResult.errorMsg("用户名已经存在");
        }
        //2.密码长度不得少于6位
        if(password.length()<6){
            return CaoJSONResult.errorMsg("密码长度不能少于6位");
        }
        //3.判断两次密码是否一致
        if(!password.equals(confirmPwd)){
            return CaoJSONResult.errorMsg("两次密码输入不一致");
        }
        //4.实现注册
        userService.createUser(userBO);
        return CaoJSONResult.ok();
    }
}
