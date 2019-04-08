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
import com.delta.auth.Service.OrganizationRoleService;
import com.delta.auth.dao.OrganizationRoleMapper;
import com.delta.auth.dto.TweiOrganizationRole;
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
public class OrganizationRoleServiceImpl extends BaseServiceImpl<OrganizationRoleMapper, TweiOrganizationRole> implements OrganizationRoleService {

    @Autowired
    public OrganizationRoleServiceImpl(OrganizationRoleMapper organizationRoleMapper) {
        super(organizationRoleMapper);
    }

    @Override
    protected EntityWrapper<TweiOrganizationRole> getDeleteAndUpdateWrapper(TweiOrganizationRole user) {
        return null;
    }

    @Override
    public ServerResponse<TweiOrganizationRole> insertBatch(List<TweiOrganizationRole> tList) {
        return null;
    }

    @Override
    public ServerResponse<TweiOrganizationRole> deleteBatch(List<TweiOrganizationRole> tList) {
        return null;
    }

    @Override
    public ServerResponse<TweiOrganizationRole> updateBatch(List<TweiOrganizationRole> tList) {
        return null;
    }
}