package com.easytodo.service.impl;

import com.easytodo.entity.User;
import com.easytodo.mapper.UserMapper;
import com.easytodo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lheng
 * @since 2021-10-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
