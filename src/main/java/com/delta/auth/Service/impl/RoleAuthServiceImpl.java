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
import com.delta.auth.Service.RoleAuthService;
import com.delta.auth.dao.RoleAuthMapper;
import com.delta.auth.dto.TweiRoleAuth;
import com.delta.common.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class RoleAuthServiceImpl extends BaseServiceImpl<RoleAuthMapper, TweiRoleAuth> implements RoleAuthService {

    @Autowired
    public RoleAuthServiceImpl(RoleAuthMapper roleAuthMapper) {
        super(roleAuthMapper);
    }

    @Override
    protected EntityWrapper<TweiRoleAuth> getDeleteAndUpdateWrapper(TweiRoleAuth user) {
        return null;
    }

    @Override
    public ServerResponse<TweiRoleAuth> insertBatch(List<TweiRoleAuth> tList) {
        return null;
    }

    @Override
    public ServerResponse<TweiRoleAuth> deleteBatch(List<TweiRoleAuth> tList) {
        return null;
    }

    @Override
    public ServerResponse<TweiRoleAuth> updateBatch(List<TweiRoleAuth> tList) {
        return null;
    }
}