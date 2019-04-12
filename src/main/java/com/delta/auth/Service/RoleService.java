/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: UserService
 * Author:   anywhere
 * Date:     4/6 0006 14:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.delta.auth.Service;

import com.delta.auth.dto.TweiRole;
import com.delta.auth.dto.TweiRoleAuth;
import com.delta.common.utils.ServerResponse;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author anywhere
 * @create 4/6 0006
 * @since 1.0.0
 */
public interface RoleService extends BaseService<TweiRole> {
    public ServerResponse<TweiRoleAuth> setAuths(List<TweiRoleAuth> roleAuths);

    public List<TweiRole> getRoles(String userid);

    public List<TweiRole> oweRoles(String userid);
}