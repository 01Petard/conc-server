package com.hzx.conc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzx.conc.mapper.UserMapper;
import com.hzx.conc.entity.User;
import com.hzx.conc.service.UserService;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author hzx
 * @since 2025-02-20 14:46:43
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

