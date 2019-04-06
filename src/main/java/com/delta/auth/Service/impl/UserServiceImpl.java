/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: UserServiceImpl
 * Author:   anywhere
 * Date:     4/6 0006 14:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.delta.auth.Service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.delta.auth.Service.UserService;
import com.delta.auth.dao.UserMapper;
import com.delta.auth.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author anywhere
 * @create 4/6 0006
 * @since 1.0.0
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    public UserServiceImpl(UserMapper userDao) {
        super(userDao);
    }

    @Override
    protected EntityWrapper<User> getDeleteAndUpdateWrapper(User user) {
        return null;
    }

    @Override
    public int listCount() {
        return dao.listCount();
    }
}