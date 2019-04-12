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
import com.delta.auth.Service.RoleService;
import com.delta.auth.dao.RoleAuthMapper;
import com.delta.auth.dao.RoleMapper;
import com.delta.auth.dao.UserRoleMapper;
import com.delta.auth.dto.TweiRole;
import com.delta.auth.dto.TweiRoleAuth;
import com.delta.auth.dto.TweiUserRole;
import com.delta.common.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, TweiRole> implements RoleService {

    @Autowired
    private RoleAuthMapper roleAuthMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper) {
        super(roleMapper);
    }

    @Override
    protected EntityWrapper<TweiRole> getDeleteAndUpdateWrapper(TweiRole role) {
        EntityWrapper<TweiRole> wrapper = new EntityWrapper<>();
        wrapper.eq("role_id", role.getRoleId());
        wrapper.eq("data_version", role.getDataVersion());
        return wrapper;
    }

    @Override
    public ServerResponse<TweiRole> deleteOne(TweiRole role, HttpServletRequest request) {
        EntityWrapper<TweiRole> wrapper = getDeleteAndUpdateWrapper(role);
        if (null == wrapper || wrapper.isEmptyOfWhere()) {
            return ServerResponse.createByErrorMessage("刪除條件不能為空");
        }
        EntityWrapper user = new EntityWrapper();
        user.eq("role_id", role.getRoleId());
        List<TweiUserRole> users = userRoleMapper.selectList(user);
        if (users.size() > 0) {
            return ServerResponse.createByErrorMessage("該角色下還設置有用戶,不可刪除");
        }
        int count = dao.delete(wrapper);
        if (count != 1) {
            return ServerResponse.createByErrorMessage("刪除失敗");
        }
        return ServerResponse.createBySuccess(role);
    }

    @Override
    @Transactional
    public ServerResponse<TweiRoleAuth> setAuths(List<TweiRoleAuth> roleAuths) {
        if (roleAuths.size() <= 0) {
            return ServerResponse.createByErrorMessage("角色不能為空");
        }
        EntityWrapper<TweiRoleAuth> wrapper = new EntityWrapper();
        wrapper.eq("role_id", roleAuths.get(0).getRoleId());
        roleAuthMapper.delete(wrapper);
        roleAuthMapper.insertBatch(roleAuths);
        return ServerResponse.createBySuccess();
    }

    @Override
    public List<TweiRole> getRoles(String userid) {
        return dao.getRoles(userid);
    }

    @Override
    public List<TweiRole> oweRoles(String userid) {
        return dao.oweRoles(userid);
    }

}