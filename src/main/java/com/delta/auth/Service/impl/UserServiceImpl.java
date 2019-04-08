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

import com.delta.auth.Service.UserService;
import com.delta.auth.dao.UserMapper;
import com.delta.auth.dto.TweiUser;
import com.delta.common.code.LoginCode;
import com.delta.common.utils.EncryptUtil;
import com.delta.common.utils.ServerResponse;
import com.delta.common.utils.StringUtil;
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
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, TweiUser> implements UserService {

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        super(userMapper);
    }

    @Override
    public ServerResponse<TweiUser> login(TweiUser user) {
        ServerResponse<TweiUser> res = ServerResponse.createBySuccess();
        if (StringUtil.isBlank(user.getUserid())) {
            res = ServerResponse.createByErrorCodeMessage(LoginCode.USERID_IS_NULL.getCode(),
                    LoginCode.USERID_IS_NULL.getDesc());
            return res;
        }
        if (StringUtil.isBlank(user.getPassword())) {
            res = ServerResponse.createByErrorCodeMessage(LoginCode.PASSWORD_IS_NULL.getCode(),
                    LoginCode.PASSWORD_IS_NULL.getDesc());
            return res;
        }
        TweiUser dbUser = (TweiUser) dao.selectById(user);
        if (null == dbUser) {
            res = ServerResponse.createByErrorCodeMessage(LoginCode.USER_NOT_EXSISTE.getCode(),
                    LoginCode.USER_NOT_EXSISTE.getDesc());
            return res;
        }
        if (!EncryptUtil.getInstance().MD5(user.getPassword()).equals(dbUser.getPassword())) {
            res = ServerResponse.createByErrorCodeMessage(LoginCode.PASSWORD_UNCURRECT.getCode(),
                    LoginCode.PASSWORD_UNCURRECT.getDesc());
        } else {
            res = ServerResponse.createBySuccess(dbUser);
        }
        return res;
    }
}