package com.easytodo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easytodo.common.dto.LoginDto;
import com.easytodo.common.dto.UpdatePasswordDto;
import com.easytodo.common.lang.Result;
import com.easytodo.entity.User;
import com.easytodo.service.UserService;
import com.easytodo.util.MD5Util;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenshuwen
 * @since 2021-10-20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired

    @GetMapping("/index")
    public Object getById(){
        return userService.getById(1);
    }

    @GetMapping("/login")
    public Result login(@Validated LoginDto loginDto){

        User user = userService.getOne(new QueryWrapper<User>().eq("username",loginDto.getUsername()));

        if(user == null){
            return Result.fail(1,"用户不存在",null);
        }
        if(!user.getPassword().equals(MD5Util.MD5EncodeUtf8(loginDto.getPassword()))){
            return Result.fail(1,"密码不正确",null);
        }
        return Result.succ(0,"登录成功",user);
    }

    @PostMapping("/register")
    public Result register(User registerUser){
        User user = userService.getOne(new QueryWrapper<User>().eq("username",registerUser.getUsername()));

        if(user != null){
            return Result.fail("用户名已存在");
        }
        else{
            registerUser.setId(userService.count()+1);
            registerUser.setPassword(MD5Util.MD5EncodeUtf8(registerUser.getPassword()));
            userService.save(registerUser);
            return Result.succ(0,"注册成功",null);
        }
    }

    @PutMapping("/updatePassword")
    public Result updatePassword(@Validated UpdatePasswordDto updatePasswordDto){

        User user = userService.getOne(new QueryWrapper<User>().eq("username",updatePasswordDto.getUsername()));
        if(user == null){
            return Result.fail(1,"用户不存在",null);
        }
        if(!user.getPassword().equals(MD5Util.MD5EncodeUtf8(updatePasswordDto.getOldPassword()))){
            return Result.fail(1,"密码不正确",null);
        }

        user.setPassword(MD5Util.MD5EncodeUtf8(updatePasswordDto.getNewPassword()));
        userService.updateById(user);
        return Result.succ(0,"密码修改成功成功",null);
    }
}
