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
public interface RoleAuthService extends BaseService<TweiRoleAuth> {
    /**
     * 批量新增
     *
     * @param tList
     * @return
     */
    ServerResponse<TweiRoleAuth> insertBatch(List<TweiRoleAuth> tList);

    /**
     * 批量刪除
     *
     * @param tList
     * @return
     */
    ServerResponse<TweiRoleAuth> deleteBatch(List<TweiRoleAuth> tList);

    /**
     * 批量更新
     *
     * @param tList
     * @return
     */
    ServerResponse<TweiRoleAuth> updateBatch(List<TweiRoleAuth> tList);
}