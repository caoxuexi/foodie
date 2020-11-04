package com.caostudy.controller;

import com.caostudy.pojo.Users;
import com.caostudy.pojo.bo.UserBO;
import com.caostudy.service.UserService;
import com.caostudy.utils.CaoJSONResult;
import com.caostudy.utils.CookieUtils;
import com.caostudy.utils.JsonUtils;
import com.caostudy.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 曹学习
 * @description HelloController
 * @date 2020/11/1 10:22
 */
@Api(value = "注册登录", tags = {"用于注册登录的相关接口"})
@RestController
@RequestMapping("passport")
public class PassportController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")
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

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/regist")
    public CaoJSONResult regist(@RequestBody UserBO userBO
            , HttpServletRequest request
            , HttpServletResponse response) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPwd = userBO.getConfirmPassword();

        //0.判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password) ||
                StringUtils.isBlank(confirmPwd)) {
            return CaoJSONResult.errorMsg("用户名或密码不能为空");
        }
        //1.查询用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return CaoJSONResult.errorMsg("用户名已经存在");
        }
        //2.密码长度不得少于6位
        if (password.length() < 6) {
            return CaoJSONResult.errorMsg("密码长度不能少于6位");
        }
        //3.判断两次密码是否一致
        if (!password.equals(confirmPwd)) {
            return CaoJSONResult.errorMsg("两次密码输入不一致");
        }
        //4.实现注册
        Users userResult = userService.createUser(userBO);

        //5.设置Cookie
        setNullProperty(userResult);

        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(userResult), true);

        return CaoJSONResult.ok();
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public CaoJSONResult login(@RequestBody UserBO userBO
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
        String username = userBO.getUsername();
        String password = userBO.getPassword();

        //0.判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password)) {
            return CaoJSONResult.errorMsg("用户名或密码不能为空");
        }

        //1.实现登录
        Users userResult = userService.queryUserForLogin(username,
                MD5Utils.getMD5Str(password));
        if (userResult == null) {
            return CaoJSONResult.errorMsg("用户名或密码错误");
        }
        setNullProperty(userResult);

        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(userResult), true);
        return CaoJSONResult.ok(userResult);
    }

    @ApiOperation(value = "用户退出登录", notes = "用户退出登录", httpMethod = "POST")
    @PostMapping("/logout")
    public CaoJSONResult login(@RequestParam String userId,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        // 清除用户的相关cookie
        CookieUtils.deleteCookie(request,response,"user");
        //TODO 用户退出登录需要清空购物车
        //TODO 分布式会话中需要清楚用户数据
        return CaoJSONResult.ok();
    }

    private void setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
    }
}
