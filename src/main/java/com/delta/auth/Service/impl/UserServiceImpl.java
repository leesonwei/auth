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
import com.delta.auth.dao.RoleMapper;
import com.delta.auth.dao.UserMapper;
import com.delta.auth.dao.UserRoleMapper;
import com.delta.auth.dto.TweiRole;
import com.delta.auth.dto.TweiUser;
import com.delta.auth.dto.TweiUserRole;
import com.delta.common.code.LoginCode;
import com.delta.common.utils.EncryptUtil;
import com.delta.common.utils.ServerResponse;
import com.delta.common.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author anywhere
 * @create 4/6 0006
 * @since 1.0.0
 */
@Service
@EnableAsync
@Slf4j
public class UserServiceImpl extends BaseServiceImpl<UserMapper, TweiUser> implements UserService {

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        super(userMapper);
    }

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;



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
            TweiRole role = roleMapper.getMaxRoles(dbUser.getUserid());
            dbUser.setRole(role);
            res = ServerResponse.createBySuccess(dbUser);
        }

        updateUser(dbUser);
        return res;
    }

    @Async
    public void updateUser(TweiUser user){
        TweiUser userNew = new TweiUser();
        userNew.setUserid(user.getUserid());
        userNew.setLastLoginAt(Calendar.getInstance().getTime());
        int count = dao.updateById(userNew);
        if (count != 1) {
            log.info("更新用戶最後登錄時間失敗");
        }
    }

    @Override
    @Transactional
    public ServerResponse<TweiUserRole> setRoles(List<TweiUserRole> userRoles) {
        EntityWrapper<TweiUserRole> wrapper = new EntityWrapper();
        if (userRoles.size() <= 0) {
            return ServerResponse.createByErrorMessage("角色列表不能為空");
        }
        wrapper.eq("userid",userRoles.get(0).getUserid());
        userRoleMapper.delete(wrapper);
        userRoleMapper.insertBatch(userRoles);
        return ServerResponse.createBySuccess();
    }

    @Override
    public List<TweiUser> selectListByRole(String userid) {
        return dao.selectListByRole(userid);
    }

    @Override
    public ServerResponse<TweiUser> insertOne(TweiUser user, HttpServletRequest request) {
        user.setDataVersion(0);
        user.setCreateAt(Calendar.getInstance().getTime());
        int count = dao.insert(user);
        if (count != 1) {
            return ServerResponse.createByErrorMessage("添加失敗");
        }
        return ServerResponse.createBySuccess(user);
    }

    public List<TweiUser> selectList(TweiUser user) {
        return dao.selectListByRole(user.getUserid());
    }
}